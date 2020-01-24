package frc.robot;

import frc.robot.JoystickF310.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import static frc.robot.Constants.*;

public class RobotContainer {

    private final JoystickF310 joystickDrive = new JoystickF310(PORT_JOYSTICK_DRIVE);
    private final JoystickF310 joystickOperator = new JoystickF310(PORT_JOYSTICK_OPERATOR);

    private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();

    //TODO Add drivetrain commands
    private final TeleopTankDriveCommand m_teleopTankDriveCommand = new TeleopTankDriveCommand(m_drivetrain);
    
    private final Solenoid testSolenoid = new Solenoid(12,1);

    public RobotContainer() {
        configureButtonBindings();
        configureDefaultCommands();
        testSolenoid.set(false);
    }

    private void configureButtonBindings() {

        m_teleopTankDriveCommand.setSuppliers(() -> joystickDrive.getRawAxis(AxisF310.JoystickLeftY), () -> joystickDrive.getRawAxis(AxisF310.JoystickRightX));
    }

    private void configureDefaultCommands() {
        CommandScheduler.getInstance().setDefaultCommand(m_drivetrain, m_teleopTankDriveCommand);
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
