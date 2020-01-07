package frc.robot.vision;

import static frc.robot.vision.Constants.*;

import edu.wpi.first.wpilibj.util.Units;


// https://www.wired.com/2012/01/projectile-motion-primer-for-first-robotics/
// http://docs.limelightvision.io/en/latest/cs_aimandrange.html

public class TargetFinder {

    public TargetFinder() {
        //
    }

    
    public static double findShooterAngleToTarget(double velocity) {
        //Returns optimal firing angle given a velocity
        return 0;
    }

    //Estimates air resistance rather than finding its exact value
    public static double estimateShooterAngleToTarget(double velocity) {
        
        return 0;
    }

    // http://docs.limelightvision.io/en/latest/cs_estimating_distance.html
    public static double findDistanceToTarget() {
        return (TARGET_HEIGHT - LIMELIGHT_HEIGHT) / Math.tan(findAngleToTarget());
    }

    public static double findAngleToTarget() {
        return Units.degreesToRadians(LIMELIGHT_ANGLE_DEGREES) + Units.degreesToRadians(Limelight.getCrosshairVerticalOffset());
    }
}
