package frc.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.AutoDriveCommand;
import frc.robot.paths.PathBuilder;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import io.github.oblarg.oblog.annotations.Config;

public class AutonomousChooser{
    
    //chooses starting position
    public SendableChooser<String> m_positionChooser;

    //chooses what it will do
    public SendableChooser<String> m_actionChooser;

    
    public AutonomousChooser(SendableChooser<String> posChooser, SendableChooser<String> actChooser){
        m_positionChooser = posChooser;
        m_actionChooser = actChooser;

        AutoChooser1();
        AutoChooser2();
    }


    public void AutoChooser1(){

        for (String key : PathBuilder.MENU1.keySet()){
            m_positionChooser.addOption(key, key);
        }

    }

    public void AutoChooser2(){

        for (String key : PathBuilder.MENU2.keySet()){
            m_actionChooser.addOption(key, key);
        }

    }
}