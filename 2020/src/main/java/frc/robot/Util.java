package frc.robot;

public class Util {
    
    public static double powCopySign(double a, double b) {
        return Math.copySign(Math.pow(a, b), a);
    }

    public static double toAlternateInput(double input) {
        if (input > 0) return 4 * powCopySign(input - 0.5, 3) + 0.5;
        return 4 * powCopySign(input + 0.5, 3) - 0.5;
    }

    public static double[] arcadeToTankDrive(double speed, double rot) {
        double left, right, maxSpeed;

        if (Math.abs(speed) > Math.abs(rot)) maxSpeed = speed;
        else maxSpeed = speed > 0 ? Math.abs(rot) : Math.abs(rot) * -1;

        if (speed > 0) {
            if (rot > 0) {
                left = maxSpeed;
                right = speed - rot;
            }
            else {
                left = speed + rot;
                right = maxSpeed;
            }
        }
        else {
            if (rot > 0) {
                left = speed + rot;
                right = maxSpeed;
            }
            else {
                left = maxSpeed;
                right = speed - rot;
            }
        }
        return new double[] {left, right};
    }
}