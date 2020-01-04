package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CargoSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_talon;

    private final PIDController m_PID;

    private double m_targetSpeed;

    public CargoSubsystem() {
        m_talon = new WPI_TalonSRX(Constants.CAN_CARGO_TALONSRX);

        m_PID = new PIDController(Constants.CARGO_KP, Constants.CARGO_KI, Constants.CARGO_KD);

        m_targetSpeed = 0;
    }

    public void setSpeed(double speed) {
        m_targetSpeed = speed;
    }

    @Override
    public void periodic() {
        m_talon.set(m_PID.calculate(getRate(), m_targetSpeed));
    }

    //TODO get actual rates
    private double getRate() {
        return -1;
    }
}
