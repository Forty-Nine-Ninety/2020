package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class DrivetrainSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_leftFront, m_leftRear, m_rightFront, m_rightRear;

    private final AHRS m_gyro;

    private final SpeedControllerGroup m_motorGroupLeft;
    private final SpeedControllerGroup m_motorGroupRight;

    private final DifferentialDrive m_drive;
    
    private final DifferentialDriveKinematics m_kinematics;
    private final DifferentialDriveOdometry m_odometry;

    public DrivetrainSubsystem() {
        m_leftFront = new WPI_TalonSRX(CAN_DRIVETRAIN_LEFT_FRONT_TALONSRX);
        m_leftRear = new WPI_TalonSRX(CAN_DRIVETRAIN_LEFT_REAR_TALONSRX);
        m_rightFront = new WPI_TalonSRX(CAN_DRIVETRAIN_RIGHT_FRONT_TALONSRX);
        m_rightRear = new WPI_TalonSRX(CAN_DRIVETRAIN_RIGHT_REAR_TALONSRX);

        m_motorGroupLeft = new SpeedControllerGroup(m_leftFront, m_leftRear);
        m_motorGroupRight = new SpeedControllerGroup(m_rightFront, m_rightRear);
        m_drive = new DifferentialDrive(m_motorGroupLeft, m_motorGroupRight);

        m_gyro = new AHRS(SPI.Port.kMXP);
        m_gyro.reset();
        m_kinematics = new DifferentialDriveKinematics(DRIVETRAIN_TRACKWIDTH_METERS);
        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(m_gyro.getAngle()));
    }

    @Override
    public void periodic() {
        //Update odometry
        m_odometry.update(Rotation2d.fromDegrees(m_gyro.getAngle()), getDistanceLeft(), getDistanceRight());
    }

    public void tankDrive(double left, double right) {
        m_drive.tankDrive(left, right, false);
    }

    public void arcadeDrive(double speed, double rot) {
        m_drive.arcadeDrive(speed, rot);
    }

    public double getGyroRate() {
        return m_gyro.getRate();
    }

    public double getDistanceLeft() {
        return m_leftFront.getSelectedSensorPosition() * DRIVETRAIN_ENCODER_DISTANCE_TO_METERS;
    }

    public double getDistanceRight() {
        return m_leftFront.getSelectedSensorPosition() * DRIVETRAIN_ENCODER_DISTANCE_TO_METERS;
    }

    public double getRateLeft() {
        return m_leftFront.getSelectedSensorVelocity() * DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }

    public double getRateRight() {
        return m_rightFront.getSelectedSensorVelocity() * DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }
}
