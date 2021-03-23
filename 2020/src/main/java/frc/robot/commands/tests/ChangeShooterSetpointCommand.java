package frc.robot.commands.tests;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.vision.TunerState;


public class ChangeShooterSetpointCommand extends InstantCommand {

    private double delta;

    public ChangeShooterSetpointCommand(double d) {
        delta = d;
    }

    @Override
    public void initialize() {
        TunerState.INSTANCE.changeSetpoint(delta);
    }
}
