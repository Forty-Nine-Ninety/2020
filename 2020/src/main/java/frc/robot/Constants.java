package frc.robot;

public final class Constants {
    public static int PORT_JOYSTICK_DRIVE = 0;
    public static int PORT_JOYSTICK_OPERATOR = 1;

    public static int DIO_BREAKBEAM = -1;

    //Below is format for analog sensors
    //public static int PWM_NAME = -1;
    public static int PCM_CLIMB = -1;

    public static int CAN_PCM = 1;
    public static int CAN_DRIVETRAIN_RIGHT_TALONSRX = 10;
    public static int CAN_DRIVETRAIN_RIGHT_VICTORSPX = 15;
    public static int CAN_DRIVETRAIN_LEFT_TALONSRX = 11;
    public static int CAN_DRIVETRAIN_LEFT_VICTORSPX = 16;
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
    public static double DRIVETRAIN_ENCODER_DISTANCE_TO_METERS = 0.10914037;//1 rotation is 4096 encoder units, 10.71:1 gear ratio, 6 inch wheels
    public static double DRIVETRAIN_ENCODER_VELOCITY_TO_METERS_PER_SECOND = DRIVETRAIN_ENCODER_DISTANCE_TO_METERS / 10;

    public static double SHOOTER_ENCODER_VELOCITY_TO_METERS_PER_SECOND = 0;

    public static double DRIVETRAIN_LEFT_KP = 0.5;
    public static double DRIVETRAIN_LEFT_KI = 0.1;
    public static double DRIVETRAIN_LEFT_KD = 0.1;
    public static double DRIVETRAIN_RIGHT_KP = 0.5;
    public static double DRIVETRAIN_RIGHT_KI = 0.1;
    public static double DRIVETRAIN_RIGHT_KD = 0.1;

    public static double LIMELIGHT_SHOOTER_KP = 0.5;
    public static double LIMELIGHT_SHOOTER_KI = 0.1;
    public static double LIMELIGHT_SHOOTER_KD = 0.1;

    public static double LIMELIGHT_DRIVETRAIN_KP = 0.5;
    public static double LIMELIGHT_DRIVETRAIN_KI = 0.1;
    public static double LIMELIGHT_DRIVETRAIN_KD = 0.1;
}
