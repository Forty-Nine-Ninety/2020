package frc.robot;

import frc.robot.JoystickF310.*;
import frc.robot.commands.*;
import frc.robot.paths.PathWeaver;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import static frc.robot.Constants.*;
import io.github.oblarg.oblog.Logger;
import io.github.oblarg.oblog.annotations.Config;

public class RobotContainer {

    private final JoystickF310 joystickDrive = new JoystickF310(PORT_JOYSTICK_DRIVE);
    private final JoystickF310 joystickOperator = new JoystickF310(PORT_JOYSTICK_OPERATOR);

    @Config
    private final SendableChooser<String> m_positionChooser = new SendableChooser<>();
    @Config
    private final SendableChooser<Command> m_actionChooser = new SendableChooser<>(); 


    private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
    private final StorageSubsystem m_storage  = new StorageSubsystem();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final AutonomousChooser auto = new AutonomousChooser(m_positionChooser, m_actionChooser, m_drivetrain, m_shooter, m_storage);
    //TODO Add drivetrain commands
    private final TeleopTankDriveCommand m_teleopTankDriveCommand = new TeleopTankDriveCommand(m_drivetrain);
    


    public RobotContainer() {
        configureButtonBindings();
        configureDefaultCommands();
        Logger.configureLoggingAndConfig(this, false);
    }

    private void configureButtonBindings() {

        m_teleopTankDriveCommand.setSuppliers(() -> joystickDrive.getRawAxis(AxisF310.JoystickLeftY), () -> joystickDrive.getRawAxis(AxisF310.JoystickRightY));
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

    public void chooseAutoActions(){
        
        auto.AutoChooser1();
        auto.AutoChooser2();

        auto.runAction(false);
    }
}
