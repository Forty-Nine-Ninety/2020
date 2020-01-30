package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class StorageSubsystem extends SubsystemBase {
    
    private ArrayList<Double> balls;
    private final TalonSRX m_motor;

    public StorageSubsystem() {
        balls = new ArrayList<>();
        m_motor = new TalonSRX(CAN_STORAGE_TALONSRX);
    }

    @Override
    public void periodic() {}

    public void addBall() {
        
    }
}
