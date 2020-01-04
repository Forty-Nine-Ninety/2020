package frc.robot.paths;

import edu.wpi.first.wpilibj.geometry.Translation2d;

public class Segment2d {
    private Translation2d m_start;
    private Translation2d m_altStart;
    private Translation2d m_end;
    private Translation2d m_altEnd;

    public Segment2d() {
        this(new Translation2d(), new Translation2d());
    }
    
    public Segment2d(Translation2d s, Translation2d e) {
        m_start = s;
        m_end = e;
    }

    public void setAltStart(Translation2d a) {
        m_altStart = a;
    }

    public void setAltEnd(Translation2d a) {
        m_altEnd = a;
    }

    public Translation2d getStart() {
        return m_start;
    }

    public Translation2d getEnd() {
        return m_end;
    }

    public Translation2d getAltStart() {
        return m_altStart == null ? m_start : m_altStart;
    }

    public Translation2d getAltEnd() {
        return m_altEnd == null ? m_end : m_altEnd;
    }

    public double getLength() {
        return m_start.getDistance(m_end);
    }

    public boolean intersects(Segment2d l) {
        return ((ccw(m_start, m_end, l.m_start) * ccw(m_start, m_end, l.m_end)) < 0) && ((ccw(l.m_start, l.m_end, m_start) * ccw(l.m_start, l.m_end, m_end)) < 0);
    }

    public boolean touches(Segment2d l) {
        // <= checks for touches as well, while < only checks for intersections
        return ((ccw(m_start, m_end, l.m_start) * ccw(m_start, m_end, l.m_end)) <= 0) && ((ccw(l.m_start, l.m_end, m_start) * ccw(l.m_start, l.m_end, m_end)) <= 0);
    }
    
    private int ccw(Translation2d p0, Translation2d p1, Translation2d p2) {
        double dx1 = p1.getX() - p0.getX(), dx2 = p2.getX() - p0.getX(), dy1 = p1.getY() - p0.getY(), dy2 = p2.getY() - p0.getY();
        if (dx1 * dy2 > dx2 * dy1) return 1;
        if (dx1 * dy2 < dx2 * dy1) return -1;
        if ((dx1 * dx2 < 0) || (dy1 * dy2 < 0)) return -1;
        if ((dx1 * dx1 + dy1 * dy1) < (dx2 * dx2 + dy2 * dy2)) return 1;
        return 0;
    }

}
