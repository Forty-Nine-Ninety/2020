package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class ClimbSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_main;

    public ClimbSubsystem() {
        m_main = new WPI_TalonSRX(CAN_CLIMB_MAIN_TALONSRX);
    }

    @Override
    public void periodic() {
    }

    public void setSpeed(double speed){
        m_main.set(speed);
    }
}
