package org.usfirst.frc.team612.commands;

import java.io.BufferedReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    public static boolean end = false;
    
    public static Integer index_counter = 0;
    
    ArrayList<Double> drive_data = new ArrayList<Double>(4);
    
    public ReplayRobot() {

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        try {
            fr = new FileReader("/home/lvuser/" + file_name);
            br = new BufferedReader(fr);
            if (fr == null || br == null) {
                System.out.println("Reader Objects are equal to Null");

            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Object creation failed");
        }
        if (RecordMovement.writer == null) {
    		System.out.println("Please Record Data Before Replay");
    	}else {
    		RecordMovement.writer.close();
            RecordMovement.end = true;
            System.out.println("Recording Ended");
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
        if (line != null) {

            String[] parts = line.split(",");
            System.out.println(line);
            double magnitude = Double.parseDouble(parts[0]);
            double angle = Double.parseDouble(parts[1]);
            double rotation = Double.parseDouble(parts[2]);
            
            double seconds_replay = replay_timer.get();
            
            drive_data.add(magnitude);
            drive_data.add(angle);
            drive_data.add(rotation);
            drive_data.add(seconds_replay);
            
            System.out.println(magnitude + "-" + angle + "-" + rotation);
            

            //if (seconds_replay >= seconds*playback_speed) {
            Robot.drivetrain.getDriveTrain().drivePolar(drive_data.get(index_counter), drive_data.get(index_counter + 1), drive_data.get(index_counter + 2));
            SmartDashboard.putNumber("magnitude", magnitude); // actually magnitude
            index_counter = index_counter + 4;
            //}

        } else {
            //System.out.println("Replay Finished");
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