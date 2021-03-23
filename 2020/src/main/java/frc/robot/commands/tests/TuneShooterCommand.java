package frc.robot.commands.tests;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.vision.TunerState;


public class TuneShooterCommand extends CommandBase {

    private final ShooterSubsystem m_shooter;

    public TuneShooterCommand(ShooterSubsystem shooter) {
        addRequirements(shooter);
        m_shooter = shooter;
    }

    public void end(boolean interrupted) {
        m_shooter.firePO(0);
        System.out.println("Stopping");
    }
    
    @Override
    public void execute() {
        m_shooter.fireRaw(TunerState.INSTANCE.getSetpoint());
        System.out.println((int)Math.round(TunerState.INSTANCE.getSetpoint()) + "\t" + m_shooter.isReady());
    }
    /*
    @Override
    public boolean isFinished() {
        return m_storage.getBallCount() == 0;
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setFireSpeed(0);
    }

    public boolean getStorageState() {
        //Make sure ball can be shot
        return m_shooter.isReady();
    }

    public void setSupplier(DoubleSupplier sp) {
        m_speed = sp;
    }
    */
}
