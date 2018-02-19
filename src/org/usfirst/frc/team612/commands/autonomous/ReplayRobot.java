package org.usfirst.frc.team612.commands.autonomous;

import java.io.BufferedReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ReplayRobot extends Command {

    Timer replay_timer = new Timer();
    public static Integer index_counter = 0;
    public static boolean end = false;

    public static double magnitude;
    public static double angle;
    public static double rotation;

    public ReplayRobot() {
    	requires(Robot.drivetrain);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        replay_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double seconds_replay = replay_timer.get();

        //if (seconds_replay >= seconds*playback_speed) {
        Robot.drivetrain.getDriveTrain().drivePolar(OI.drive_data.get(index_counter), OI.drive_data.get(index_counter + 1), OI.drive_data.get(index_counter + 2));
        SmartDashboard.putNumber("magnitude", magnitude); // actually magnitude
        index_counter = index_counter + 4;
        //}

    }
    //System.out.println("Replay Finished");

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}