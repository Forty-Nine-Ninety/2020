package frc.robot.paths;

import java.io.*;
import java.util.*;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;

public class PathBuilder {
    private static Quad2d[] m_obstacles;
    private static boolean m_initialized = false;

    //Do this in separate thread
    public static Trajectory generatePath(Translation2d start, Translation2d end, boolean reversed) {
        return null;
    }


    
    private static ArrayList<Segment2d> findShortestPath(boolean[] visited, ArrayList<Segment2d> route, int index) {
        return null;
    }

    public static void initialize() {
        try {
            if (! loadObstaclesFromFile("field_2020.dat")) return;
            m_initialized = true;
        }
        catch (Exception e) {

        }
    }

    public static boolean isInitialized() {
        return m_initialized;
    }

    public static boolean loadObstaclesFromFile(String fileName) throws IOException {
        System.out.println("Loading field obstacles from " + Filesystem.getDeployDirectory().toString() + fileName);
        Scanner s = new Scanner(new File(Filesystem.getDeployDirectory().toString() + fileName));
        int count = s.nextInt();
        m_obstacles = new Quad2d[count];
        for (int i = 0; i < count; i++) {
            m_obstacles[i] = new Quad2d(new Translation2d(s.nextDouble(), s.nextDouble()), new Translation2d(s.nextDouble(), s.nextDouble()), new Translation2d(s.nextDouble(), s.nextDouble()), new Translation2d(s.nextDouble(), s.nextDouble()));
        }
        s.close();
        return true;
    }
}
