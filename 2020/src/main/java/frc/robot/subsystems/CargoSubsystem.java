package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

//See https://docs.wpilib.org/en/latest/docs/software/commandbased/pid-subsystems-commands.html
//And perhaps https://github.com/wpilibsuite/allwpilib/blob/master/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/gyrodrivecommands/commands/TurnToAngle.java

//But most importantly https://docs.wpilib.org/en/latest/docs/software/commandbased/pid-subsystems-commands.html
//Maybe change this back to "normal" subsystem and use a PIDCommand because the drivetrain can both turn and go straight?

//https://www.chiefdelphi.com/t/frc-driving-straight/364640

public class CargoSubsystem extends SubsystemBase {

    private final WPI_TalonSRX m_motor;


    public CargoSubsystem() {
        m_motor = new WPI_TalonSRX(CAN_CARGO_TALONSRX);
    }

    @Override
    public void periodic() {}

    public void run(double speed) {
        m_motor.set(speed);
    }
}
