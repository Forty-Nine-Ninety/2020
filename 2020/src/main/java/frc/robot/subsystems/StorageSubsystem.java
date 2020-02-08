package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static frc.robot.Constants.*;

public class StorageSubsystem extends SubsystemBase {
    
    private ArrayList<Double> balls;
    private final WPI_TalonSRX m_motorLow, m_motorHigh;

    public StorageSubsystem() {
        balls = new ArrayList<>();
        m_motorLow = new WPI_TalonSRX(CAN_STORAGE_LOW_TALONSRX);
        m_motorHigh = new WPI_TalonSRX(CAN_STORAGE_HIGH_TALONSRX);
    }

    @Override
    public void periodic() {}

    public void addBall() {
        //TODO
    }
    
    public int getBallsLeft() {
        return balls.size();
    }

    public double getNextBall() {
        //Returns the distance until the ball reaches the end of the storage system
        //TODO
        return 0;
    }
}
