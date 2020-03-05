package frc.robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

    private Command m_autonomousCommand;

    private RobotContainer m_robotContainer;

    @Override
    public void robotInit() {
        m_robotContainer = new RobotContainer();

        //Splash text!
        try {
            Scanner s = new Scanner(new File(Filesystem.getDeployDirectory().toString() + "/splash.txt"));
            ArrayList<String> splashes = new ArrayList<>();
            Random r = new Random();
            while (s.hasNextLine()) splashes.add(s.nextLine());
            System.out.println("\n\n" + splashes.get(r.nextInt(splashes.size())) + "\n\n");
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find splash messages :(");
        }
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        m_robotContainer.updateLoggerEntries();
    }

    @Override
    public void disabledInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void disabledPeriodic() {}

    @Override
    public void autonomousInit() {
        CommandScheduler.getInstance().cancelAll();
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
        Compressor test = new Compressor();
        //test.setClosedLoopControl(false);
    }

    @Override
    public void teleopPeriodic() {}

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {}
}
