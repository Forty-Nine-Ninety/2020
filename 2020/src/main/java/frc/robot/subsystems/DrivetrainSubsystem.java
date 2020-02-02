package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Util;

import static frc.robot.Constants.*;

public class DrivetrainSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_leftTalon, m_rightTalon;
    private final WPI_VictorSPX m_leftVictor, m_rightVictor;

    private final AHRS m_gyro;
    
    private final DifferentialDriveKinematics m_kinematics;
    private final DifferentialDriveOdometry m_odometry;

    public DrivetrainSubsystem() {
        m_leftTalon = new WPI_TalonSRX(CAN_DRIVETRAIN_LEFT_TALONSRX);
        m_leftVictor = new WPI_VictorSPX(CAN_DRIVETRAIN_LEFT_VICTORSPX);
        m_rightTalon = new WPI_TalonSRX(CAN_DRIVETRAIN_RIGHT_TALONSRX);
        m_rightVictor = new WPI_VictorSPX(CAN_DRIVETRAIN_RIGHT_VICTORSPX);

        configureMotors();

        m_gyro = new AHRS(SPI_PORT_GYRO);
        m_gyro.reset();

        m_kinematics = new DifferentialDriveKinematics(DRIVETRAIN_TRACKWIDTH_METERS);
        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(m_gyro.getAngle()));
    }

    @Override
    public void periodic() {
        //Update odometry
        m_odometry.update(Rotation2d.fromDegrees(m_gyro.getAngle()), getDistanceLeft(), getDistanceRight());
    }

    //Assumes left and right are in encoder units per 100ms
    public void driveRaw(double left, double right) {
        m_leftTalon.set(ControlMode.Velocity, left);
        m_rightTalon.set(ControlMode.Velocity, right);
    }

    //Functions below are for 0-1
    public void tankDrive(double left, double right) {
        //Convert from value in range [0, 1] to raw encoder units
        left *= MAXIMUM_TESTED_ENCODER_VELOCITY;
        right *= MAXIMUM_TESTED_ENCODER_VELOCITY;
        driveRaw(left, right);
    }

    public void tankDrive(double[] speeds) {
        this.tankDrive(speeds[0], speeds[1]);
    }

    public void arcadeDrive(double speed, double rot) {
        this.tankDrive(Util.arcadeToTankDrive(speed, rot));
    }

    public void arcadeDrive(double[] speeds) {
        this.arcadeDrive(speeds[0], speeds[1]);
    }

    private void configureMotors() {
        
        //First setup talons with default settings
        m_leftTalon.configFactoryDefault();
        m_leftVictor.configFactoryDefault();
        m_rightTalon.configFactoryDefault();
        m_rightVictor.configFactoryDefault();

        
        //Left side encoder goes in the wrong direction
        m_leftTalon.setSensorPhase(true);

        m_leftVictor.follow(m_leftTalon, VICTOR_MOTOR_FOLLOWER_TYPE);
        m_rightVictor.follow(m_rightTalon, VICTOR_MOTOR_FOLLOWER_TYPE);

        //Setup talon built-in PID
        m_leftTalon.configSelectedFeedbackSensor(TALON_DEFAULT_FEEDBACK_DEVICE, TALON_DEFAULT_PID_ID, TALON_TIMEOUT_MS);
        m_rightTalon.configSelectedFeedbackSensor(TALON_DEFAULT_FEEDBACK_DEVICE, TALON_DEFAULT_PID_ID, TALON_TIMEOUT_MS);

        //Create config objects
        TalonSRXConfiguration cLeft = new TalonSRXConfiguration(), cRight = new TalonSRXConfiguration();

        //Setup config objects with desired values
        cLeft.slot0 = DRIVETRAIN_LEFT_FPID;
        cRight.slot0 = DRIVETRAIN_RIGHT_FPID;

        //Configure talons
        m_leftTalon.configAllSettings(cLeft);
        m_rightTalon.configAllSettings(cRight);
    }

    public double getGyroRate() {
        return m_gyro.getRate();
    }

    public double getDistanceLeft() {
        return m_leftTalon.getSelectedSensorPosition() * DRIVETRAIN_ENCODER_DISTANCE_TO_METERS;
    }

    public double getDistanceRight() {
        return m_rightTalon.getSelectedSensorPosition() * DRIVETRAIN_ENCODER_DISTANCE_TO_METERS;
    }

    public double getRateLeft() {
        return m_leftTalon.getSelectedSensorVelocity() * DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }

    public double getRateRight() {
        return m_rightTalon.getSelectedSensorVelocity() * DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    }
}
