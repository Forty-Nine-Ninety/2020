package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;
import frc.robot.RobotContainer;

public class IntakeSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_main;
    private final DigitalInput breakBeam;
    private final RobotContainer robot;

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
            robot.getStorage().addBall();
        }
    }
    public void setSpeed(double speed){
        m_main.set(speed);
    }
}