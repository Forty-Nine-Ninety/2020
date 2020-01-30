package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbBalanceCommand extends CommandBase {

    private final ClimbSubsystem m_climb;

    public ClimbBalanceCommand(ClimbSubsystem climb) {
        addRequirements(climb);
        m_climb = climb;
    }

    @Override
    public void execute() {
        //TODO
    }

}
