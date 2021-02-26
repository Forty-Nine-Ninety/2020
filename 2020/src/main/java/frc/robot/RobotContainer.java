package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.JoystickF310.*;
import frc.robot.commands.*;
import frc.robot.commands.tests.*;
import frc.robot.subsystems.*;

import static frc.robot.Constants.*;

import io.github.oblarg.oblog.Logger;
import io.github.oblarg.oblog.annotations.*;

//TODO rename constants with new command names (i.e. runhoppercommand)
public class RobotContainer {

    //Allow Oblog to find the Constants class
    private Constants constants;
    
    //Set up Joysticks
    private final JoystickF310 joystickDrive = new JoystickF310(PORT_JOYSTICK_DRIVE);
    private final JoystickF310 joystickOperator = new JoystickF310(PORT_JOYSTICK_OPERATOR);

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

    //Intake
    private final RunIntakeCommand m_runForwardIntakeCommand = new RunIntakeCommand(m_intake, Direction.Forward);
    private final RunIntakeCommand m_runReverseIntakeCommand = new RunIntakeCommand(m_intake, Direction.Reverse);

    //Inserter

    //Shooter

    //Storage

    /* OLD CODE BELOW
    //private final ClimbBalanceCommand m_climbBalanceCommand = new ClimbBalanceCommand(m_climb, m_drivetrain);
    private final ExtendClimbCommand m_extendClimbCommand = new ExtendClimbCommand(m_climb);
    private final RetractClimbCommand m_retractClimbCommand = new RetractClimbCommand(m_climb);
    private final FollowPathCommand m_followPathCommand = new FollowPathCommand();
    //private final LimelightShootBallCommand m_limelightShootBallCommand = new LimelightShootBallCommand(m_shooter, m_storage, m_drivetrain);
    //private final RunStorageCommand m_runStorageCommand = new RunStorageCommand(m_storage, m_hopper);
    
    private final TestElevatorCommand m_elevatorTestCommand = new TestElevatorCommand(m_climb);

    private final TestBallCommand m_BallMotorsCommand = new TestBallCommand(m_shooter, m_hopper);
    */

    public RobotContainer() {
        configureControlBindings();
        configureDefaultCommands();
        //Logger.configureLoggingAndConfig(this, false);
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
