package frc.robot.paths;

import java.io.*;
import java.util.*;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

public class PathBuilder {
    private static Segment2d[] m_fieldElements;

    //Do this in separate thread
    public static Trajectory generatePath(Translation2d start, Translation2d end, boolean reversed) {
        return null;
    }


    
    private static ArrayList<Segment2d> findShortestPath(boolean[] visited, ArrayList<Segment2d> route, int index) {
        return null;
    }

    public static boolean loadFieldFromFile(String fileName) {
        try {
            Scanner s = new Scanner(new File(Filesystem.getDeployDirectory().toString() + fileName));
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file " + fileName);
            return false;
        }
        return true;
    }
}
