package org.usfirst.frc.team612.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class ReplayArray extends Command {

    //String for file
    public static String file_name = "data10.txt";

    //Init objects for reader
    public static FileReader fr;
    public static BufferedReader br;
    public String line;

    public static double playback_speed = 1;
    public static boolean end = false;

    public static ArrayList < Double > drive_data = new ArrayList < Double > (4);

    public static double magnitude;
    public static double angle;
    public static double rotation;
    public static double seconds_replay;

    public ReplayArray() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
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
        } else {
            RecordMovement.writer.close();
            RecordMovement.end = true;
            System.out.println("Recording Ended");
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        try {
            line = ReplayArray.br.readLine();
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
            double seconds_replay = Double.parseDouble(parts[3]);

            drive_data.add(magnitude);
            drive_data.add(angle);
            drive_data.add(rotation);
            drive_data.add(seconds_replay);
            
            if (line == null) {
            	//end = true;
            }
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