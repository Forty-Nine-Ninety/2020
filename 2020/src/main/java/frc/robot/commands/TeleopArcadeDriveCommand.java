package frc.robot.commands;

import static frc.robot.Constants.*;
import frc.robot.subsystems.DrivetrainSubsystem;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopArcadeDriveCommand extends CommandBase {

    private final DrivetrainSubsystem m_drive;
    private DoubleSupplier m_speedSupplier, m_rotationSupplier;
    private PIDController m_pidL, m_pidR;

    public TeleopArcadeDriveCommand(DrivetrainSubsystem drive) {
        addRequirements(drive);
        m_drive = drive;
        m_pidL = new PIDController(DRIVETRAIN_LEFT_KP, DRIVETRAIN_LEFT_KI, DRIVETRAIN_LEFT_KD);
        m_pidR = new PIDController(DRIVETRAIN_RIGHT_KP, DRIVETRAIN_RIGHT_KI, DRIVETRAIN_RIGHT_KD);
    }

    public void setSuppliers(DoubleSupplier left, DoubleSupplier right) {
        m_speedSupplier = left;
        m_rotationSupplier = right;
    }

    @Override
    public void execute() {
        
        System.out.println("Running motors at I: " + m_speedSupplier.getAsDouble() + ", " + m_rotationSupplier.getAsDouble() + " ");

        double[] tank = convertToTank(m_speedSupplier.getAsDouble(), m_rotationSupplier.getAsDouble());
        double l = m_pidL.calculate(m_drive.getRateLeft(), tank[0] * DRIVETRAIN_MAXIMUM_CRUISE_SPEED_METERS_PER_SECOND), r = m_pidR.calculate(m_drive.getRateRight(), tank[1] * DRIVETRAIN_MAXIMUM_CRUISE_SPEED_METERS_PER_SECOND);

        System.out.println("LEFT: " + m_drive.getRateLeft() + " " + tank[0] * DRIVETRAIN_MAXIMUM_CRUISE_SPEED_METERS_PER_SECOND + " " + l);
        System.out.println("RIGHT: " + m_drive.getRateRight() + " " + tank[1] * DRIVETRAIN_MAXIMUM_CRUISE_SPEED_METERS_PER_SECOND + " " + r);
        m_drive.tankDrive(l, r);
    }

    private static double[] convertToTank(double speed, double rot) {
        double left, right, maxSpeed;

        if (Math.abs(speed) > Math.abs(rot)) maxSpeed = speed;
        else maxSpeed = speed > 0 ? Math.abs(rot) : Math.abs(rot) * -1;

        if (speed > 0) {
            if (rot > 0) {
                left = maxSpeed;
                right = speed - rot;
            }
            else {
                left = speed + rot;
                right = maxSpeed;
            }
        }
        else {
            if (rot > 0) {
                left = speed + rot;
                right = maxSpeed;
            }
            else {
                left = maxSpeed;
                right = speed - rot;
            }
        }
        return new double[] {left, right};
    }

}
