package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class DrivetrainReverseCommand extends InstantCommand {

    private final DrivetrainSubsystem m_drive;

    public DrivetrainReverseCommand(DrivetrainSubsystem drive) {
        m_drive = drive;
    }

    @Override
    public void initialize() {
        m_drive.setReversed(! m_drive.isReversed());
    }

}
