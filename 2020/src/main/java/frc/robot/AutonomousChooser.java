package frc.robot;

import java.io.IOException;
import java.util.HashMap;
import java.nio.file.Path;
import java.util.function.BiConsumer;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.MoveOnlyCommand;
import frc.robot.commands.ShootGetBallsCommand;
import frc.robot.commands.ShootMoveCommand;
import frc.robot.commands.ShootShieldGenCommand;
import frc.robot.commands.ShootTrenchRunCommand;
import frc.robot.paths.PathBuilder;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.Loggable;

public class AutonomousChooser implements Loggable{
    
    //chooses starting position
    public SendableChooser<String> m_positionChooser;

    //chooses what it will do
    public SendableChooser<SequentialCommandGroup> m_actionChooser;

    private final DrivetrainSubsystem m_drive;
    private final ShooterSubsystem m_shooter;
    private final StorageSubsystem m_storage;

    private String m_ballLocation;


    
    public AutonomousChooser(SendableChooser<String> posChooser, SendableChooser<SequentialCommandGroup> actChooser,
        DrivetrainSubsystem drive, ShooterSubsystem shooter, StorageSubsystem storage){

        m_positionChooser = posChooser;
        m_actionChooser = actChooser;

        m_drive = drive;
        m_shooter = shooter;
        m_storage = storage;
    }


    /*public void AutoChooser1(){

        for (String key : PathBuilder.MENU1.keySet()){
            m_positionChooser.addOption(key, key);
        }

    }

    public void AutoChooser2(){

        for (String key : PathBuilder.MENU2.keySet()){
            m_actionChooser.addOption(key, key);
        }

    }*/

    public void AutoChooser1() {
        //TODO add code using starting position info
        m_positionChooser.setDefaultOption("Left", "left");
        m_positionChooser.addOption("Center", "center");
        m_positionChooser.addOption("Right", "right");
        getStartingPosition();

    }

    public void AutoChooser2() {
        try{
            m_actionChooser.setDefaultOption("Move Only", new MoveOnlyCommand(m_drive, getTrajectory("/paths/MoveOnly.wpilib.json")));
            m_actionChooser.addOption("Shoot, Move", new ShootMoveCommand(m_shooter, m_storage, m_drive, getTrajectory("/paths/ShootMove.wpilib.json")));
            
            if (m_positionChooser.getSelected() == "left"){
                m_actionChooser.addOption("Shoot, Get Balls", new ShootShieldGenCommand(m_shooter, m_storage, m_drive, getTrajectory(m_ballLocation)));
            }
            else if (m_positionChooser.getSelected() == "center"){
                m_actionChooser.addOption("Shoot, Get Balls", new ShootShieldGenCommand(m_shooter, m_storage, m_drive, getTrajectory(m_ballLocation)));
            }

            else{
                m_actionChooser.addOption("Shoot, Get Balls", new ShootTrenchRunCommand(m_shooter, m_storage, m_drive, getTrajectory(m_ballLocation)));
            }
        } catch (IOException io){
            io.printStackTrace();
        }
    }

    private Trajectory getTrajectory(String autoPath) throws IOException {
        String trajectoryJSON = autoPath;
        Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
        Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        return trajectory;
    }

    private void getStartingPosition(){
       
        switch (m_positionChooser.getSelected()){
            case "left":
                m_ballLocation = "/paths/ShieldGen.wpilib.json";//goes to shield gen to get ball
                break;
            case "center":
                m_ballLocation = "/paths/ShieldGen.wpilib.json";//same as above
                break;
            case "right":
                m_ballLocation = "/paths/TrenchRun.wpilib.json";//goes to trench to get ball
                break;
        }
    }

    @Config.ToggleButton
    public void runAction(boolean run){
        if (run) m_actionChooser.getSelected().schedule();
        else m_actionChooser.getSelected().cancel();
    }
}