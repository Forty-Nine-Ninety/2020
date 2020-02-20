package frc.robot;

import frc.robot.JoystickF310.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import static frc.robot.Constants.*;
import io.github.oblarg.oblog.Logger;

public class RobotContainer {

    private final JoystickF310 joystickDrive = new JoystickF310(PORT_JOYSTICK_DRIVE);
    private final JoystickF310 joystickOperator = new JoystickF310(PORT_JOYSTICK_OPERATOR);

    private final ClimbSubsystem m_climb = new ClimbSubsystem();
    private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
    private final HopperSubsystem m_hopper = new HopperSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final StorageSubsystem m_storage  = new StorageSubsystem();

    private final ClimbBalanceCommand m_climbBalanceCommand = new ClimbBalanceCommand(m_climb);
    private final ClimbCommand m_climbCommand = new ClimbCommand(m_climb);
    private final LimelightShootBallCommand m_limelightShootBallCommand = new LimelightShootBallCommand(m_shooter, m_storage, m_drivetrain);
    private final RunHopperCommand m_runHopperCommand = new RunHopperCommand(m_hopper, m_storage);
    private final RunStorageCommand m_runStorageCommand = new RunStorageCommand(m_storage);
    private final ShootBallCommand m_shootBallCommand = new ShootBallCommand(m_shooter, m_storage);
    private final TeleopArcadeDriveCommand m_teleopArcadeDriveCommand = new TeleopArcadeDriveCommand(m_drivetrain);
    private final TeleopTankDriveCommand m_teleopTankDriveCommand = new TeleopTankDriveCommand(m_drivetrain);
    private final ToggleIntakeCommand m_toggleIntakeCommand = new ToggleIntakeCommand(m_intake);

    public RobotContainer() {
        configureButtonBindings();
        configureDefaultCommands();
        Logger.configureLoggingAndConfig(this, false);
    }

    private void configureButtonBindings() {
        joystickOperator.getButton(ButtonF310.Start).whenPressed(m_climbBalanceCommand);
        joystickOperator.getButton(ButtonF310.Back).whenPressed(m_climbCommand);
        joystickOperator.getButton(ButtonF310.A).whenPressed(m_toggleIntakeCommand);
        
        m_teleopArcadeDriveCommand.setSuppliers(() -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), JOYSTICK_INPUT_EXPONENT), () -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickRightX), JOYSTICK_INPUT_EXPONENT));
        m_teleopTankDriveCommand.setSuppliers(() -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), JOYSTICK_INPUT_EXPONENT), () -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickRightY), JOYSTICK_INPUT_EXPONENT));
    }

    private void configureDefaultCommands() {
        CommandScheduler.getInstance().setDefaultCommand(m_drivetrain, m_teleopArcadeDriveCommand);
        CommandScheduler.getInstance().setDefaultCommand(m_hopper, m_runHopperCommand);
        CommandScheduler.getInstance().setDefaultCommand(m_storage, m_runStorageCommand);
        CommandScheduler.getInstance().setDefaultCommand(m_shooter, m_shootBallCommand);
    }

    public Command getAutonomousCommand() {
        return null;
    }

    public void updateLoggerEntries() {
        Logger.updateEntries();
    }
}
