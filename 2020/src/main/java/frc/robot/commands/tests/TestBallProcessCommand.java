package frc.robot.commands.tests;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Direction;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import frc.robot.commands.*;

public class TestBallProcessCommand extends ParallelCommandGroup {

    private final HopperSubsystem m_hopper;
    private final IntakeSubsystem m_intake;
    private final StorageSubsystem m_storage;

    public TestBallProcessCommand(HopperSubsystem hopper, IntakeSubsystem intake, StorageSubsystem storage) {
        m_hopper = hopper;
        m_intake = intake;
        m_storage = storage;
        
        addCommands(
           new RunHopperCommand(m_hopper, Direction.Forward),
           new RunIntakeCommand(m_intake, Direction.Forward),
           new RunStorageCommand(m_storage, Direction.Forward)
        );
    }
    
}
