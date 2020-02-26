package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoSequenceChooser {
    SendableChooser<String> autoSequenceChooser;
    
    public AutoSequenceChooser() {
        super();
        init();
    }

    public void init() {
        Shuffleboard.getTab("Autonomous").add(autoSequenceChooser);

    }
}