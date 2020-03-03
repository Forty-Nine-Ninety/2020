package frc.robot.paths;

import java.io.IOException;
import java.nio.file.Path;

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

    private SendableChooser<String> m_positionChooser;
    private SendableChooser<Command> m_actionChooser;
    private DrivetrainSubsystem m_drive;
    private ShooterSubsystem m_shooter;
    private StorageSubsystem m_storage;

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

    }

    public void AutoChooser2() throws IOException {
        m_actionChooser.setDefaultOption("Move Only", new AutoDriveCommand(m_drive, getTrajectory("/paths/MoveOnly.wpilib.json")));
        m_actionChooser.addOption("Shoot, Move", new ShootMoveCommand(m_shooter, m_storage, m_drive, getTrajectory("/paths/ShootMove.wpilib.json")));
        m_actionChooser.addOption("Shoot, Get Balls", new ShootGetBallsCommand(m_shooter, m_storage, m_drive, getTrajectory("/paths/ShootGetBalls.wpilib.json")));

    }

    private Trajectory getTrajectory(String autoPath) throws IOException {
        String trajectoryJSON = autoPath;
        Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
        Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        return trajectory;
    }


}