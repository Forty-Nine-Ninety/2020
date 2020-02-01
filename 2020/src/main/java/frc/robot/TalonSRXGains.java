package frc.robot;

import com.ctre.phoenix.motorcontrol.can.SlotConfiguration;

public class TalonSRXGains extends SlotConfiguration {

    public TalonSRXGains(double kF, double kP, double kI, double kD) {
        super();
        this.kF = kF;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }
}
