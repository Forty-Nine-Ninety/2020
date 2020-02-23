package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class ShooterSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_motor, m_slave, m_inserter;

    public ShooterSubsystem() {
        m_motor = new WPI_TalonSRX(CAN_SHOOTER_TALONSRX);
        m_slave = new WPI_TalonSRX(CAN_SHOOTER_SLAVE_TALONSRX);
        m_inserter = new WPI_TalonSRX(CAN_SHOOTER_INSERTER_TALONSRX);

        configureMotors();
    }

    @Override
    public void periodic() {}

    public void fireRaw(double speed) {
        m_motor.set(ControlMode.Velocity, speed);
        m_inserter.set(ControlMode.Velocity, speed * SHOOTER_INSERTER_SPEED_MULTIPLIER);
    }

    public void setFireSpeed(double speed) {
        fireRaw(speed * SHOOTER_MAXIMUM_TESTED_ENCODER_VELOCITY);
    }
    
    public double getVelocity() {
        return m_motor.getSelectedSensorVelocity() * SHOOTER_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }

    public double getVelocityError() {
        return m_motor.getClosedLoopError();
    }

    private void configureMotors() {
        
        //First setup talons with default settings
        m_motor.configFactoryDefault();
        m_slave.configFactoryDefault();
        m_inserter.configFactoryDefault();

        m_slave.follow(m_motor, DEFAULT_MOTOR_FOLLOWER_TYPE);

        //Setup talon built-in PID
        m_motor.configSelectedFeedbackSensor(TALON_DEFAULT_FEEDBACK_DEVICE, TALON_DEFAULT_PID_ID, TALON_TIMEOUT_MS);
        m_inserter.configSelectedFeedbackSensor(TALON_DEFAULT_FEEDBACK_DEVICE, TALON_DEFAULT_PID_ID, TALON_TIMEOUT_MS);

        //Create config objects
        TalonSRXConfiguration configM = new TalonSRXConfiguration(), configI = new TalonSRXConfiguration();

        //Setup config objects with desired values
        configM.slot0 = SHOOTER_FPID;
        configI.slot0 = SHOOTER_INSERTER_FPID;
        
        //Configure talons
        m_motor.configAllSettings(configM);
        m_inserter.configAllSettings(configI);
    }
}
