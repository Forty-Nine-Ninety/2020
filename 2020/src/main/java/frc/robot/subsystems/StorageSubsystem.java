package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Direction;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import io.github.oblarg.oblog.Loggable;

import static frc.robot.Constants.*;

public class StorageSubsystem extends SubsystemBase implements Loggable {

    private final WPI_TalonSRX m_motor;

    public StorageSubsystem() {
        m_motor = new WPI_TalonSRX(Ports.CAN_STORAGE_TALONSRX);
        m_motor.configFactoryDefault();
    }

    @Override
    public void periodic() {}

    public void run(Direction direction) {
        int multiplier = direction.getMultiplier();
        m_motor.set(multiplier * STORAGE_MOTOR_SPEED);
    }


}
