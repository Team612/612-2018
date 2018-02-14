package org.usfirst.frc.team612.commands;

import java.io.File;
import org.usfirst.frc.team612.commands.DefaultDrive;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RecordMovement extends Command {
	static String directory = "/home/lvuser/";
	public static String file_name_create = "data10.txt";
	public static String file_dir = directory + file_name_create;
	public static PrintWriter writer;
	public static File file_to_create = new File(file_dir);
	public static Timer data_timer = new Timer();
	public static boolean end = false;
	public static double magnitude;
	public static double angle;
	public static double rotation;
	
    public RecordMovement() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
			if (file_to_create.createNewFile()) {
				System.out.println("File created...");
			} else {
				if (file_to_create.delete()) {
					System.out.println("File deleted");
					file_to_create.createNewFile();
					System.out.println("File overwrited");
				} else {
					System.out.println("Error deleting file");
				}
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
    	data_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//save magnitude angle and rotation to a file
   	 	String seconds = String.valueOf(data_timer.get());
   	 	
   	 	/*
   	 	double talon_FL = Robot.drivetrain.getTalon(1).get();
   	 	double talon_FR = Robot.drivetrain.getTalon(2).get();
	   	double talon_RR = Robot.drivetrain.getTalon(3).get();
	   	double talon_RL = Robot.drivetrain.getTalon(4).get();
	   	*/
   	 	double talon_FL = magnitude;
	 	double talon_FR = angle;
	   	double talon_RR = rotation;
   	 	
	   	String talon_1 = Double.toString(talon_FL);
	   	String talon_2 = Double.toString(talon_FR);
	   	String talon_3 = Double.toString(talon_RR);
	   	//String talon_4 = Double.toString(talon_RL);
	   	
   	 	//create array of data to store
   	 	String input_data[] = {talon_1, talon_2, talon_3, seconds};
   	 	String writable_data = "";
   	 	
   	 	//format the data
   	 	writable_data = input_data[0] + "," + input_data[1] + "," + input_data[2] + "," + input_data[3];
   	 	
   	 	//print and write data to file
   	 	if (writer == null) {
   	 		System.out.println("Writer Object = Null");
   	 	} else {
   	 		System.out.println(writable_data);
   	 		writer.println(writable_data);
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
