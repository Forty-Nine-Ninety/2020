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
    SendableChooser<String> m_stringChooser;

    
    public AutonomousChooser(SendableChooser<String> stringChooser){
        m_stringChooser = stringChooser;  
    }


    public void AutoChooser1(){

        for (String key : PathBuilder.MENU1.keySet()){
            m_stringChooser.addOption(key, key);
        }

    }

    public void AutoChooser2(){

        for (String key : PathBuilder.MENU2.keySet()){
            m_stringChooser.addOption(key, key);
        }

    }
}