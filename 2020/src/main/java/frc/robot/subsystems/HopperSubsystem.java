package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HopperSubsystem extends SubsystemBase {
    
    private final WPI_TalonSRX m_motor;
    private final DigitalInput m_ballSensor;

    public HopperSubsystem() {
        m_motor = new WPI_TalonSRX(CAN_HOPPER_TALONSRX);
        m_ballSensor = new DigitalInput(DIO_BREAKBEAM_HOPPER);
    }

    @Override
    public void periodic() {}

    public void set(boolean on) {
        m_motor.set(on ? HOPPER_MOTOR_SPEED : 0);
    }

    public boolean hasBall() {
        return m_ballSensor.get();
    }
}
