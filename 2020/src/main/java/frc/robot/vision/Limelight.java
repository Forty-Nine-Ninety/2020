package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    public static boolean inRange() {
        return getCrosshairVerticalOffset() < 0 && getCrosshairVerticalOffset() > -1;
    }

    /**
     * Gets valid target
     * 
     * @return True if a valid target is found; false if otherwise.
     */
    public static boolean hasValidTarget() {
        return (getNetworkTableEntry("tv") == 1);
    }

    /**
     * Gets the horizontal offset from the crosshair to the target.
     * 
     * @return The horizontal offset from the crosshair to the target (-29.8 to 29.8 degrees)
     */
    public static double getCrosshairHorizontalOffset() {
        return getNetworkTableEntry("tx");
    }

    /**
     * Gets the vertical offset from the crosshair to the target.
     * 
     * @return The vertical offset from the crosshair to the target (-24.85 to 24.85 degrees)
     */
    public static double getCrosshairVerticalOffset() {
        return getNetworkTableEntry("ty");
    }

    /**
     * Returns the target area, a percentage of the screen filled by the target box
     * See this part of the documentation:
     * http://docs.limelightvision.io/en/latest/vision_pipeline_tuning.html#target-area
     * 
     * @return The target area (0% of image to 100% of image)
     */
    public static double getTargetArea() {
        return getNetworkTableEntry("ta");
    }

    /**
     * Returns the skew/rotation of a target. (basically direction filter) see here
     * for direction filter example
     * https://giant.gfycat.com/HalfUnselfishHarvestmen.gif
     * 
     * @return The skew or rotation (-90 degrees to 0 degrees)
     */
    public static double getSkew() {
        return getNetworkTableEntry("ts");
    }

    /**
     * Returns the pipeline's latency contribution for non-image capture
     * 
     * @return The pipeline’s latency contribution in milliseconds
     */
    public static int getLatency() {
        return (int) Math.round(getNetworkTableEntry("tl"));
    }

    /**
     * Returns the pipeline's minimum latency contribution for image capture
     * 
     * @return The pipeline’s minimum latency contribution in milliseconds
     */
    public static int getImageCaptureLatency() {
        return (int) Math.round(getNetworkTableEntry("tl")) + 11;// See http://docs.limelightvision.io/en/latest/networktables_api.html
    }

    /**
     * Sets the Limelight's LED mode.
     * 
     * @param m The new LED mode
     */
    public static void setLEDMode(LEDMode m) {
        int n = m.ordinal();
        if (n < 0 || n > 4) return;
        setNetworkTableEntry("ledMode", n);
    }

    /**
     * Gets the Limelight's LED state.
     * 
     * @return The Limelight's current LED mode
     */
    public static LEDMode getLEDMode() {
        return LEDMode.values()[(int)getNetworkTableEntry("ledMode")];
    }

    /**
     * Toggles the Limelight's LED mode between 0 and 1. If the current setting is 2
     * or 3, the code will change it to 1.
     */
    public static void toggleLEDMode() {
        switch (getLEDMode()) {
            case Off:
                setLEDMode(LEDMode.Default);
                break;
            default:// 0, 2, and 3
                setLEDMode(LEDMode.Off);
                break;
        }
    }

    /**
     * Sets the Limelight's pipeline.
     * 
     * @param n Pipeline number between 0..9 inclusive.
     */
    public static void setPipeline(int n) {
        if (n < 0 || n > 9) return;
        setNetworkTableEntry("pipeline", n);
    }

    /**
     * Gets the Limelight's pipeline.
     * 
     * @return The currently selected pipeline number
     */
    public static int getPipeline() {
        return (int) getNetworkTableEntry("getpipe");
    }

    /**
     * Sets the Limelight's camMode. (0 = Vision Processing, 1 = Driver Camera
     * (Increases exposure, disables vision processing))
     */
    public static void setCamMode(int n) {
        setNetworkTableEntry("camMode", n);
    }

    /**
     * Queries the NetworkTable
     * 
     * @param s The key to query for
     * @return The value in the NetworkTable
     */
    private static double getNetworkTableEntry(String s) {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry(s).getDouble(0);
    }

    /**
     * Sets a value in the NetworkTable
     * 
     * @param k The key to set
     * @param s The value to set the key to
     * @return The value in the NetworkTable
     */
    private static void setNetworkTableEntry(String k, double s) {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry(k).setNumber(s);
    }


    public static enum LEDMode { Default, Off, Blink, On }
}
