package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Direction;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.InserterSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;

public class UnjamStorageCommand extends SequentialCommandGroup{
    
    private final HopperSubsystem m_hopper;
    private final StorageSubsystem m_storage;
    private final InserterSubsystem m_inserter;
    private final ShooterSubsystem m_shooter;

    public UnjamStorageCommand(HopperSubsystem hopper, StorageSubsystem storage, InserterSubsystem inserter, ShooterSubsystem shooter) {
        m_hopper = hopper;
        m_storage = storage;
        m_inserter = inserter;
        m_shooter = shooter;

        addCommands( {
            new ParallelCommandGroup (new RunShooterCommand(m_shooter), new RunInserterCommand(m_inserter, Direction.Forward)),
            new RunStorageCommand(m_storage, Direction.Forward),
            new WaitCommand(0.5);
            new RunStorageCommand(m_storage, Direction.Reverse),
        })
    }
    
}
