package frc.robot;

import frc.robot.JoystickF310.*;
import frc.robot.commands.*;
//import frc.robot.AutonomousChooser;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.Constants.*;
import io.github.oblarg.oblog.Logger;
import io.github.oblarg.oblog.annotations.Config;

public class RobotContainer {

    private final JoystickF310 joystickDrive = new JoystickF310(PORT_JOYSTICK_DRIVE);
    private final JoystickF310 joystickOperator = new JoystickF310(PORT_JOYSTICK_OPERATOR);

    @Config
    private final SendableChooser<String> m_positionChooser = new SendableChooser<>();
    @Config
    private final SendableChooser<SequentialCommandGroup> m_actionChooser = new SendableChooser<>(); 


    private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
    private final StorageSubsystem m_storage  = new StorageSubsystem();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final ClimbSubsystem m_climb = new ClimbSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    //private final AutonomousChooser auto = new AutonomousChooser(m_positionChooser, m_actionChooser, m_drivetrain, m_shooter, m_storage);
    
    //TODO Add drivetrain commands
    private final TeleopTankDriveCommand m_teleopTankDriveCommand = new TeleopTankDriveCommand(m_drivetrain);
    private final TeleopArcadeDriveCommand m_teleopArcadeDriveCommand = new TeleopArcadeDriveCommand(m_drivetrain);
    
    //Mech Tests
    private final ElevatorTestCommand m_elevatorTestCommand = new ElevatorTestCommand(m_climb);


    private final ClimbBalanceCommand m_climbBalanceCommand = new ClimbBalanceCommand(m_climb, m_drivetrain);
    private final ToggleIntakeCommand m_toggleIntakeCommand = new ToggleIntakeCommand(m_intake);
s    private final ExtendClimbCommand m_extendClimbCommand = new ExtendClimbCommand(m_climb);
    private final RetractClimbCommand m_retractClimbCommand = new RetractClimbCommand(m_climb);
    private final ShootBallCommand m_shootBallCommand = new ShootBallCommand(m_shooter, m_storage);




    public RobotContainer() {
        configureButtonBindings();
        configureDefaultCommands();
        Logger.configureLoggingAndConfig(this, false);
    }

    private void configureButtonBindings() {
        joystickOperator.getButton(ButtonF310.Start).whenPressed(m_climbBalanceCommand);
        joystickOperator.getButton(ButtonF310.A).whenPressed(m_toggleIntakeCommand);
        joystickOperator.getButton(ButtonF310.BumperRight).whenPressed(m_extendClimbCommand);
        joystickOperator.getButton(ButtonF310.BumperLeft).whenPressed(m_retractClimbCommand);
        
        m_teleopArcadeDriveCommand.setSuppliers(() -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), JOYSTICK_INPUT_EXPONENT), () -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickRightX), JOYSTICK_INPUT_EXPONENT));
        m_teleopTankDriveCommand.setSuppliers(() -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), JOYSTICK_INPUT_EXPONENT), () -> Util.powCopySign(joystickDrive.getRawAxis(AxisF310.JoystickRightY), JOYSTICK_INPUT_EXPONENT));
        m_shootBallCommand.setSupplier(() -> joystickOperator.getRawAxis(AxisF310.JoystickLeftY));

        m_teleopTankDriveCommand.setSuppliers(() -> joystickDrive.getRawAxis(AxisF310.JoystickLeftY), 
        () -> joystickDrive.getRawAxis(AxisF310.JoystickRightY));

        //TODO bind buttons for elevator test
        m_elevatorTestCommand.setSuppliers(, () -> joystickOperator.getRawAxis(AxisF310.JoystickLeftY), () -> joystickOperator.getRawAxis(AxisF310.JoystickRightX));
    }

    private void configureDefaultCommands() {
        CommandScheduler.getInstance().setDefaultCommand(m_drivetrain, m_teleopTankDriveCommand);
    }

    public Command getAutonomousCommand() {
        return null;
    }

    public void updateLoggerEntries() {
        Logger.updateEntries();
    }

    // public void chooseAutoActions(){
        
    //     auto.AutoChooser1();
    //     auto.AutoChooser2();

    //     auto.runAction(false);
    // }
}
