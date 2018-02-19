package org.usfirst.frc.team612.commands;

import java.io.File;
import org.usfirst.frc.team612.commands.DefaultDrive;
import org.usfirst.frc.team612.robot.Robot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RecordMovement extends Command {

    static String directory = "/home/lvuser/";
    public static String file_name_create = "data10.txt";
    public static String file_dir = directory + file_name_create;
    public static File file_to_create = new File(file_dir);
    public static PrintWriter writer;
    public static double magnitude;
    public static double angle;
    public static double rotation;
    public static Timer data_timer = new Timer();
    public static boolean end = false;

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

        String magnitude_string = Double.toString(magnitude);
        String angle_string = Double.toString(angle);
        String rotation_string = Double.toString(rotation);
        String lift_voltage = Double.toString(Robot.lift.getTalon().get());
        check_grabber_voltage = !Robot.grabber.getSolenoid().get();
        String grabber_voltage = "";
        if (check_grabber_voltage == Value.kOff) {
        	grabber_voltage = "0";
        }
        else if (check_grabber_voltage == Value.kForward) {
        	grabber_voltage = "1";
        }
        
        else if (check_grabber_voltage == Value.kReverse) {
        	grabber_voltage = "2";
        }
        
        check_dropper_voltage = !Robot.dropper.getSolenoid().get());
        String dropper_voltage = "";
        if (check_dropper_voltage == Value.kOff) {
        	dropper_voltage = "0";
        }
        else if (check_dropper_voltage == Value.kForward) {
        	dropper_voltage = "1";
        }
        
        else if (check_dropper_voltage == Value.kReverse) {
        	dropper_voltage = "2";
        }
        
        //String talon_4 = Double.toString(talon_RL);

        //create array of data to store
        String input_data[] = {magnitude_string, angle_string, rotation_string, lift_voltage, grabber_voltage, dropper_voltage, seconds};
        String writable_data = "";

        //format the data
        writable_data = input_data[0] + "," + input_data[1] + "," + input_data[2] + "," + input_data[3] + "," + input_data[4] + "," + input_data[5] + "," + input_data[6] + "," + input_data[7];

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
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}