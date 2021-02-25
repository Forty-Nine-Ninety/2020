package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.Constants.*;

public class InserterSubsystem extends CommandBase {

    private final WPI_TalonSRX m_inserter;

    public InserterSubsystem() {
        m_inserter = new WPI_TalonSRX(CAN_SHOOTER_INSERTER_TALONSRX);
    }

    public void run(boolean run) {
        m_inserter.set(run ? INSERTER_MOTOR_SPEED : 0);
    }
}