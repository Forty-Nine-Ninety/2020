package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Direction;

import static frc.robot.Constants.*;
import io.github.oblarg.oblog.Loggable;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HopperSubsystem extends SubsystemBase implements Loggable {
    
    private final WPI_TalonSRX m_leftMotor;
    private final WPI_TalonSRX m_rightMotor;
    private final DigitalInput m_ballSensor;

    public HopperSubsystem() {
        m_leftMotor = new WPI_TalonSRX(Ports.CAN_LEFT_HOPPER_TALONSRX);
        m_rightMotor = new WPI_TalonSRX(Ports.CAN_RIGHT_HOPPER_TALONSRX);
        m_ballSensor = new DigitalInput(Ports.DIO_BREAKBEAM_HOPPER);
    }

    @Override
    public void periodic() {}

    public void run(Direction direction) {
        int multiplier = direction.getMultiplier();

        double leftMotorSpeed = multiplier * HOPPER_MOTOR_SPEED;
        double rightMotorSpeed = -1 * leftMotorSpeed;

        m_rightMotor.set(rightMotorSpeed);
        m_leftMotor.set(leftMotorSpeed);

        m_rightMotor.configFactoryDefault();

        m_leftMotor.configFactoryDefault();

    }

    public boolean hasBall() {
        return m_ballSensor.get();
    }
}
