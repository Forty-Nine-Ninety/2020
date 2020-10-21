package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import io.github.oblarg.oblog.Loggable;

public class AutoSequenceChooser implements Loggable {
    SendableChooser<String> autoSequenceChooser;
    
    public AutoSequenceChooser() {
        super();
        init();
    }

    public void init() {
        Shuffleboard.getTab("Autonomous").add(autoSequenceChooser);

    }
}