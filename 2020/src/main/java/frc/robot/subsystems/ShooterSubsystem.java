package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Conversions;
import frc.robot.Constants.MotionControl;
import frc.robot.Constants.MotorConfig;
import frc.robot.Constants.Ports;
import frc.robot.Constants.SubsystemConfig;
import io.github.oblarg.oblog.Loggable;

public class ShooterSubsystem extends SubsystemBase implements Loggable {

    private final WPI_TalonSRX m_motor, m_slave;

    public ShooterSubsystem() {
        m_motor = new WPI_TalonSRX(Ports.CAN_SHOOTER_TALONSRX);
        m_slave = new WPI_TalonSRX(Ports.CAN_SHOOTER_SLAVE_TALONSRX);
        
        configureMotors();
    }

    @Override
    public void periodic() {}

    public void fireRaw(double speed) {
        //TODO Add acceleration
        m_motor.set(ControlMode.Velocity, speed, DemandType.ArbitraryFeedForward, MotionControl.SHOOTER_FEEDFORWARD.calculate(speed) * Conversions.SHOOTER_FEEDFORWARD_TO_ENCODER_UNITS);
    }

    public void setRotationPercentage(double speed) {
        fireRaw(speed * SubsystemConfig.SHOOTER_MAXIMUM_TESTED_ENCODER_VELOCITY);
    }

    public void firePO(double speed) {
        m_motor.set(ControlMode.PercentOutput, speed);
        System.out.println(m_motor.getSelectedSensorVelocity());
    }
    
    public double getVelocity() {
        return m_motor.getSelectedSensorVelocity() * Conversions.SHOOTER_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }

    public boolean isReady() {
        return Math.abs(m_motor.getClosedLoopError()) < SubsystemConfig.SHOOTER_MAXIMUM_ALLOWED_VELOCITY_ERROR;
    }

    private void configureMotors() {
        
        //First setup talons with default settings
        m_motor.configFactoryDefault();
        m_slave.configFactoryDefault();

        m_slave.follow(m_motor, MotorConfig.DEFAULT_MOTOR_FOLLOWER_TYPE);

        //Setup talon built-in PID
        m_motor.configSelectedFeedbackSensor(MotorConfig.TALON_DEFAULT_FEEDBACK_DEVICE, MotorConfig.TALON_DEFAULT_PID_ID, MotorConfig.TALON_TIMEOUT_MS);

        //Create config objects
        TalonSRXConfiguration configM = new TalonSRXConfiguration();

        //Setup config objects with desired values
        configM.slot0 = MotionControl.SHOOTER_PID;
        
        //Configure talons
        m_motor.configAllSettings(configM);
    }
}
