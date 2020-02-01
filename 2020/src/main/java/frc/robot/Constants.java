package frc.robot;

public final class Constants {
    public static int PORT_JOYSTICK_DRIVE = 0;
    public static int PORT_JOYSTICK_OPERATOR = 1;

    public static int DIO_BREAKBEAM = -1;

    //Below is format for analog sensors
    //public static int PWM_NAME = -1;
    public static int PCM_CLIMB = -1;

    public static int CAN_PCM = 1;
    public static int CAN_DRIVETRAIN_RIGHT_TALONSRX = 11;
    public static int CAN_DRIVETRAIN_RIGHT_VICTORSPX = 16;
    public static int CAN_DRIVETRAIN_LEFT_TALONSRX = 10;
    public static int CAN_DRIVETRAIN_LEFT_VICTORSPX = 15;
    public static int CAN_SHOOTER_TALONSRX = 36;
    public static int CAN_CLIMB_MAIN_TALONSRX = -1;
    public static int CAN_CLIMB_BALANCE_TALONSRX = -1;
    public static int CAN_STORAGE_TALONSRX = -1;
    public static int CAN_INTAKE_TALONSRX = -1;
    public static int CAN_SPINNER_TALONSRX = -1;

    public static double ENCODER_RESOLUTION = 4096;
    public static int TALON_TIMEOUT_MS = 5;

    public static double DRIVETRAIN_TRACKWIDTH_METERS = 0.606425;
    public static double DRIVETRAIN_MAXIMUM_CRUISE_SPEED_METERS_PER_SECOND = 8;
    //For some reason the thing below is broken :(
    public static double DRIVETRAIN_ENCODER_DISTANCE_TO_METERS = 1f / ENCODER_RESOLUTION * 18.85f / 2.54f / 100f;//1 rotation is 4096 encoder units, encoder is on drivetrain, 6 inch wheels
    public static double DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND = DRIVETRAIN_ENCODER_DISTANCE_TO_METERS * 10f;

    public static double SHOOTER_ENCODER_VELOCITY_TO_METERS_PER_SECOND = 0;


    public static TalonSRXGains DRIVETRAIN_LEFT_FPID = new TalonSRXGains(0.3, 0.5, 0, 15);
    public static TalonSRXGains DRIVETRAIN_RIGHT_FPID = new TalonSRXGains(0.3, 0.5, 0, 15);

    //The following two could possibly just be normal PID values
    public static TalonSRXGains LIMELIGHT_SHOOTER_FPID = new TalonSRXGains(0, 0, 0, 0);
    public static TalonSRXGains LIMELIGHT_DRIVETRAIN_FPID = new TalonSRXGains(0, 0, 0, 0);

    public static double LIMELIGHT_SHOOTER_KP = 0.5;
    public static double LIMELIGHT_SHOOTER_KI = 0;
    public static double LIMELIGHT_SHOOTER_KD = 0;

    public static double LIMELIGHT_DRIVETRAIN_KP = 0.5;
    public static double LIMELIGHT_DRIVETRAIN_KI = 0;
    public static double LIMELIGHT_DRIVETRAIN_KD = 0;
}
