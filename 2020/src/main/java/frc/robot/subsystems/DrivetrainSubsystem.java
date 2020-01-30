package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

import java.util.function.DoubleSupplier;

//See https://docs.wpilib.org/en/latest/docs/software/commandbased/pid-subsystems-commands.html
//And perhaps https://github.com/wpilibsuite/allwpilib/blob/master/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/gyrodrivecommands/commands/TurnToAngle.java

//But most importantly https://docs.wpilib.org/en/latest/docs/software/commandbased/pid-subsystems-commands.html
//Maybe change this back to "normal" subsystem and use a PIDCommand because the drivetrain can both turn and go straight?

//https://www.chiefdelphi.com/t/frc-driving-straight/364640

public class DrivetrainSubsystem extends SubsystemBase {

    private final SpeedControllerGroup m_left_group, m_right_group;

    private final DifferentialDrive m_drive;

    private DoubleSupplier slowSupplier = () -> SLOW_MULTIPLIER;


    public DrivetrainSubsystem() {
        m_left_group = new SpeedControllerGroup(new WPI_TalonSRX(CAN_DRIVETRAIN_LEFT_FRONT_TALONSRX), new WPI_VictorSPX(CAN_DRIVETRAIN_LEFT_REAR_TALONSRX));
        m_right_group = new SpeedControllerGroup(new WPI_VictorSPX(CAN_DRIVETRAIN_RIGHT_FRONT_TALONSRX), new WPI_TalonSRX(CAN_DRIVETRAIN_RIGHT_REAR_TALONSRX));
        m_drive = new DifferentialDrive(m_left_group, m_right_group);
        Shuffleboard.getTab("config").addNumber("slowSpeed", slowSupplier);
    }

    @Override
    public void periodic() {
    }

    public void drive(double left, double right) {
        m_drive.arcadeDrive(left * slowSupplier.getAsDouble(), right * slowSupplier.getAsDouble(), true);
       // m_left_group.set(left);
        //m_right_group.set(right);
    }
}
