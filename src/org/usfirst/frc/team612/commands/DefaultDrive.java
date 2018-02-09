package org.usfirst.frc.team612.commands;


import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team612.robot.Robot;
import org.usfirst.frc.team612.robot.OI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.wpi.first.wpilibj.Timer;


/**
 *
 */
public class DefaultDrive extends Command {
	
	double DEADZONE = 0.05;
	double prev_magnitude = 0;
	double rate = 0.05;
	boolean DRIVER_PERSPECTIVE = false;
	
	static String directory = "/home/lvuser/";
	public static String file_name_create = "data.txt";
	public static String file_dir = directory + file_name_create;
	public static PrintWriter writer;
	public static File file_to_create = new File(file_dir);
	
    public DefaultDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
			if (file_to_create.createNewFile()) {
				System.out.println("File created...");
			} else {
				System.out.println("File not created...");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	try {
			writer = new PrintWriter(file_dir); //Try both file object and string...
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double direction_x = 0;
    	double direction_y = 0;
    	double rotation = 0;
    	if(OI.XBOX) {
       	 	direction_x = OI.driver.getX(Hand.kLeft) * -1;
       	 	direction_y = OI.driver.getY(Hand.kLeft) * -1;
       	 	rotation    = OI.driver.getX(Hand.kRight) * -1;
       	 	
       	} else {
           	 direction_x = OI.joy.getX() * -1;
           	 direction_y = OI.joy.getY() * -1;
           	 rotation = OI.joy.getTwist() * -1;
       	}
    	// Get all the joystick information
    	if(direction_x<DEADZONE&&direction_x>-DEADZONE) {
    		direction_x = 0;
    	}
    	if(direction_y<DEADZONE&&direction_y>-DEADZONE) {
    		direction_y = 0;
    	}
    	if(rotation<DEADZONE&&rotation>-DEADZONE) {
    		rotation = 0;
    	}
    	
    	
    	// Do all the deadzone math
    	double magnitude = Math.sqrt(direction_x*direction_x+direction_y*direction_y);
    	if(magnitude > 1.0) {
    		magnitude = 1.0;
    	}
    	double angle = Math.atan2(direction_y, direction_x)*180/Math.PI;
    	// Convert cartesian to polar
    	double yaw = Robot.navx.getYaw();
    	if(DRIVER_PERSPECTIVE) {
    		angle = angle-yaw;
    	}
    	if(magnitude > prev_magnitude) {
    		if(prev_magnitude+rate>magnitude) {
    			Robot.drivetrain.getDriveTrain().drivePolar(magnitude, angle, rotation);
    			prev_magnitude = magnitude;
    		}
    		prev_magnitude += rate;
    		Robot.drivetrain.getDriveTrain().drivePolar(prev_magnitude+rate, angle, rotation);
    	} else {
    		prev_magnitude = magnitude;
    		Robot.drivetrain.getDriveTrain().drivePolar(magnitude, angle, rotation);
    	}
    	// Save what angle should be to theoretical_angle to be used next iteration
    	
    	//save magnitude angle and rotation to a file
    	String driver_magnitude = String.valueOf(magnitude);
   	 	String driver_angle = String.valueOf(angle);	
   	 	String driver_rotation = String.valueOf(rotation);
   	 	String seconds = String.valueOf(OI.data_timer.get());
   	 	
   	 	//create array of data to store
   	 	String input_data[] = {driver_magnitude, driver_angle, driver_rotation, seconds};
   	 	String writable_data = "";
   	 	
   	 	//format the data
   	 	writable_data = input_data[0] + "," + input_data[1] + "," + input_data[2] + "," + input_data[3];
   	 	
   	 	//print and write data to file
   	 	System.out.println(writable_data);
   	 	if (writer == null) {
   	 		System.out.println("Writer Object = Null");
   	 	} else {
   	 		writer.println(writable_data);
   	 	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.getDriveTrain().driveCartesian(0,  0,  0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.getDriveTrain().driveCartesian(0,  0,  0);
    }
}
