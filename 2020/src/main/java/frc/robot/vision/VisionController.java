package frc.robot.vision;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.util.Units;


// https://www.wired.com/2012/01/projectile-motion-primer-for-first-robotics/
// http://docs.limelightvision.io/en/latest/cs_aimandrange.html

public class VisionController {

    private static Spline[] splines;
    private static ControlPoint[] controlPoints;

    public static void setControlPoints(ControlPoint[] points) {
        //TODO Check to make sure points are in order here

        controlPoints = points;
        computeSplines();
    }

    private static void computeSplines() {
        //TODO Compute splines from control points
    }

    public static double getShooterSpeedFromDistance(double dist) {
        //If the distance is a control point, return that.
        for (ControlPoint p : controlPoints) {
            if (dist == p.getDistance()) return p.getSpeed();
        }

        //Try to interpolate.
        double i = 0;
        for (Spline s : splines) {
            i = s.interpolate(dist);
            if (i != -1) return i;
        }

        //If we couldn't interpolate, it means that the distance isn't in the correct range.
        System.err.println("[ERROR] Couldn't interpolate shooter speed, distance out of range.");
        return 0;
    }

    public static double getShooterSpeedFromLimelight() {
        return getShooterSpeedFromDistance(findDistanceToTarget());
    }

    // http://docs.limelightvision.io/en/latest/cs_estimating_distance.html
    public static double findDistanceToTarget() {
        return (Vision.TARGET_HEIGHT_METERS - RobotMeasurements.LIMELIGHT_HEIGHT_METERS) / Math.tan(findAngleToTarget());
    }

    public static double findAngleToTarget() {
        return RobotMeasurements.LIMELIGHT_ANGLE_RADIANS + Units.degreesToRadians(Limelight.getCrosshairVerticalOffset());
    }

    private class Spline {
        private double a, b, c, d;
        private double xi;
        private double xmin, xmax;

        public Spline(double xmin, double xmax, double a, double b, double c, double d, double xi) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.xi = xi;
            this.xmin = xmin;
            this.xmax = xmax;
        }

        public double interpolate(double x) {
            if (x <= xmin || x >= xmax) return -1;
            x -= xi;
            return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
        }
    }
}
