package org.usfirst.frc.team612.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CreateReplayFile extends Command {

	//Variables to file creation
		File file = new File("C:\\Users\\1420569\\Documents\\TestData\\data.txt");
		public static Timer data_timer = new Timer();
		public static FileWriter fw = null;
		public static BufferedWriter bw = null;
		
    public CreateReplayFile() throws IOException {
    	
    	if (file.createNewFile()) {
    		System.out.println("File has been created");
    	} else {
    		System.out.println("File already exists");
    	}
    	
    	FileWriter fw = new FileWriter(file, true);
    	BufferedWriter bw = new BufferedWriter(fw);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
