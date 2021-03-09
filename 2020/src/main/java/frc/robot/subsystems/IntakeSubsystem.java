package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Direction;
import io.github.oblarg.oblog.Loggable;

import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase implements Loggable {

    private final WPI_TalonSRX m_motor;
    private final DoubleSolenoid m_solenoid;
    private final DigitalInput m_ballSensor;
    private boolean m_reversed;

    public IntakeSubsystem() {
        m_motor = new WPI_TalonSRX(Ports.CAN_INTAKE_TALONSRX);
        m_ballSensor = new DigitalInput(Ports.DIO_BREAKBEAM_INTAKE);
        m_solenoid = new DoubleSolenoid(Ports.PCM_INTAKE_FORWARD, Ports.PCM_INTAKE_REVERSE);

        m_solenoid.set(Value.kOff);
        m_motor.setInverted(true);
    }

    @Override
    public void periodic() {
        //TODO I don't think there needs to be anything here.
    }

    public void run(Direction direction) {
        int multiplier = direction.getMultiplier();
        m_motor.set(multiplier * INTAKE_MOTOR_SPEED);

        //Either running forwards or backwards, solenoid extends
        if (Math.abs(multiplier) == 1) {
            m_solenoid.set(Value.kForward);
        }
        //The motor is stopped, intake is retracted
        else {
            m_solenoid.set(Value.kReverse);
        }
    }

    public Value get() {
        return m_solenoid.get();
    }

    public boolean hasBall() {
        return m_ballSensor.get();
    }
}
