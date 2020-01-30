package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class ClimbSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_climb, m_balance;
    private final Solenoid m_lock;

    public ClimbSubsystem() {
        m_climb = new WPI_TalonSRX(CAN_CLIMB_MAIN_TALONSRX);
        m_balance = new WPI_TalonSRX(CAN_CLIMB_BALANCE_TALONSRX);
        m_lock = new Solenoid(PCM_CLIMB);
    }

    @Override
    public void periodic() {
        //TODO
    }


    //TODO
}
