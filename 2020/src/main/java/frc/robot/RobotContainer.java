package frc.robot;

import frc.robot.JoystickF310.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import static frc.robot.Constants.*;
import io.github.oblarg.oblog.Logger;
import io.github.oblarg.oblog.annotations.*;

public class RobotContainer {

    private final JoystickF310 joystickDrive = new JoystickF310(PORT_JOYSTICK_DRIVE);
    private final JoystickF310 joystickOperator = new JoystickF310(PORT_JOYSTICK_OPERATOR);

    //private final ClimbSubsystem m_climb = new ClimbSubsystem();
    @Config.NumberSlider(name = "Multiplier", methodName = "setMultiplier", methodTypes = {double.class}, defaultValue = 1.1, min = 0, max = 2)
    private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
    //private final HopperSubsystem m_hopper = new HopperSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    //private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    //private final StorageSubsystem m_storage  = new StorageSubsystem();

    //private final ClimbBalanceCommand m_climbBalanceCommand = new ClimbBalanceCommand(m_climb, m_drivetrain);
    //private final ExtendClimbCommand m_extendClimbCommand = new ExtendClimbCommand(m_climb);
    //private final RetractClimbCommand m_retractClimbCommand = new RetractClimbCommand(m_climb);
    private final FollowPathCommand m_followPathCommand = new FollowPathCommand();
    //private final LimelightShootBallCommand m_limelightShootBallCommand = new LimelightShootBallCommand(m_shooter, m_storage, m_drivetrain);
    //private final RunStorageCommand m_runStorageCommand = new RunStorageCommand(m_storage, m_hopper);
    //private final ShootBallCommand m_shootBallCommand = new ShootBallCommand(m_shooter, m_storage);
    private final TeleopArcadeDriveCommand m_teleopArcadeDriveCommand = new TeleopArcadeDriveCommand(m_drivetrain);
    private final TeleopTankDriveCommand m_teleopTankDriveCommand = new TeleopTankDriveCommand(m_drivetrain);
    private final ToggleIntakeCommand m_toggleIntakeCommand = new ToggleIntakeCommand(m_intake);

    public RobotContainer() {
        configureButtonBindings();
        configureDefaultCommands();
        Logger.configureLoggingAndConfig(this, false);
    }

    private void configureButtonBindings() {
        //joystickOperator.getButton(ButtonF310.Start).whenPressed(m_climbBalanceCommand);
        joystickOperator.getButton(ButtonF310.A).whenPressed(m_toggleIntakeCommand);
        //joystickOperator.getButton(ButtonF310.BumperRight).whenPressed(m_extendClimbCommand);
        //joystickOperator.getButton(ButtonF310.BumperLeft).whenPressed(m_retractClimbCommand);
        
        m_teleopArcadeDriveCommand.setSuppliers(() -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), JOYSTICK_INPUT_EXPONENT), () -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickRightX), JOYSTICK_INPUT_EXPONENT));
        m_teleopTankDriveCommand.setSuppliers(() -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), JOYSTICK_INPUT_EXPONENT), () -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickRightY), JOYSTICK_INPUT_EXPONENT));
        //m_shootBallCommand.setSupplier(() -> joystickOperator.getRawAxis(AxisF310.JoystickLeftY));
    }

    private void configureDefaultCommands() {
        CommandScheduler.getInstance().setDefaultCommand(m_drivetrain, m_teleopArcadeDriveCommand);
        //Hopper is run by storage command
        //CommandScheduler.getInstance().setDefaultCommand(m_hopper, m_runStorageCommand);
        //CommandScheduler.getInstance().setDefaultCommand(m_storage, m_runStorageCommand);
    }

    public Command getAutonomousCommand() {
        return null;
    }

    public void updateLoggerEntries() {
        Logger.updateEntries();
    }
}
