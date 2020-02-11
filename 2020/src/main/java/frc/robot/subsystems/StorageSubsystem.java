package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static frc.robot.Constants.*;

public class StorageSubsystem extends SubsystemBase {
    private int ballsLow, ballsHigh;
    private boolean enabled;
    private final WPI_TalonSRX m_motorLow, m_motorHigh;
    private final DigitalInput m_ballSensorLow, m_ballSensorHigh;

    public StorageSubsystem() {
        m_motorLow = new WPI_TalonSRX(CAN_STORAGE_LOW_TALONSRX);
        m_motorHigh = new WPI_TalonSRX(CAN_STORAGE_HIGH_TALONSRX);
        m_ballSensorLow = new DigitalInput(DIO_BREAKBEAM_STORAGE_LOW);
        m_ballSensorHigh = new DigitalInput(DIO_BREAKBEAM_STORAGE_HIGH);

        ballsLow = 0;
        ballsHigh = 0;
        enabled = false;
    }

    @Override
    public void periodic() {}

    public void runHigh(boolean b) {
        b = b && enabled;
        m_motorHigh.set(b ? STORAGE_MOTOR_SPEED : 0);
    }
    
    public void runLow(boolean b) {
        m_motorLow.set(b ? STORAGE_MOTOR_SPEED : 0);
    }

    public boolean hasBallLow() {
        return m_ballSensorLow.get();
    }

    public boolean hasBallHigh() {
        return m_ballSensorHigh.get();
    }
    
    public void insertBall() {
        ballsLow += 1;
    }

    public void moveBall() {
        ballsLow -= 1;
        ballsHigh += 1;
    }

    public void removeBall() {
        ballsHigh -= 1;
    }

    public int getTotalBalls() {
        return ballsLow + ballsHigh;
    }
    
    public int getBallsLow() {
        return ballsLow;
    }

    public int getBallsHigh() {
        return ballsHigh;
    }

    public double getLastEntered() {
        return 0;
    }

    public double getLastExited() {
        return 0;
    }

    public void setEnabled(boolean e) {
        enabled = e;
    }
}
