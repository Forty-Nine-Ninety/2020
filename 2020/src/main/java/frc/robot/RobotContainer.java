package frc.robot;

import frc.robot.JoystickF310.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.ClimbSubsystem.Side;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class RobotContainer {

    private final JoystickF310 joystickDrive = new JoystickF310(Constants.PORT_JOYSTICK_DRIVE);
    private final JoystickF310 joystickOperator = new JoystickF310(Constants.PORT_JOYSTICK_OPERATOR);

    private final HatchSubsystem m_hatch = new HatchSubsystem();
    private final HatchBeakSubsystem m_hatchBeak = new HatchBeakSubsystem();
    private final CargoSubsystem m_cargo = new CargoSubsystem();
    private final TurretSubsystem m_turret = new TurretSubsystem();
    private final ClimbSubsystem m_climbFront = new ClimbSubsystem(Side.Front);
    private final ClimbSubsystem m_climbRear = new ClimbSubsystem(Side.Rear);
    private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();

    private final ExtendHatchCommand m_extendHatchCommand = new ExtendHatchCommand(m_hatch);
    private final RetractHatchCommand m_retractHatchCommand = new RetractHatchCommand(m_hatch);
    
    private final ExtendHatchBeakCommand m_extendHatchBeakCommand = new ExtendHatchBeakCommand(m_hatchBeak);
    private final RetractHatchBeakCommand m_retractHatchBeakCommand = new RetractHatchBeakCommand(m_hatchBeak);

    //TODO Add cargo commands
    private final TeleopCargoTurnCommand m_teleopCargoTurnCommand = new TeleopCargoTurnCommand(m_cargo);

    private final TeleopTurretTurnCommand m_teleopTurretTurnCommand = new TeleopTurretTurnCommand(m_turret);
    private final SetpointTurretTurnCommand m_setpointTurretTurnCommandA = new SetpointTurretTurnCommand(m_turret, 180);
    private final SetpointTurretTurnCommand m_setpointTurretTurnCommandB = new SetpointTurretTurnCommand(m_turret, 90);
    private final SetpointTurretTurnCommand m_setpointTurretTurnCommandX = new SetpointTurretTurnCommand(m_turret, -90);
    private final SetpointTurretTurnCommand m_setpointTurretTurnCommandY = new SetpointTurretTurnCommand(m_turret, 0);

    private final ExtendClimbCommand m_extendClimbFrontCommand = new ExtendClimbCommand(m_climbFront);
    private final RetractClimbCommand m_retractClimbFrontCommand = new RetractClimbCommand(m_climbRear);
    private final ExtendClimbCommand m_extendClimbRearCommand = new ExtendClimbCommand(m_climbFront);
    private final RetractClimbCommand m_retractClimbRearCommand = new RetractClimbCommand(m_climbRear);

    //TODO Add drivetrain commands
    private final TeleopTankDriveCommand m_teleopTankDriveCommand = new TeleopTankDriveCommand(m_drivetrain);
    


    public RobotContainer() {
        configureButtonBindings();
        configureDefaultCommands();
    }

    private void configureButtonBindings() {

        joystickOperator.getButton(ButtonF310.BumperLeft).whenPressed(m_extendHatchCommand);
        joystickOperator.getButton(ButtonF310.BumperRight).whenPressed(m_retractHatchCommand);

        joystickOperator.getButton(POVF310.Top).whenPressed(m_extendHatchBeakCommand);
        joystickOperator.getButton(POVF310.Right).whenPressed(m_retractHatchBeakCommand);

        m_teleopCargoTurnCommand.setSupplier(() -> joystickOperator.getRawAxis(AxisF310.JoystickRightY));
        m_teleopTurretTurnCommand.setSupplier(() -> joystickOperator.getRawAxis(AxisF310.JoystickLeftX));

        joystickOperator.getButton(ButtonF310.A).whenPressed(m_setpointTurretTurnCommandA);
        joystickOperator.getButton(ButtonF310.B).whenPressed(m_setpointTurretTurnCommandB);
        joystickOperator.getButton(ButtonF310.X).whenPressed(m_setpointTurretTurnCommandX);
        joystickOperator.getButton(ButtonF310.Y).whenPressed(m_setpointTurretTurnCommandY);

        joystickOperator.getButton(POVF310.TopLeft).whenPressed(m_extendClimbFrontCommand);
        joystickOperator.getButton(POVF310.TopRight).whenPressed(m_extendClimbRearCommand);

        joystickOperator.getButton(POVF310.BottomLeft).whenPressed(m_retractClimbFrontCommand);
        joystickOperator.getButton(POVF310.BottomRight).whenPressed(m_retractClimbRearCommand);

        //TODO add drivetrain command bindings (?)
        m_teleopTankDriveCommand.setSuppliers(() -> joystickDrive.getRawAxis(AxisF310.JoystickLeftY), () -> joystickDrive.getRawAxis(AxisF310.JoystickRightY));
    }

    private void configureDefaultCommands() {
        CommandScheduler.getInstance().setDefaultCommand(m_drivetrain, m_teleopTankDriveCommand);
        CommandScheduler.getInstance().setDefaultCommand(m_cargo, m_teleopCargoTurnCommand);
        CommandScheduler.getInstance().setDefaultCommand(m_turret, m_teleopTurretTurnCommand);
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
