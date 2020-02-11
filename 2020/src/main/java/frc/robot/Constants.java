package frc.robot;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.SlotConfiguration;

import edu.wpi.first.wpilibj.SPI;
import io.github.oblarg.oblog.annotations.Config;

public final class Constants {
    
    //Laptop ports
    public static int PORT_JOYSTICK_DRIVE = 0;
    public static int PORT_JOYSTICK_OPERATOR = 1;

    //RoboRIO sensor ports
    public static int DIO_BREAKBEAM_INTAKE = -1;
    public static int DIO_BREAKBEAM_HOPPER = -1;
    public static int DIO_BREAKBEAM_STORAGE_LOW = -1;
    public static int DIO_BREAKBEAM_STORAGE_HIGH = -1;
    //Below is format for analog sensors
    //public static int PWM_NAME = -1;
    public static SPI.Port SPI_PORT_GYRO = SPI.Port.kMXP;

    //Solenoid PCM ports
    public static int PCM_CLIMB = -1;

    //CAN Bus IDs
    public static int CAN_PCM = 1;
    public static int CAN_DRIVETRAIN_RIGHT_TALONSRX = 11;
    public static int CAN_DRIVETRAIN_RIGHT_VICTORSPX = 16;
    public static int CAN_DRIVETRAIN_LEFT_TALONSRX = 10;
    public static int CAN_DRIVETRAIN_LEFT_VICTORSPX = 15;
    public static int CAN_SHOOTER_TALONSRX = -1;
    public static int CAN_SHOOTER_SLAVE_TALONSRX = -1;
    public static int CAN_CLIMB_MAIN_TALONSRX = -1;
    public static int CAN_CLIMB_BALANCE_TALONSRX = -1;
    public static int CAN_STORAGE_LOW_TALONSRX = -1;
    public static int CAN_STORAGE_HIGH_TALONSRX = -1;
    public static int CAN_INTAKE_TALONSRX = -1;
    public static int CAN_SPINNER_TALONSRX = -1;
    public static int CAN_HOPPER_TALONSRX = -1;

    //Talon and Victor information
    public static double TALON_ENCODER_RESOLUTION = 4096;
    public static int TALON_TIMEOUT_MS = 5;
    public static int TALON_DEFAULT_PID_ID = 0;//0 is primary, 1 is auxilary
    public static TalonSRXFeedbackDevice TALON_DEFAULT_FEEDBACK_DEVICE = TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative;
    public static FollowerType DEFAULT_MOTOR_FOLLOWER_TYPE = FollowerType.PercentOutput;

    //Robot physical characteristics
    public static double DRIVETRAIN_TRACKWIDTH_METERS = 0.606425;
    public static double DRIVETRAIN_WHEEL_RADIUS_METERS = 0.0762;//3 inches
    public static double SHOOTER_HEIGHT_METERS = 0;
    public static double SHOOTER_ANGLE_RADIANS = 0;
    public static double LIMELIGHT_HEIGHT_METERS = 0;
    public static double LIMELIGHT_ANGLE_RADIANS = 0;
    
    //Conversions
    public static double ENCODER_VELOCITY_UNIT_TO_SECONDS = 0.1;//Encoder measures things in units per 0.1s
    public static double DRIVETRAIN_ENCODER_DISTANCE_TO_METERS = 1 / TALON_ENCODER_RESOLUTION * 2 * Math.PI * DRIVETRAIN_WHEEL_RADIUS_METERS;
    public static double DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND = DRIVETRAIN_ENCODER_DISTANCE_TO_METERS / ENCODER_VELOCITY_UNIT_TO_SECONDS;
    public static double METERS_PER_SECOND_TO_DRIVETRAIN_ENCODER_VELOCITY = 1 / DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
    public static double SHOOTER_ENCODER_VELOCITY_TO_METERS_PER_SECOND = 0;
    public static double SPINNER_ENCODER_VELOCITY_TO_METERS_PER_SECOND = 0;

    //Drivetrain movement information
    public static double DRIVETRAIN_MAXIMUM_TESTED_ENCODER_VELOCITY = 3000;//TODO find this number, ~3200
    public static double DRIVETRAIN_MAXIMUM_CRUISE_SPEED_METERS_PER_SECOND = 2.5;
    public static double DRIVETRAIN_MAXIMUM_MOVEMENT_SPEED_METERS_PER_SECOND = DRIVETRAIN_MAXIMUM_TESTED_ENCODER_VELOCITY * DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;

    //Shooter movement information
    public static double SHOOTER_MAXIMUM_TESTED_ENCODER_VELOCITY = 50;//TODO find this number
    public static double SHOOTER_MAXIMUM_ALLOWED_VELOCITY_ERROR = 50;//TODO find this number

    //PID
    public static TalonSRXGains DRIVETRAIN_LEFT_FPID = new TalonSRXGains(0.3, 0.5, 0, 5);
    public static TalonSRXGains DRIVETRAIN_RIGHT_FPID = new TalonSRXGains(0.3, 0.5, 0, 5);
    public static TalonSRXGains SHOOTER_FPID = new TalonSRXGains(0.3, 0.5, 0, 5);

    //The following two could possibly just be normal PID values
    public static TalonSRXGains LIMELIGHT_SHOOTER_FPID = new TalonSRXGains(0, 0, 0, 0);
    public static TalonSRXGains LIMELIGHT_DRIVETRAIN_FPID = new TalonSRXGains(0, 0, 0, 0);

    public static double LIMELIGHT_SHOOTER_KP = 0.5;
    public static double LIMELIGHT_SHOOTER_KI = 0;
    public static double LIMELIGHT_SHOOTER_KD = 0;

    public static double LIMELIGHT_DRIVETRAIN_KP = 0.5;
    public static double LIMELIGHT_DRIVETRAIN_KI = 0;
    public static double LIMELIGHT_DRIVETRAIN_KD = 0;

    //Miscellaneous
    public static double JOYSTICKF310_AXIS_DEADBAND = 0.05;
    public static double JOYSTICK_INPUT_EXPONENT = 2;

    //Operation config
    @Config(name = "Rotation Input Multiplier", tabName = "Op Configuration")
    public static double ARCADE_ROTATION_MULTIPLIER = 0.5;

    @Config(name = "Speed Input Multiplier", tabName = "Op Configuration")
    public static double ARCADE_SPEED_MULTIPLIER = 0.75;

    @Config(name = "Intake Motor Speed", tabName = "Op Configuration")
    public static double INTAKE_MOTOR_SPEED = 0.75;

    @Config(name = "Hopper Motor Speed", tabName = "Op Configuration")
    public static double HOPPER_MOTOR_SPEED = 0.75;

    @Config(name = "Storage Motor Speed", tabName = "Op Configuration")
    public static double STORAGE_MOTOR_SPEED = 0.75;

    //Classes
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
