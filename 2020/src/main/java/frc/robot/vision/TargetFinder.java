package frc.robot.vision;

import static frc.robot.vision.Constants.*;
import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.util.Units;


// https://www.wired.com/2012/01/projectile-motion-primer-for-first-robotics/
// http://docs.limelightvision.io/en/latest/cs_aimandrange.html

public class TargetFinder {

    public TargetFinder() {
        //nothing here yet!
    }

    
    public static double estimateShooterVelocityToTarget() {
        //Returns optimal firing angle given a velocity
        //See https://physics.stackexchange.com/questions/27992/solving-for-initial-velocity-required-to-launch-a-projectile-to-a-given-destinat for explanation
        //y0 in our case is negative because final target is up, not down.
        double d = findDistanceToTarget(), h = TARGET_HEIGHT_METERS - SHOOTER_HEIGHT_METERS;
        double denominator = 2 * (d * Math.tan(SHOOTER_ANGLE_RADIANS) - h) * Math.pow(Math.cos(SHOOTER_ANGLE_RADIANS), 2);
        return Math.sqrt(GRAVITY * Math.pow(d, 2) / denominator);
    }


    // http://docs.limelightvision.io/en/latest/cs_estimating_distance.html
    public static double findDistanceToTarget() {
        return (TARGET_HEIGHT_METERS - LIMELIGHT_HEIGHT_METERS) / Math.tan(findAngleToTarget());
    }

    public static double findAngleToTarget() {
        return LIMELIGHT_ANGLE_RADIANS + Units.degreesToRadians(Limelight.getCrosshairVerticalOffset());
    }
}
