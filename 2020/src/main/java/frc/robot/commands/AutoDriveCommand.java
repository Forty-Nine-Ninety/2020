package frc.robot.commands;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.BiConsumer;

import static frc.robot.Constants.*;
import frc.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;


public class AutoDriveCommand extends RamseteCommand {
     
    DrivetrainSubsystem m_drive;

    public AutoDriveCommand(DrivetrainSubsystem drive) {

        super(
            PathBuilder.generatePath(),
            () -> m_drive.getPose(),
            new RamseteController(),
            drive.getKinematics(),
            drive.getOutputMetersPerSecond(),
            drive
        );

        m_drive = drive;

    }

    public Command doAuto() throws IOException{
        // Create a voltage constraint to ensure we don't accelerate too fast
        DifferentialDriveVoltageConstraint autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(KS_VOLTS, KV_VOLT_SECONDS_PER_METER, KA_VOLT_SECONDS_SQUARED_PER_METER),
            K_DRIVE_KINEMATICS,
            10);
        // Create config for trajectory
        TrajectoryConfig config =
        new TrajectoryConfig(K_MAX_SPEED_METERS_PER_SECOND, K_MAX_ACCELERATION_METERS_PER_SECOND_SQUARED)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(K_DRIVE_KINEMATICS)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);
        
        String trajectoryJSON = "paths/YourPath.wpilib.json";
        Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
        Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        RamseteCommand ramseteCommand = new RamseteCommand(
            trajectory,
            () -> m_drive.getPose(),
            new RamseteController(),
            m_drive.getKinematics(),
            m_drive.getOutputMetersPerSecond(),
            m_drive
        );

        // Run path following command, then stop at the end.
        return ramseteCommand;
    }

    @Override
    public void execute(){
    }
}