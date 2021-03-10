package frc.robot;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.SlotConfiguration;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import frc.robot.vision.*;

import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;

public final class Constants implements Loggable {
    public static class Ports {
        //Laptop ports
        public static int PORT_JOYSTICK_DRIVE = 0;
        public static int PORT_JOYSTICK_OPERATOR = 1;

        //RoboRIO sensor ports
        public static int DIO_BREAKBEAM_INTAKE = 4;
        public static int DIO_BREAKBEAM_HOPPER = 5;
        public static int DIO_BREAKBEAM_STORAGE_LOW = 6;
        public static int DIO_BREAKBEAM_STORAGE_HIGH = 7;
        public static int DIO_LIMIT_CLIMB_TOP_1 = 2;
        public static int DIO_LIMIT_CLIMB_TOP_2 = 3;
        public static int DIO_LIMIT_CLIMB_BOTTOM_1 = 0;
        public static int DIO_LIMIT_CLIMB_BOTTOM_2 = 1;
        //Below is format for analog sensors
        //public static int PWM_NAME = -1;
        public static SPI.Port SPI_PORT_GYRO = SPI.Port.kMXP;

        //Solenoid PCM ports
        public static int PCM_CLIMB = 7;
        public static int PCM_INTAKE_FORWARD = 1;
        public static int PCM_INTAKE_REVERSE = 2;

        //CAN Bus IDs
        public static int CAN_PDP = 0;
        public static int CAN_PCM = 0;
        public static int CAN_DRIVETRAIN_RIGHT_TALONSRX = 0;
        public static int CAN_DRIVETRAIN_RIGHT_VICTORSPX = 1;
        public static int CAN_DRIVETRAIN_LEFT_TALONSRX = 15;
        public static int CAN_DRIVETRAIN_LEFT_VICTORSPX = 14;
        public static int CAN_SHOOTER_TALONSRX = 2;
        public static int CAN_SHOOTER_SLAVE_TALONSRX = 3;
        public static int CAN_SHOOTER_INSERTER_TALONSRX = 30;
        public static int CAN_CLIMB_MAIN_TALONSRX = 13;
        public static int CAN_CLIMB_BALANCE_TALONSRX = 6;
        public static int CAN_STORAGE_LOW_TALONSRX = 5;
        public static int CAN_INTAKE_TALONSRX = 8;
        public static int CAN_SPINNER_TALONSRX = -1;
        public static int CAN_LEFT_HOPPER_TALONSRX = 7;
        public static int CAN_RIGHT_HOPPER_TALONSRX = 9;
    }

    public static class MotorConfig {
        //Talon and Victor information
        public static double TALON_ENCODER_RESOLUTION = 4096; // = EPR = CPR
        public static int TALON_TIMEOUT_MS = 5;
        public static int TALON_DEFAULT_PID_ID = 0;//0 is primary, 1 is auxilary
        public static TalonSRXFeedbackDevice TALON_DEFAULT_FEEDBACK_DEVICE = TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative;
        public static FollowerType DEFAULT_MOTOR_FOLLOWER_TYPE = FollowerType.PercentOutput;
    }

    public static class RobotMeasurements {
        //Robot physical characteristics
        public static double DRIVETRAIN_TRACKWIDTH_METERS = 0.606425;
        public static double DRIVETRAIN_WHEEL_RADIUS_METERS = 0.0762;//3 inches
        public static double SHOOTER_HEIGHT_METERS = 0;
        public static double SHOOTER_ANGLE_RADIANS = 0;
        public static double LIMELIGHT_HEIGHT_METERS = 0;
        public static double LIMELIGHT_ANGLE_RADIANS = 0;
    }
    
    public static class Conversions {
        //Conversions
        public static double ENCODER_VELOCITY_UNIT_TO_SECONDS = 0.1;//Encoder measures things in units per 0.1s
        public static double DRIVETRAIN_ENCODER_DISTANCE_TO_METERS = 1 / MotorConfig.TALON_ENCODER_RESOLUTION * 2 * Math.PI * RobotMeasurements.DRIVETRAIN_WHEEL_RADIUS_METERS;
        public static double DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND = DRIVETRAIN_ENCODER_DISTANCE_TO_METERS / ENCODER_VELOCITY_UNIT_TO_SECONDS;
        public static double METERS_PER_SECOND_TO_DRIVETRAIN_ENCODER_VELOCITY = 1 / DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
        public static double SHOOTER_ENCODER_VELOCITY_TO_METERS_PER_SECOND = 0;
        public static double SPINNER_ENCODER_VELOCITY_TO_METERS_PER_SECOND = 0;
        public static double DRIVETRAIN_FEEDFORWARD_TO_ENCODER_UNITS = 1;//TODO find this number
        public static double SHOOTER_FEEDFORWARD_TO_ENCODER_UNITS = 1;//TODO find this number
    }

    public static class SubsystemConfig {
        //Climb movement information
        public static int CLIMB_TARGET_ENCODER_TICKS = 0;
        public static int CLIMB_LOCK_MAXIMUM_ALLOWED_ERROR = 0;
        public static int CLIMB_TIMEOUT_S = 5;

