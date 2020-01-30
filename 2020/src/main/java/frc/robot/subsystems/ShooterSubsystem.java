package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class ShooterSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_fireMotor;

    public ShooterSubsystem() {
        m_fireMotor = new WPI_TalonSRX(CAN_SHOOTER_TALONSRX);
    }

    @Override
    public void periodic() {}

    public void fire(double speed) {
        m_fireMotor.set(speed);
    }

    public double getRateFire() {
        return m_fireMotor.getSelectedSensorVelocity() * SHOOTER_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }
}
