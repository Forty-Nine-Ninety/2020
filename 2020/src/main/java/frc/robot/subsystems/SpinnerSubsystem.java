package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class SpinnerSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_motor;

    public SpinnerSubsystem() {
        m_motor = new WPI_TalonSRX(CAN_SPINNER_TALONSRX);
    }

    @Override
    public void periodic() {}

    public void spin(double speed) {
        m_motor.set(speed);
    }

    public double getSpinRate() {
        return m_motor.getSelectedSensorVelocity() * SPINNER_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }
}
