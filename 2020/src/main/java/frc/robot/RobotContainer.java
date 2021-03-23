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

    public final RunType runType = RunType.TuneShooter;

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

    //Test Commands
    private final TestBallProcessCommand m_testBallProcessCommand = new TestBallProcessCommand(m_hopper, m_intake, m_storage);
    private final TestShooterCommand m_testShooterCommand = new TestShooterCommand(m_shooter);
    private final TeleopBadArcadeDriveCommand m_TeleopBadArcadeDriveCommand = new TeleopBadArcadeDriveCommand(m_drivetrain);

    public RobotContainer() {
        switch(runType)  {
            case Drive:
                configureControlBindings();
                configureDefaultCommands();
                break;

            case Test:
                configureTestBindings();
                configureDefaultCommands();
                break;

            case TuneShooter:
                configureTuneBindings();
                break;
        }
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
            () -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), JOYSTICK_INPUT_EXPONENT),
            () -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickRightX), JOYSTICK_INPUT_EXPONENT)
            );

        //Hopper

        //Intake
        joystickOperator.getButton(ButtonF310.Y).toggleWhenPressed(m_runForwardIntakeCommand);
        joystickOperator.getButton(ButtonF310.A).toggleWhenPressed(m_runReverseIntakeCommand);

        //Inserter

        //Shooter

        //Storage
    }

    private void configureTestBindings() {

        // Start/Stop Compressor button
        joystickOperator.getButton(ButtonF310.Start).whenPressed(new InstantCommand(() -> m_compressor.setClosedLoopControl(! m_compressor.getClosedLoopControl())));

        //Drivetrain: DONE, except constants are strange.
        m_TeleopBadArcadeDriveCommand.setSuppliers(
            () -> Util.toAlternateInput(joystickDrive.getRawAxis(AxisF310.JoystickLeftY)),
            () -> Util.toAlternateInput(joystickDrive.getRawAxis(AxisF310.JoystickRightX))
        );
        
        //Hopper: DONE
        joystickOperator.getButton(ButtonF310.Y).toggleWhenPressed(m_runForwardHopperCommand);
        joystickOperator.getButton(ButtonF310.A).toggleWhenPressed(m_runReverseHopperCommand);

        //Intake: DONE
        joystickOperator.getButton(ButtonF310.B).toggleWhenPressed(m_runForwardIntakeCommand);
        joystickOperator.getButton(ButtonF310.X).toggleWhenPressed(m_runReverseIntakeCommand);

        //Inserter: DONE
        joystickOperator.getButton(POVF310.Top).toggleWhenPressed(m_runForwardInserterCommand);
        joystickOperator.getButton(POVF310.Bottom).toggleWhenPressed(m_runReverseInserterCommand);

        //Shooter
        joystickOperator.getButton(ButtonF310.BumperLeft).toggleWhenPressed(m_testShooterCommand);

        //Storage: DONE
        joystickOperator.getButton(POVF310.Right).toggleWhenPressed(m_runForwardStorageCommand);
        joystickOperator.getButton(POVF310.Left).toggleWhenPressed(m_runReverseStorageCommand);
        
        //Unjamming
        joystickOperator.getButton(ButtonF310.Start).toggleWhenPressed(m_unjamStorageCommand);
        joystickOperator.getButton(ButtonF310.Back).toggleWhenPressed(m_testBallProcessCommand);
    }

    private void configureTuneBindings() {
        joystickOperator.getButton(ButtonF310.A).toggleWhenPressed(new TuneShooterCommand(m_shooter));
        joystickOperator.getButton(POVF310.Top).whenPressed(new ChangeShooterSetpointCommand(100));
        joystickOperator.getButton(POVF310.Bottom).whenPressed(new ChangeShooterSetpointCommand(-100));
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

    private enum RunType {
        Drive,
        Test,
        TuneShooter
    }
}
