package org.usfirst.frc.team612.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReplayRobot extends Command {
	public static int lineNumber = 0;
	private static boolean end = false;
	public static Timer data_timer = new Timer();
	public static String file_name = "data41.txt";
	public static FileReader fr;
	public static BufferedReader br;
	public static double playback_speed = 1;
	double talon_1, talon_2, talon_3, talon_4;
	float seconds;
	public String line = "NOTHING";
	Timer replay_timer = new Timer();
    public ReplayRobot() {
    	//requires(Robot.drivetrain);

    	/*
    	for(String line; (line = bf.readLine()) != null; ) {
    		String[] parts = line.split(",");
    		float driver_magnitude = Float.parseFloat(parts[0]);
    		float driver_angle = Float.parseFloat(parts[1]);
    		float driver_rotation = Float.parseFloat(parts[2]);
    		float seconds = Float.parseFloat(parts[3]);
    		double timer = OI.data_timer.get();
    		if (timer >= seconds) {
    		Robot.drivetrain.getDriveTrain().drivePolar(driver_magnitude, driver_angle, driver_rotation);
    		}
    	}
    	*/
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Replay Called");
    	try {
			fr = new FileReader("/home/lvuser/" + file_name);
			br = new BufferedReader(fr);
			if(fr == null) {
				System.out.println("FR IN NULL");

			}
			if(br == null) {
				System.out.println("BR IS NULL");

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed at object");
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
		if(line == "NOTHING" ) {
			System.out.println("NTOHING");
		}
    	if(line == null) {
    		try {
				System.out.println(br.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} else {
    	String[] parts = line.split(",");
    	
    	try {
    		talon_1 = Double.parseDouble(parts[0]);
    		talon_2 = Double.parseDouble(parts[1]);
			talon_3 = Double.parseDouble(parts[2]);
			talon_4 = Double.parseDouble(parts[3]);
			seconds = Float.parseFloat(parts[4]);
    	} catch (ArrayIndexOutOfBoundsException e) {
    		end = true;
    	}
		System.out.println(talon_1 + "/" + talon_2 + "/" + talon_3 + "/" + talon_4 + "/" + seconds);
		double seconds_replay = replay_timer.get();
		//if (seconds_replay >= seconds*playback_speed) {
    		Robot.drivetrain.getTalon(1).set(talon_1);
    		Robot.drivetrain.getTalon(2).set(talon_2);
    		Robot.drivetrain.getTalon(3).set(talon_3);
    		Robot.drivetrain.getTalon(4).set(talon_4);
    		System.out.println("Set Talons");
    	//}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return end;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
