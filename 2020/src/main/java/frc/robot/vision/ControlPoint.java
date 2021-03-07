package frc.robot.vision;

public class ControlPoint {
    private final double m_distance;
    private final double m_speed;

    public ControlPoint(double distance, double speed) {
        m_distance = distance;
        m_speed = speed;
    }

    public double getDistance() {
        return m_distance;
    }

    public double getSpeed() {
        return m_speed;
    }

    double getX() {
        return m_distance;
    }

    double getY() {
        return m_speed;
    }

    public String toString() {
        return "(" + m_distance + ", " + m_speed + ")";
    }
}