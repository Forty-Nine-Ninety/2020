package frc.robot.commands.tests;

import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

import java.util.function.DoubleSupplier;

public class TestClimbBalanceCommand extends CommandBase {

    private final ClimbSubsystem m_climb;
    private DoubleSupplier m_speed;

    public TestClimbBalanceCommand(ClimbSubsystem climb) {
        addRequirements(climb);
        m_climb = climb;
    }

    public void setSupplier(DoubleSupplier d) {
        m_speed = d;
    }

    @Override
    public void execute() {
        m_climb.runBalance(m_speed.getAsDouble());
    }

}
