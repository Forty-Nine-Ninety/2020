package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class DrivetrainSubsystem extends SubsystemBase {

    private final SpeedControllerGroup m_left_group, m_right_group;

    private final DifferentialDrive m_drive;


    public DrivetrainSubsystem() {
        m_left_group = new SpeedControllerGroup(new WPI_TalonSRX(CAN_DRIVETRAIN_LEFT_FRONT_TALONSRX), new WPI_VictorSPX(CAN_DRIVETRAIN_LEFT_REAR_TALONSRX));
        m_right_group = new SpeedControllerGroup(new WPI_VictorSPX(CAN_DRIVETRAIN_RIGHT_FRONT_TALONSRX), new WPI_TalonSRX(CAN_DRIVETRAIN_RIGHT_REAR_TALONSRX));
        m_drive = new DifferentialDrive(m_left_group, m_right_group);
    }

    @Override
    public void periodic() {
    }
    public void setSpeedMultiplier(double d) {
        SLOW_MULTIPLIER = d;
    }

    public void drive(double left, double right) {
        m_drive.arcadeDrive(left * SLOW_MULTIPLIER, right * SLOW_MULTIPLIER, true);
    }
}
