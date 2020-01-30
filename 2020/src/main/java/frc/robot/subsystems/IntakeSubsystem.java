package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_motor;
    private final DigitalInput m_ballSensor;

    public IntakeSubsystem() {
        m_motor = new WPI_TalonSRX(CAN_INTAKE_TALONSRX);
        m_ballSensor = new DigitalInput(DIO_BREAKBEAM);
    }

    @Override
    public void periodic() {
        //TODO
    }
}
