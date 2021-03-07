package frc.robot.vision;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.util.Units;


// https://www.wired.com/2012/01/projectile-motion-primer-for-first-robotics/
// http://docs.limelightvision.io/en/latest/cs_aimandrange.html

public class VisionController {

    private static Spline[] splines;
    private static ControlPoint[] controlPoints;

    public static void setControlPoints(ControlPoint[] points) {
        double last = Integer.MIN_VALUE;
        for (ControlPoint p : points) {
            if (p.getDistance() <= last) {
                System.err.println("[ERROR] Control points not in increasing order");
                return;
            }
            last = p.getDistance();
        }
        controlPoints = points;
        computeSplines();
    }

    private static void computeSplines() {
        int n = controlPoints.length - 1;
        double[][] matrix = new double[n * 4][n * 4 + 1];

        //Convert control points to matrix
        for (int i = 0; i < n; i++) {
            matrix[i * 2][i * 4 + 0] = Math.pow(controlPoints[i].getX(), 3);
            matrix[i * 2][i * 4 + 1] = Math.pow(controlPoints[i].getX(), 2);
            matrix[i * 2][i * 4 + 2] = controlPoints[i].getX();
            matrix[i * 2][i * 4 + 3] = 1;

            matrix[i * 2][n * 4] = controlPoints[i].getY();

            matrix[i * 2 + 1][i * 4 + 0] = Math.pow(controlPoints[i + 1].getX(), 3);
            matrix[i * 2 + 1][i * 4 + 1] = Math.pow(controlPoints[i + 1].getX(), 2);
            matrix[i * 2 + 1][i * 4 + 2] = controlPoints[i + 1].getX();
            matrix[i * 2 + 1][i * 4 + 3] = 1;

            matrix[i * 2 + 1][n * 4] = controlPoints[i + 1].getY();
        }

        //First derivative of control points
        for (int i = 0; i < n - 1; i++) {
            int index = i + n * 2;

            matrix[index][i * 4 + 0] = 3 * Math.pow(controlPoints[i + 1].getX(), 2);
            matrix[index][i * 4 + 1] = 2 * controlPoints[i + 1].getX();
            matrix[index][i * 4 + 2] = 1;

            matrix[index][i * 4 + 4] = -3 * Math.pow(controlPoints[i + 1].getX(), 2);
            matrix[index][i * 4 + 5] = -2 * controlPoints[i + 1].getX();
            matrix[index][i * 4 + 6] = -1;

        }

        //Second derivative of control points
        for (int i = 0; i < n - 1; i++) {
            int index = n * 3 - 1 + i;

            matrix[index][i * 4 + 0] = 6 * controlPoints[i + 1].getX();
            matrix[index][i * 4 + 1] = 2;

            matrix[index][i * 4 + 4] = -6 * controlPoints[i + 1].getX();
            matrix[index][i * 4 + 5] = -2;
        }

        //Endpoints
        int index = n * 4 - 2;
        matrix[index][0] = controlPoints[0].getX();
        matrix[index][1] = 2;

        index++;
        matrix[index][(n - 1) * 4 + 0] = 6 * controlPoints[n].getX();
        matrix[index][(n - 1) * 4 + 1] = 2;

        //Solve for equations
        matrix = MatrixOperations.rref(matrix);

        //Move results to splines
        splines = new Spline[n];
        for (int i = 0; i < n; i++) {
            splines[i] = new Spline(controlPoints[i].getX(), controlPoints[i + 1].getX(),
                    matrix[i * 4 + 0][n * 4], matrix[i * 4 + 1][n * 4], matrix[i * 4 + 2][n * 4], matrix[i * 4 + 3][n * 4]);
        }
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

    private static class Spline {
        private double a, b, c, d;
        private double xmin, xmax;

        public Spline(double xmin, double xmax, double a, double b, double c, double d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.xmin = xmin;
            this.xmax = xmax;
        }

        public double interpolate(double x) {
            if (x <= xmin || x >= xmax) return -1;
            return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
        }

        public String toString() {
            return "[" + xmin + ", " + xmax + "] " + a + " * x ^ 3" + " + " + b + " * x ^ 2" + " + " + c + " * x + " + d;
        }
    }
}
