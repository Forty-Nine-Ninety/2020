package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

//See https://docs.wpilib.org/en/latest/docs/software/commandbased/pid-subsystems-commands.html
//And perhaps https://github.com/wpilibsuite/allwpilib/blob/master/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/gyrodrivecommands/commands/TurnToAngle.java

//But most importantly https://docs.wpilib.org/en/latest/docs/software/commandbased/pid-subsystems-commands.html
//Maybe change this back to "normal" subsystem and use a PIDCommand because the drivetrain can both turn and go straight?

//https://www.chiefdelphi.com/t/frc-driving-straight/364640

public class DrivetrainSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_left, m_right;

    private final DifferentialDrive m_drive;


    public DrivetrainSubsystem() {
        m_left = new WPI_TalonSRX(CAN_DRIVETRAIN_LEFT_TALONSRX);
        m_right = new WPI_TalonSRX(CAN_DRIVETRAIN_RIGHT_TALONSRX);
        m_drive = new DifferentialDrive(m_left, m_right);
    }

    @Override
    public void periodic() {
    }

    public void drive(double left, double right) {
        m_drive.tankDrive(left, right, true);
    }
}