        //Drivetrain movement information
        public static double DRIVETRAIN_MAXIMUM_TESTED_ENCODER_VELOCITY = 6000;//free speed ~3200
        public static double DRIVETRAIN_MAXIMUM_CRUISE_SPEED_METERS_PER_SECOND = 7;//Max ~7.1
        public static double DRIVETRAIN_MAXIMUM_MOVEMENT_SPEED_METERS_PER_SECOND = DRIVETRAIN_MAXIMUM_TESTED_ENCODER_VELOCITY * Conversions.DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND;
        public static double DRIVETRAIN_CLOSED_LOOP_RAMP = 0.1; //seconds from 0 to full or full to 0

        //Shooter movement information
        public static double SHOOTER_MAXIMUM_TESTED_ENCODER_VELOCITY = 5000;//TODO find this number
        public static double SHOOTER_MAXIMUM_ALLOWED_VELOCITY_ERROR = 50;//TODO find this number
        public static double SHOOTER_MAXIMUM_ALLOWED_ANGULAR_ERROR_DEGREES = 0.1;//TODO find this number

        //Storage movement information
        public static double STORAGE_MINIMUM_BALL_SPACING_ENCODER_UNITS = 50;//TODO find this number
        public static double STORAGE_LENGTH_ENCODER_UNITS = 250;//TODO find this number
        public static double STORAGE_UNJAM_REVERSE_TIME = 0.15;
        public static double STORAGE_UNJAM_WAIT_TIME = 0.05;
    }

    public static class Vision {
        //TODO find these two
        public static double TARGET_HEIGHT_METERS = 0;

        public static ControlPoint[] CONTROL_POINTS = {
            new ControlPoint(0, 1),
            new ControlPoint(1, 1)
        };
    }
    
    public static class MotionControl {
        //PID
        public static TalonSRXGains DRIVETRAIN_LEFT_PID = new TalonSRXGains(0, 0, 0);
        public static TalonSRXGains DRIVETRAIN_RIGHT_PID = new TalonSRXGains(0, 0, 0);
        public static TalonSRXGains SHOOTER_PID = new TalonSRXGains(0, 0, 0);
        public static TalonSRXGains CLIMB_PID = new TalonSRXGains(0, 0, 0);

        //Feedforward
        public static SimpleMotorFeedforward DRIVETRAIN_FEEDFORWARD = new SimpleMotorFeedforward(0.843, 0.362, 0.04);
        public static SimpleMotorFeedforward SHOOTER_FEEDFORWARD = new SimpleMotorFeedforward(0, 0, 0);

        //The following two could possibly just be normal PID values
        public static TalonSRXGains LIMELIGHT_SHOOTER_PID = new TalonSRXGains(0, 0, 0);
        public static TalonSRXGains LIMELIGHT_DRIVETRAIN_PID = new TalonSRXGains(0, 0, 0);

        public static double LIMELIGHT_SHOOTER_KP = 0.5;
        public static double LIMELIGHT_SHOOTER_KI = 0;
        public static double LIMELIGHT_SHOOTER_KD = 0;

        public static double LIMELIGHT_DRIVETRAIN_KP = 0.5;
        public static double LIMELIGHT_DRIVETRAIN_KI = 0;
        public static double LIMELIGHT_DRIVETRAIN_KD = 0;

        public static double CLIMB_BALANCE_KP = 0.5;
        public static double CLIMB_BALANCE_KI = 0;
        public static double CLIMB_BALANCE_KD = 0;
    }

    //Miscellaneous
    public static double JOYSTICKF310_AXIS_DEADBAND = 0.05;
    public static double JOYSTICK_INPUT_EXPONENT = 2;

    //Operation config
    @Config(name = "Rotation Input Multiplier", tabName = "Op Configuration")
    public static double ARCADE_ROTATION_MULTIPLIER = 0.5;

    @Config(name = "Speed Input Multiplier", tabName = "Op Configuration")
    public static double ARCADE_SPEED_MULTIPLIER = 0.75;

    @Config(name = "Intake Motor Speed", tabName = "Op Configuration")
    public static double INTAKE_MOTOR_SPEED = 0.8;

    @Config(name = "Hopper Motor Speed", tabName = "Op Configuration")
    public static double HOPPER_MOTOR_SPEED = 0.4;

    @Config(name = "Storage Motor Speed", tabName = "Op Configuration")
    public static double STORAGE_MOTOR_SPEED = 0.6;

    @Config(name = "Climb Motor Speed", tabName = "Op Configuration")
    public static double CLIMB_MOTOR_SPEED = 1;

    //TODO find this number
    @Config(name = "Inserter Motor Speed", tabName = "Op Configuration")
    public static double INSERTER_MOTOR_SPEED = 0.25;

    //Classes
    public static class TalonSRXGains extends SlotConfiguration {

        public TalonSRXGains(double kP, double kI, double kD) {
            super();
            this.kP = kP;
            this.kI = kI;
            this.kD = kD;
        }
    }
}
