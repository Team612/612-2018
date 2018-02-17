package org.usfirst.frc.team612.commands;

import java.io.File;
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
	public static File file = new File("/home/lvuser/Output.txt");
	static String directory = "/home/lvuser/";
	public static String file_name_create = "data41.txt";
	public static String file_dir = directory + file_name_create;
	public static PrintWriter writer;
	public static File file_to_create = new File(file_dir);
	public static Timer data_timer = new Timer();
	
	/**
	 * Requires something.
	 * @deprecated <code>RecordMovement</code> has no code whatsoever in its body.
	 */
    public RecordMovement() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    /**
     * Tries to create a new file, and starts a data timer when <code>RecordMovement</code> runs the first time.
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
			file.createNewFile();
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

    /**
     * Does some Talon stuff, as well as some Writable stuff, 60 times every second.
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//save magnitude angle and rotation to a file
   	 	String seconds = String.valueOf(data_timer.get());
   	 	
   	 	double talon_FL = Robot.drivetrain.getTalon(1).get();
   	 	double talon_FR = Robot.drivetrain.getTalon(2).get();
	   	double talon_RR = Robot.drivetrain.getTalon(3).get();
	   	double talon_RL = Robot.drivetrain.getTalon(4).get();
   	 	
	   	String talon_1 = Double.toString(talon_FL);
	   	String talon_2 = Double.toString(talon_FR);
	   	String talon_3 = Double.toString(talon_RR);
	   	String talon_4 = Double.toString(talon_RL);
	   	
   	 	//create array of data to store
   	 	String input_data[] = {talon_1, talon_2, talon_3, talon_4, seconds};
   	 	String writable_data = "";
   	 	
   	 	//format the data
   	 	writable_data = input_data[0] + "," + input_data[1] + "," + input_data[2] + "," + input_data[3] + "," + input_data[4];
   	 	System.out.println(writable_data);
   	 	//print and write data to file
   	 	if (writer == null) {
   	 		System.out.println("Writer Object = Null");
   	 	} else {
   	 		writer.println(writable_data);
   	 	}
    }

    /**
     * Returns the value of <code>driver_button_Y</code>, and if the value is <code>true</code>, then <code>execute()</code> will stop.
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return OI.driver_button_Y.get();
    }

    // Called once after isFinished returns true
    /**
     * Called once after <code>isFinished()</code> returns <code>true</code>.
     * @deprecated <code>end()</code> does nothing in RecordMovement.java.
     */
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     * @deprecated <code>interrupted()</code> does nothing in RecordMovement.java.
     */
    protected void interrupted() {
    }
}
