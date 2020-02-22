package frc.robot;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.SlotConfiguration;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import frc.robot.subsystems.DrivetrainSubsystem.DriveMode;

public final class Constants {
    public static int PORT_JOYSTICK_DRIVE = 0;
    public static int PORT_JOYSTICK_OPERATOR = 1;

    public static int PORT_BREAKBEAM_SENSOR = -1;
    public static int DIO_BREAKBEAM_STORAGE_LOW = -1;
    public static int DIO_BREAKBEAM_STORAGE_HIGH = -1;
    public static double STORAGE_MOTOR_SPEED = -1;

    public static int PCM_CLIMB = -1;

    public static int CAN_PCM = 12;
    public static int CAN_SHOOTER_TALONSRX = 36;
    public static int CAN_SHOOTER_SLAVE_TALONSRX = 36;
    public static int CAN_DRIVETRAIN_LEFT_REAR_TALONSRX = 32;
    public static int CAN_DRIVETRAIN_LEFT_FRONT_TALONSRX = 31;
    public static int CAN_DRIVETRAIN_RIGHT_REAR_TALONSRX = 34;
    public static int CAN_DRIVETRAIN_RIGHT_FRONT_TALONSRX = 33;
    
    public static int CAN_STORAGE_LOW_TALONSRX = -1;
    public static int CAN_STORAGE_HIGH_TALONSRX = -1;
    public static int CAN_CLIMB_MAIN_TALONSRX = -1;
    public static int CAN_BALANCE_CLIMB_MAIN_TALONSRX = -1;
    public static int CAN_INTAKE_MAIN_TALONSRX = -1;
    public static int CAN_SPINNER_MAIN_TALONSRX = -1;

    public static double DRIVETRAIN_TRACKWIDTH_METERS = 0.606425;
    public static double DRIVETRAIN_MINIMUM_SPEED_METERS_PER_SECOND = 0.1;
    public static double DRIVETRAIN_ENCODER_DISTANCE_TO_METERS = 0.10914037;//1 rotation is 4096 encoder units, 10.71:1 gear ratio, 6 inch wheels
    public static double DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND = DRIVETRAIN_ENCODER_DISTANCE_TO_METERS / 10;
    public static double DRIVETRAIN_MAXIMUM_SPEED_METERS_PER_SECOND = 8;
    public static DriveMode DEFAULT_DRIVE_MODE = DriveMode.Arcade;

    public static double SHOOTER_ENCODER_VELOCITY_TO_METERS_PER_SECOND = 0;

    public static double DRIVETRAIN_ARCADE_KP = 0.1;
    public static double DRIVETRAIN_ARCADE_KI = 0.1;
    public static double DRIVETRAIN_ARCADE_KD = 0.1;

    public static double LIMELIGHT_SHOOTER_KP = 0.1;
    public static double LIMELIGHT_SHOOTER_KI = 0.1;
    public static double LIMELIGHT_SHOOTER_KD = 0.1;

    public static FollowerType DEFAULT_MOTOR_FOLLOWER_TYPE = null;
    public static TalonSRXGains SHOOTER_FPID = new TalonSRXGains(0, 0, 0, 0);
    public static double SHOOTER_MAXIMUM_TESTED_ENCODER_VELOCITY = -1;
    public static int SHOOTER_MAXIMUM_ALLOWED_ANGULAR_ERROR_DEGREES = -1;

    public static double BALANCE_KP = 0.1;
    public static double BALANCE_KI = 0.1;
    public static double BALANCE_KD = 0.1;

    public static double LIMELIGHT_DRIVETRAIN_KP = 0.1;
    public static double LIMELIGHT_DRIVETRAIN_KI = 0.1;
    public static double LIMELIGHT_DRIVETRAIN_KD = 0.1;

    public static double ENCODER_RESOLUTION = 4096;
    public static int TALON_TIMEOUT_MS = 5;
    public static int TALON_DEFAULT_PID_ID = 0;//0 is primary, 1 is auxilary
    public static TalonSRXFeedbackDevice TALON_DEFAULT_FEEDBACK_DEVICE = TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative;

    public static double KS_VOLTS = -1;
    public static double KV_VOLT_SECONDS_PER_METER = -1;
    public static double KA_VOLT_SECONDS_SQUARED_PER_METER = -1;
    public static DifferentialDriveKinematics K_DRIVE_KINEMATICS = null;

    public static double K_MAX_SPEED_METERS_PER_SECOND = -1;
    public static double K_MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = -1;

    public static double K_RAMSETE_B = -1;
    public static double K_RAMSETE_Z = -1;

    public static TalonSRXGains CLIMB_FPID = new TalonSRXGains(0, 0, 0, 0);
    public static int CLIMB_TARGET_ENCODER_TICKS = -1;
    public static int CLIMB_LOCK_MAXIMUM_ALLOWED_ERROR = -1;

    public static class TalonSRXGains extends SlotConfiguration {

        public TalonSRXGains(double kF, double kP, double kI, double kD) {
            super();
            this.kF = kF;
            this.kP = kP;
            this.kI = kI;
            this.kD = kD;
        }

        public TalonSRXGains(double kF, double kP, double kI, double kD, double iA, int iZ) {
            this(kF, kP, kI, kD);
            this.maxIntegralAccumulator = iA;
            this.integralZone = iZ;
        }
    }
}
