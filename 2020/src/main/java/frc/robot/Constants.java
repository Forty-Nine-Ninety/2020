package frc.robot;

public final class Constants {
    public static int PORT_JOYSTICK_DRIVE = 0;
    public static int PORT_JOYSTICK_OPERATOR = 1;

    public static int CAN_PCM = 12;
    public static int CAN_CARGO_TALONSRX = 36;
    public static int CAN_TURRET_TALONSRX = 35;
    public static int CAN_DRIVETRAIN_LEFT_REAR_TALONSRX = 32;
    public static int CAN_DRIVETRAIN_LEFT_FRONT_TALONSRX = 31;
    public static int CAN_DRIVETRAIN_RIGHT_REAR_TALONSRX = 34;
    public static int CAN_DRIVETRAIN_RIGHT_FRONT_TALONSRX = 33;
    
    public static int PORT_PCM_HATCH = 0;
    public static int PORT_PCM_CLIMB_REAR = 1;
    public static int PORT_PCM_CLIMB_FRONT = 2;
    public static int PORT_PCM_HATCH_BEAK = 3;


    public static int PORT_SENSOR_TURRET_1 = 0;
    public static int PORT_SENSOR_TURRET_2 = 1;
    

    public static double DRIVETRAIN_TRACKWIDTH_METERS = 0.606425;

    public static double TURRET_KP = 0.1;
    public static double TURRET_KI = 0;
    public static double TURRET_KD = 0;
    
    public static double TURRET_SETPOINT_KP = 0.1;
    public static double TURRET_SETPOINT_KI = 0;
    public static double TURRET_SETPOINT_KD = 0;
    
    public static double CARGO_KP = 0.1;
    public static double CARGO_KI = 0;
    public static double CARGO_KD = 0;

    public static double ENCODER_RESOLUTION = 4096;
    public static int TALON_TIMEOUT_MS = 5;
    public static double TURRET_UNITS_PER_REVOLUTION = 26000;
    public static double DEGREES_TO_TURRET_ENCODER = TURRET_UNITS_PER_REVOLUTION / 360;
    public static double DEGREES_TURRET_SAFE_POINT = -45;
    public static double DEGREES_TURRET_MIN = -95;
    public static double DEGREES_TURRET_MAX = -45;
}
