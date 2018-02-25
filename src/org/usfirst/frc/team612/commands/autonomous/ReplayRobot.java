package org.usfirst.frc.team612.commands.autonomous;

import java.io.BufferedReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ReplayRobot extends Command {

    Timer replay_timer = new Timer();
    public int index_counter = 0;
    public static boolean end_R = false;

    public static double magnitude;
    public static double angle;
    public static double rotation;

    public ReplayRobot() {
    	requires(Robot.drivetrain);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        replay_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(index_counter+4 < OI.drive_data.size() ) {
    		System.out.println("REPLAY IN IF");
    		double seconds_replay = replay_timer.get();
	        //if (seconds_replay >= seconds*playback_speed) {
	        Robot.drivetrain.getDriveTrain().drivePolar(OI.drive_data.get(index_counter), OI.drive_data.get(index_counter + 1), OI.drive_data.get(index_counter + 2));
	        if(OI.drive_data.get(index_counter+3) == 0) {
	        	Robot.grabber.getSolenoid().set(Value.kOff);
	        } else if (OI.drive_data.get(index_counter+3) == 1) {
	        	Robot.grabber.getSolenoid().set(Value.kForward);
	        } else if (OI.drive_data.get(index_counter+3) == -1) {
	        	Robot.grabber.getSolenoid().set(Value.kReverse);
	        }
	        System.out.println("Yo THIS IS THE NUM SET " + OI.drive_data.get(index_counter+4));
	        Robot.lift.getTalon().set(ControlMode.Position,  OI.drive_data.get(index_counter+4));
	        SmartDashboard.putNumber("magnitude", magnitude); // actually magnitude
	        index_counter = index_counter + 6;
    	}
    	else {
    		end_R = true;
    	}
        if(OI.driver_button_X.get()) {
        	end_R = true;
        }
        //}

    }
    //System.out.println("Replay Finished");

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return end_R;
    }

    // Called once after isFinished returns true
    protected void end() {
    	OI.drive_data = new ArrayList<Double>(4);
    	index_counter = 0;
    	end_R = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}