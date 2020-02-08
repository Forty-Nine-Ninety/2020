package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class ShooterSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_motor, m_slave;

    public ShooterSubsystem() {
        m_motor = new WPI_TalonSRX(CAN_SHOOTER_TALONSRX);
        m_slave = new WPI_TalonSRX(CAN_SHOOTER_SLAVE_TALONSRX);

        configureMotors();
    }

    @Override
    public void periodic() {}

    public void fireRaw(double speed) {
        m_motor.set(ControlMode.Velocity, speed);
    }

    public void fire(double speed) {
        fireRaw(speed * SHOOTER_MAXIMUM_TESTED_ENCODER_VELOCITY);
    }
    
    public double getRateFire() {
        return m_motor.getSelectedSensorVelocity() * SHOOTER_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }

    private void configureMotors() {
        
        //First setup talons with default settings
        m_motor.configFactoryDefault();
        m_slave.configFactoryDefault();

        m_slave.follow(m_motor, DEFAULT_MOTOR_FOLLOWER_TYPE);

        //Setup talon built-in PID
        m_motor.configSelectedFeedbackSensor(TALON_DEFAULT_FEEDBACK_DEVICE, TALON_DEFAULT_PID_ID, TALON_TIMEOUT_MS);

        //Create config objects
        TalonSRXConfiguration config = new TalonSRXConfiguration();

        //Setup config objects with desired values
        config.slot0 = SHOOTER_FPID;

        //Configure talons
        m_motor.configAllSettings(config);
    }
}
