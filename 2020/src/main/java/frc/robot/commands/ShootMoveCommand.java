package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootMoveCommand extends CommandBase {

    LimelightTargetCommand target;
    public ShootMoveCommand(){

    }

    @Override
    public void execute(){
        target.setTargetAngle(angle);
        target.setTargetHeading(heading);
    }
}