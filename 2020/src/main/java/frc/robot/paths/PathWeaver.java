package frc.robot.paths;

import java.io.IOException;
import java.nio.file.Path;

import io.github.oblarg.oblog.annotations.Config;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutoDriveCommand;
import frc.robot.commands.ShootGetBallsCommand;
import frc.robot.commands.ShootMoveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;

public class PathWeaver {

    private final SendableChooser<String> m_positionChooser;
    private final SendableChooser<Command> m_actionChooser;
    private String m_ballLocation;
    private final DrivetrainSubsystem m_drive;
    private final ShooterSubsystem m_shooter;
    private final StorageSubsystem m_storage;

    public PathWeaver(SendableChooser<String> posChooser, SendableChooser<Command> actChooser,
            DrivetrainSubsystem drive, ShooterSubsystem shooter, StorageSubsystem storage) {
        m_positionChooser = posChooser;
        m_actionChooser = actChooser;
    }

    public void AutoChooser1() {
        //TODO add code using starting position info
        m_positionChooser.setDefaultOption("Left", "left");
        m_positionChooser.addOption("Center", "center");
        m_positionChooser.addOption("Right", "right");
        getStartingPosition();

    }

    public void AutoChooser2() throws IOException {
        m_actionChooser.setDefaultOption("Move Only", new AutoDriveCommand(m_drive, getTrajectory("/paths/MoveOnly.wpilib.json")));
        m_actionChooser.addOption("Shoot, Move", new ShootMoveCommand(m_shooter, m_storage, m_drive, getTrajectory("/paths/ShootMove.wpilib.json")));
        m_actionChooser.addOption("Shoot, Get Balls", new ShootGetBallsCommand(m_shooter, m_storage, m_drive, getTrajectory(m_BallLocation)));

    }

    private Trajectory getTrajectory(String autoPath) throws IOException {
        String trajectoryJSON = autoPath;
        Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
        Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        return trajectory;
    }

    private void getStartingPosition(){
       
        switch (m_positionChooser.getSelected()){
            case "left":
                m_ballLocation = "/paths/ShieldGen.wpilib.json";//goes to shield gen to get ball
                break;
            case "center":
                m_ballLocation = "/paths/ShieldGen.wpilib.json";//same as above
                break;
            case "right":
                m_ballLocation = "/paths/TrenchRun.wpilib.json";//goes to trench to get ball
                break;
        }
    }

    //TODO add oblog ToggleButton when added to robot container
    public void runAction(boolean run){
        if (run) m_actionChooser.getSelected().schedule();
        else m_actionChooser.getSelected().cancel();
    }


}