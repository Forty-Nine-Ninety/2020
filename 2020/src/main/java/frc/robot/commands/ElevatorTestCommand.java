package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

public class ElevatorTestCommand extends CommandBase {

    private final ClimbSubsystem m_climb;
    private BooleanSupplier m_setLockSupplier;
    private IntSupplier m_climbPositionSupplier;

    public ElevatorTestCommand(ClimbSubsystem climb) {
        addRequirements(climb);
        m_climb = climb;
    }

    @Override
    public void execute() {
        m_climb.climb(m_climbPositionSupplier.getAsInt());
        m_climb.setLock(m_setLockSupplier.getAsBoolean());
    }

    public void setSuppliers(BooleanSupplier lock, IntSupplier climb) {
        m_setLockSupplier = lock;
        m_climbPositionSupplier = climb;
    }

    @Override
    public void end(boolean bool){
        m_climb.climb(0);
    }

}