package org.usfirst.frc.team612.commands;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReplayRobot extends Command {
	
	//String for file
	public static String file_name = "data10.txt";
	public String line;
	
	//Init objects for reader
	public static FileReader fr;
	public static BufferedReader br;
	Timer replay_timer = new Timer();
	
	public static double playback_speed = 1;
	
	
    public ReplayRobot() {

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
			fr = new FileReader("/home/lvuser/" + file_name);
			br = new BufferedReader(fr);
			if(fr == null || br == null) {
				System.out.println("Reader Objects are equal to Null");

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Object creation failed");
		}
    	replay_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {	
		try {
			line = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(line != null) {
    		String[] parts = line.split(",");
			double talon_1 = Double.parseDouble(parts[0]);
			double talon_2 = Double.parseDouble(parts[1]);
			double talon_3 = Double.parseDouble(parts[2]);
			double talon_4 = Double.parseDouble(parts[3]);
			float seconds = Float.parseFloat(parts[4]);
			//System.out.println(talon_1 + "/" + talon_2 + "/" + talon_3 + "/" + talon_4 + "/" + seconds);
			double seconds_replay = replay_timer.get();
			if (seconds_replay >= seconds*playback_speed) {
	    		Robot.drivetrain.getTalon(1).set(talon_1);
	    		Robot.drivetrain.getTalon(2).set(talon_2);
	    		Robot.drivetrain.getTalon(3).set(talon_3);
	    		Robot.drivetrain.getTalon(4).set(talon_4);
			}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
