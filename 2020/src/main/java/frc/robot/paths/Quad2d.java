package frc.robot.paths;

import java.io.*;
import java.util.*;

import edu.wpi.first.wpilibj.geometry.Translation2d;

public class Quad2d {
    
    private Segment2d[] m_sides;

    public Quad2d(Translation2d p1, Translation2d p2, Translation2d p3, Translation2d p4) {
        m_sides = new Segment2d[] {new Segment2d(p1, p2), new Segment2d(p2, p3), new Segment2d(p3, p4), new Segment2d(p4, p1)};
    }
}
