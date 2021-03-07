package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.JoystickF310.*;
import frc.robot.commands.*;
import frc.robot.commands.tests.*;
import frc.robot.subsystems.*;
import frc.robot.vision.VisionController;

import static frc.robot.Constants.*;

import io.github.oblarg.oblog.Logger;
import io.github.oblarg.oblog.annotations.*;

//TODO rename constants with new command names (i.e. runhoppercommand)
public class RobotContainer {

    public final boolean TESTING = false;

    //Allow Oblog to find the Constants class
    private Constants constants;
    
    //Set up Joysticks
    private final JoystickF310 joystickDrive = new JoystickF310(Ports.PORT_JOYSTICK_DRIVE);
    private final JoystickF310 joystickOperator = new JoystickF310(Ports.PORT_JOYSTICK_OPERATOR);

    //Create subsystems
    private final ClimbSubsystem m_climb = new ClimbSubsystem();
    private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
    private final HopperSubsystem m_hopper = new HopperSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final InserterSubsystem m_inserter = new InserterSubsystem();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final StorageSubsystem m_storage  = new StorageSubsystem();

    private final Compressor m_compressor = new Compressor();

    //Create commands in the same order as the subsystems (alphabetical)
    //Climb

    //Drivetrain
    private final TeleopArcadeDriveCommand m_teleopArcadeDriveCommand = new TeleopArcadeDriveCommand(m_drivetrain);

    //Hopper
    private final RunHopperCommand m_runForwardHopperCommand =  new RunHopperCommand(m_hopper, Direction.Forward);
    private final RunHopperCommand  m_runReverseHopperCommand = new RunHopperCommand(m_hopper, Direction.Reverse);

    //Intake
    private final RunIntakeCommand m_runForwardIntakeCommand = new RunIntakeCommand(m_intake, Direction.Forward);
    private final RunIntakeCommand m_runReverseIntakeCommand = new RunIntakeCommand(m_intake, Direction.Reverse);

    //Inserter
    private final RunInserterCommand  m_runForwardInserterCommand = new RunInserterCommand(m_inserter, Direction.Forward);
    private final RunInserterCommand m_runReverseInserterCommand = new RunInserterCommand(m_inserter, Direction.Reverse);

    //Shooter

    //Storage
    private final RunStorageCommand m_runForwardStorageCommand = new RunStorageCommand(m_storage, Direction.Forward);
    private final RunStorageCommand m_runReverseStorageCommand = new RunStorageCommand(m_storage, Direction.Reverse);
    private final UnjamStorageCommand m_unjamStorageCommand = new UnjamStorageCommand(m_storage);

    public RobotContainer() {
        if (TESTING) configureTestBindings();
        else configureControlBindings();
        
        configureDefaultCommands();
        //Logger.configureLoggingAndConfig(this, false);

        //Set control points for shooter speed calculations
        VisionController.setControlPoints(Vision.CONTROL_POINTS);
    }

    private void configureControlBindings() {
        // Start/Stop Compressor button
        joystickOperator.getButton(ButtonF310.Start).whenPressed(new InstantCommand(() -> m_compressor.setClosedLoopControl(! m_compressor.getClosedLoopControl())));
        
        //Configure control in the same order as the subsystems (alphabetical)
        //Climb

        //Drivetrain
        m_teleopArcadeDriveCommand.setSuppliers(
            () -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickLeftY),JOYSTICK_INPUT_EXPONENT),
            () -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickRightX), JOYSTICK_INPUT_EXPONENT)
            );

        //Hopper

        //Intake
        joystickOperator.getButton(ButtonF310.Y).toggleWhenPressed(m_runForwardIntakeCommand);
        joystickOperator.getButton(ButtonF310.A).toggleWhenPressed(m_runReverseIntakeCommand);

        //Inserter

        //Shooter

        //Storage


        /* OLD CODE BELOW
        joystickOperator.getButton(ButtonF310.BumperRight).toggleWhenPressed(m_extendClimbCommand);
        joystickOperator.getButton(ButtonF310.BumperLeft).toggleWhenPressed(m_retractClimbCommand);

        joystickOperator.getButton(ButtonF310.Y).toggleWhenPressed(m_hopperManualCommandFwd);
        joystickOperator.getButton(ButtonF310.Back).toggleWhenPressed(m_hopperManualCommandRev);
        
        m_elevatorTestCommand.setSuppliers(() -> joystickOperator.getRawAxis(AxisF310.JoystickLeftY), () -> joystickOperator.getRawAxis(AxisF310.JoystickRightX));
        */
    }

    private void configureTestBindings() {
        //Hopper
        joystickOperator.getButton(ButtonF310.Y).toggleWhenPressed(m_runForwardHopperCommand);
        joystickOperator.getButton(ButtonF310.A).toggleWhenPressed(m_runReverseHopperCommand);

        //Intake
        joystickOperator.getButton(ButtonF310.B).toggleWhenPressed(m_runForwardIntakeCommand);
        joystickOperator.getButton(ButtonF310.X).toggleWhenPressed(m_runReverseIntakeCommand);

        //Inserter
        joystickOperator.getButton(POVF310.Top).toggleWhenPressed(m_runForwardInserterCommand);
        joystickOperator.getButton(POVF310.Bottom).toggleWhenPressed(m_runReverseInserterCommand);

        //Shooter

        //Storage
        joystickOperator.getButton(POVF310.Right).toggleWhenPressed(m_runForwardIntakeCommand);
        joystickOperator.getButton(POVF310.Left).toggleWhenPressed(m_runReverseIntakeCommand);
        
        //Unjamming
        joystickOperator.getButton(ButtonF310.Start).toggleWhenPressed(m_unjamStorageCommand);
    }

    private void configureDefaultCommands() {
        CommandScheduler.getInstance().setDefaultCommand(m_drivetrain, m_teleopArcadeDriveCommand);
    }

    public Command getAutonomousCommand() {
        return null;
    }

    public void updateLoggerEntries() {
        Logger.updateEntries();
    }
}
