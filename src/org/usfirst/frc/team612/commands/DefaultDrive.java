package org.usfirst.frc.team612.commands;


import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team612.robot.Robot;
import org.usfirst.frc.team612.robot.OI;


/**
 *
 */
public class DefaultDrive extends Command {
	
	double DEADZONE = 0.05;
	double prev_magnitude = 0;
	double rate = 0.05;
	boolean DRIVER_PERSPECTIVE = false;

    public DefaultDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double direction_x = 0;
    	double direction_y = 0;
    	double rotation = 0;
    	if(OI.XBOX) {
       	 	direction_x = OI.joystick.getX(Hand.kLeft) * -1;
       	 	direction_y = OI.joystick.getY(Hand.kLeft) * -1;
       	 	rotation    = OI.joystick.getX(Hand.kRight) * -1;
       	}else {
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
