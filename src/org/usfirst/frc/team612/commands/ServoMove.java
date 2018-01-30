package org.usfirst.frc.team612.commands;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ServoMove extends Command {
	boolean isUp;
	private static Servo servo = new Servo(0);
	private static double angle = 0;
	
    public ServoMove(boolean up) {//true means up, false means down
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	isUp = up;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	angle = servo.getAngle();
    	SmartDashboard.putNumber("servo angle", angle);
    	if(isUp) {
    		if(angle != 170) {
    			servo.setAngle(angle+1);
    		}
    	}
    	else {
    		if(angle != 0) {
    			servo.setAngle(angle-1);
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
