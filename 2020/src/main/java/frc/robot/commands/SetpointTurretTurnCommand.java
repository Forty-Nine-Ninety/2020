package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

//Realistically this could be done with a PIDCommand; I'm only doing this as practice so I know what's going on.
public class SetpointTurretTurnCommand extends CommandBase {

    private final TurretSubsystem m_turret;
    private PIDController m_PID;
    private double m_setpoint;

    public SetpointTurretTurnCommand(TurretSubsystem turret, double setpoint) {
        m_turret = turret;
        addRequirements(turret);

        m_PID = new PIDController(Constants.TURRET_SETPOINT_KP, Constants.TURRET_SETPOINT_KI, Constants.TURRET_SETPOINT_KD);

        m_setpoint = setpoint * Constants.DEGREES_TO_TURRET_ENCODER;
    }

    @Override
    public void execute() {
        m_turret.setSpeed(m_PID.calculate(m_turret.getPosition(), m_setpoint));
    }
}
