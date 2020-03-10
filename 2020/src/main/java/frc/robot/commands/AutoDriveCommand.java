package frc.robot.commands;



import static frc.robot.Constants.*;
import frc.robot.subsystems.DrivetrainSubsystem;


import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;

import edu.wpi.first.wpilibj2.command.RamseteCommand;


public class AutoDriveCommand extends RamseteCommand {
     
    DrivetrainSubsystem m_drive;
    Trajectory m_autoPath;

    public AutoDriveCommand(DrivetrainSubsystem drive, Trajectory trajectory) {
        super(
            trajectory,
            () -> drive.getPose(),
            new RamseteController(K_RAMSETE_B, K_RAMSETE_Z),
            new SimpleMotorFeedforward(KS_VOLTS,
                                    KV_VOLT_SECONDS_PER_METER,
                                    KA_VOLT_SECONDS_SQUARED_PER_METER),
            drive.getKinematics(),
            ()  -> drive.getSpeeds(),
            new PIDController(DRIVETRAIN_LEFT_KP, DRIVETRAIN_LEFT_KI, DRIVETRAIN_LEFT_KD),
            new PIDController(DRIVETRAIN_RIGHT_KP, DRIVETRAIN_RIGHT_KI, DRIVETRAIN_RIGHT_KD),
            drive::drive,
            drive
        );
    }

    @Override
    public void execute(){
    }
}