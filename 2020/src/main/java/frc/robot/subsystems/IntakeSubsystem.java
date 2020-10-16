package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_motor;
    public final DoubleSolenoid m_solenoid;
    private final DigitalInput m_ballSensor;

    public IntakeSubsystem() {
        m_motor = new WPI_TalonSRX(CAN_INTAKE_TALONSRX);
        m_ballSensor = new DigitalInput(DIO_BREAKBEAM_INTAKE);
        m_solenoid = new DoubleSolenoid(PCM_INTAKE_FORWARD,PCM_INTAKE_REVERSE);

        m_solenoid.set(Value.kOff);
        m_motor.setInverted(true);
    }

    @Override
    public void periodic() {
        //TODO
    }

    public void set(boolean on) {
        m_solenoid.set(on ? Value.kForward : Value.kReverse);
        m_motor.set(on ? INTAKE_MOTOR_SPEED : 0);
    }

    public Value get() {
        return m_solenoid.get();
    }

    public boolean hasBall() {
        return m_ballSensor.get();
    }
}
