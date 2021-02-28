package frc.robot.vision;

import static frc.robot.vision.Constants.*;
import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.util.Units;


// https://www.wired.com/2012/01/projectile-motion-primer-for-first-robotics/
// http://docs.limelightvision.io/en/latest/cs_aimandrange.html

public class VisionShooterSpeedController {

    private Spline[] splines;

    public static void setControlPoints(ControlPoint[] points) {
        //TODO Check to make sure points are in order

        computeSpline(points);
    }

    private static void computeSpline(ControlPoint[] points) {
        //TODO Compute spline
    }

    public static double getSpeedFromDistance(double dist) {
        //TODO check if distance is in a control point

        //TODO if not, then interpolate.
        return 0;
    }

    public static double getSpeedFromLimelight() {
        return getSpeedFromDistance(findDistanceToTarget());
    }

    // http://docs.limelightvision.io/en/latest/cs_estimating_distance.html
    private static double findDistanceToTarget() {
        return (TARGET_HEIGHT_METERS - LIMELIGHT_HEIGHT_METERS) / Math.tan(findAngleToTarget());
    }

    private static double findAngleToTarget() {
        return LIMELIGHT_ANGLE_RADIANS + Units.degreesToRadians(Limelight.getCrosshairVerticalOffset());
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
            if (x <= xmin || x >= xmax) throw new IllegalArgumentException(x + " out of bounds for spline (" + xmin + ", " + xmax + ")");
            x -= xi;
            return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
        }
    }
}
