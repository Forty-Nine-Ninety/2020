package frc.robot.commands;

import static frc.robot.Constants.*;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FollowPathCommand extends CommandBase {

    public FollowPathCommand() throws IOException {
        Trajectory t = TrajectoryUtil.fromPathweaverJson(p);
        
    }

    @Override
    public void execute() {
    }

}
