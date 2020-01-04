package frc.robot.path;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

public class PathBuilder {
    //For field element line segments, corners are not included as overlaps, so the line segments MUST overlap slightly.
    private static Segment2d[] m_fieldElements;

    //Do this in separate thread
    public static Trajectory generatePath(Translation2d start, Translation2d end, boolean reversed) {
        ArrayList<Segment2d> route = new ArrayList<>();
        route.add(new Segment2d(start, end));
        //Find shortest path between two points
        route = findShortestPath(new boolean[m_fieldElements.length * 2], route, 0);

        //Use temp arraylist to create spline path
        return null;
    }


    /*
    NEW PLAN:
    Rather than routing through the corner, route through another point that's also read.
    If a point has already been passed though, just return empty list because otherwise it'll go back and forth (and remember to set the empty list's weight to default to infinity)
    After all of the finding is done, if there are any convex sections in the path check to see if it can be simplified
    //Rather than only checking one intersecting segment, check all of them (increases processing time, but the convex path checking part would be unnecessary.)
    */
    private static ArrayList<Segment2d> findShortestPath(boolean[] visited, ArrayList<Segment2d> route, int index) {
        if (route.isEmpty()) return route;
        Segment2d cur = route.get(index);
        double minLength = Double.MAX_VALUE;
        ArrayList<Segment2d> minRoute = new ArrayList<>();
        boolean isFinal = true;
        for (int i = 0; i < m_fieldElements.length; i++) {
            Segment2d element = m_fieldElements[i];
            if (element.intersects(cur)) {
                isFinal = false;
                //Reroute path through each endpoint of e and find new path length, whichever one is shorter is "correct"

                //Paths a and b are the two possible new paths, with A going through e.m_start and B going through e.m_end
                //Front is the portion connecting the start to the new point, and Rear is the portion connecting the new point to the destination
                Segment2d pathAFront, pathARear, pathBFront, pathBRear;
                pathAFront = new Segment2d(cur.getStart(), element.getAltStart());
                pathBFront = new Segment2d(cur.getStart(), element.getAltEnd());
                pathARear = new Segment2d(element.getAltStart(), cur.getEnd());
                pathBRear = new Segment2d(element.getAltEnd(), cur.getEnd());
                

                //Copy old route into new arraylists
                ArrayList<Segment2d> routeA = new ArrayList<>(route), routeB = new ArrayList<>(route);

                Collections.copy(routeA, route);
                Collections.copy(routeB, route);

                //Find shortest legal route through A
                if (! visited[i * 2]) {
                    routeA.remove(cur);
                    routeA.add(index, pathARear);
                    routeA.add(index, pathAFront);
                    visited[i * 2] = true;
                    routeA = findShortestPath(visited, routeA, index);
                    routeA = findShortestPath(visited, routeA, index + 1);
                    visited[i * 2] = false;
                }

                //Find shortest legal route through B
                if (! visited[i * 2 + 1]) {
                    routeB.remove(cur);
                    routeB.add(index, pathBRear);
                    routeB.add(index, pathBFront);
                    visited[i * 2 + 1] = true;
                    routeB = findShortestPath(visited, routeB, index);
                    routeB = findShortestPath(visited, routeB, index + 1);
                    visited[i * 2 + 1] = false;
                }

                //Find total length of each path
                double totalA = Double.MAX_VALUE, totalB = Double.MAX_VALUE;

                if (! visited[i * 2] && ! routeA.isEmpty()) {
                    totalA = 0;
                    for (Segment2d p : routeA) totalA += p.getLength();
                }
                if (! visited[i * 2 + 1] && ! routeB.isEmpty()) {
                    totalB = 0;
                    for (Segment2d p : routeB) totalB += p.getLength();
                }
                //Set minLength to shorter one
                if (totalA < minLength) {
                    minLength = totalA;
                    minRoute = routeA;
                }
                if (totalB < minLength) {
                    minLength = totalB;
                    minRoute = routeB;
                }
            }
        }

        return isFinal ? route : minRoute;
    }

    /*
    NEW PLAN: 
    All waypoints should be shared and segments should reference the ones they use
    */
    public static boolean loadFieldFromFile(String fileName) {
        try {
            Scanner s = new Scanner(new File(Filesystem.getDeployDirectory().toString() + "field_elements_2019.txt"));
            m_fieldElements = new Segment2d[s.nextInt()];
            for (int i = 0; i < m_fieldElements.length; i++) {
                m_fieldElements[i] = new Segment2d(new Translation2d(s.nextDouble(), s.nextDouble()), new Translation2d(s.nextDouble(), s.nextDouble()));
                m_fieldElements[i].setAltStart(new Translation2d(s.nextDouble(), s.nextDouble()));
                m_fieldElements[i].setAltEnd(new Translation2d(s.nextDouble(), s.nextDouble()));
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file " + fileName);
            return false;
        }
        return true;
    }
}
