package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;
import io.github.oblarg.oblog.Loggable;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HopperSubsystem extends SubsystemBase implements Loggable {
    
    private final WPI_TalonSRX m_leftMotor;
    private final WPI_TalonSRX m_rightMotor;
    private final DigitalInput m_ballSensor;

    public HopperSubsystem() {
        m_leftMotor = new WPI_TalonSRX(CAN_LEFT_HOPPER_TALONSRX);
        m_rightMotor = new WPI_TalonSRX(CAN_RIGHT_HOPPER_TALONSRX);
        m_ballSensor = new DigitalInput(DIO_BREAKBEAM_HOPPER);
    }

    @Override
    public void periodic() {}

    public void set(boolean on) {
        m_leftMotor.set(on ? -1 * HOPPER_MOTOR_SPEED : 0);
        m_rightMotor.set(on ? HOPPER_MOTOR_SPEED : 0);
    }

    public void setReverse(boolean on) {
        m_leftMotor.set(on ? HOPPER_MOTOR_SPEED : 0);
        m_rightMotor.set(on ? -1 * HOPPER_MOTOR_SPEED : 0);
    }

    public boolean hasBall() {
        return m_ballSensor.get();
    }
}
