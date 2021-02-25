package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import io.github.oblarg.oblog.Loggable;

import static frc.robot.Constants.*;

public class StorageSubsystem extends SubsystemBase implements Loggable {
    private int balls, lastBall;
    private boolean enabled;
    private final WPI_TalonSRX m_motor;
    private final DigitalInput m_sensor;

    public StorageSubsystem() {
        m_motor = new WPI_TalonSRX(CAN_STORAGE_LOW_TALONSRX);
        m_sensor = new DigitalInput(DIO_BREAKBEAM_STORAGE_LOW);

        balls = 0;
        enabled = false;
    }

    @Override
    public void periodic() {}

    public void run(boolean b) {
        m_motor.set(b && enabled ? STORAGE_MOTOR_SPEED : 0);
    }

    public boolean hasBall() {
        return m_sensor.get();
    }
    
    public void insertBall() {
        balls += 1;
        lastBall = m_motor.getSelectedSensorPosition();
    }

    public int getEmptyDistance() {
        return m_motor.getSelectedSensorPosition() - lastBall;
    }

    public void removeBall() {
        balls -= 1;
    }

    public int getBallCount() {
        return balls;
    }

    public void setEnabled(boolean e) {
        enabled = e;
    }
}
