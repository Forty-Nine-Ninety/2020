package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;
import frc.robot.RobotContainer;

public class IntakeSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_main;
    private final DigitalInput m_breakBeam;
    private final double intakeSpeed = -1;

    public IntakeSubsystem() {
        m_main = new WPI_TalonSRX(CAN_INTAKE_MAIN_TALONSRX);
        m_breakBeam = new DigitalInput(PORT_BREAKBEAM_SENSOR);
    }

    @Override
    public void periodic() {
        checkBeam();
    }

    public boolean checkBeam(){
        return this.m_breakBeam.get();
    }
    public void run(){
        m_main.set(this.intakeSpeed);
    }
}