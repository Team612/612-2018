package org.usfirst.frc.team612.commands;


import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team612.robot.Robot;
import org.usfirst.frc.team612.robot.OI;


/**
 *
 */
public class DefaultDrive extends Command {
	
	double DEADZONE = 0.1;

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
    	double direction_x = OI.joystick.getX(Hand.kLeft);
    	double direction_y = OI.joystick.getY(Hand.kLeft);
    	double rotation    = OI.joystick.getX(Hand.kRight);
    	if(direction_x<DEADZONE&&direction_x>-DEADZONE) {
    		direction_x = 0;
    	}
    	if(direction_y<DEADZONE&&direction_y>-DEADZONE) {
    		direction_y = 0;
    	}
    	if(rotation<DEADZONE&&rotation>-DEADZONE) {
    		rotation = 0;
    	}
    	System.out.println("X: " + Robot.navx.getDisplacementX());
    	System.out.println("Y: " + Robot.navx.getDisplacementY());
    	System.out.println("Z: " + Robot.navx.getDisplacementZ());
    	Robot.drivetrain.getDriveTrain().driveCartesian(direction_y, direction_x, -rotation);
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
