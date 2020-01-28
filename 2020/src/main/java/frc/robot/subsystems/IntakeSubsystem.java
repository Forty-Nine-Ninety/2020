package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_main;
    private final DigitalInput breakBeam;

    public IntakeSubsystem() {
        m_main = new WPI_TalonSRX(CAN_INTAKE_MAIN_TALONSRX);
        breakBeam = new DigitalInput(PORT_BREAKBEAM_SENSOR);
    }

    @Override
    public void periodic() {
        checkBeam(this.breakBeam);
    }

    private void checkBeam(DigitalInput breakBeam){
        if (breakBeam.get()){
            //addBall but I don't want to create an instance of storage subsystem
        }
    }
    public void setSpeed(double speed){
        m_main.set(speed);
    }
}