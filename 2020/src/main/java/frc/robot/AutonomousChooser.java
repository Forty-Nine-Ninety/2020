package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.AutoDriveCommand;
import edu.wpi.first.wpilibj2.command.Command;
import io.github.oblarg.oblog.annotations.Config;

public class AutonomousChooser{
    
    //chooses starting position
    SendableChooser<Command> m_commandChooser;
    
    public AutonomousChooser(SendableChooser<Command> commandChooser){
        m_commandChooser = commandChooser;
        commandChooser.setDefaultOption("MoveOnly", new AutoDriveCommand());
        commandChooser.addOption("ShootMove", /*corresponding command*/);
        commandChooser.addOption("ShootGetBalls", /*corresponding command*/);
    }

    @Config.ToggleButton
    void runSelectedCommand(boolean run) {
        if (run) {
            m_commandChooser.getSelected().schedule();
        } 
        else {
            m_commandChooser.getSelected().cancel();
        }
}