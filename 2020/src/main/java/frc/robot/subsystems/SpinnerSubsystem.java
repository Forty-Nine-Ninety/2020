package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class SpinnerSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_main;
    private final double spinSpeed = -1;

    public SpinnerSubsystem() {
        m_main = new WPI_TalonSRX(CAN_SPINNER_MAIN_TALONSRX);
    }

    @Override
    public void periodic() {
    }

    public void setSpeed(){
        m_main.set(this.spinSpeed);
    }
}
