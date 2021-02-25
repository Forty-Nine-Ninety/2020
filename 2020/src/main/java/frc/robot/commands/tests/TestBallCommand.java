package frc.robot.commands.tests;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class TestBallCommand extends CommandBase {

    private final ShooterSubsystem m_shooter;
    private final HopperSubsystem m_hopper;

    public TestBallCommand(ShooterSubsystem shooter, HopperSubsystem hopper){
        addRequirements(shooter);
        addRequirements(hopper);

        m_shooter = shooter;
        m_hopper = hopper;
    }

    @Override
    public void execute() {
    }


}
