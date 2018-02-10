package org.usfirst.frc.team612.commands;

import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class Turn extends Command {
	
	double initAngle;
	double changeAngle;
	boolean isDone = false;
	boolean clockWise;
	
    public Turn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//for changeAngle, a positive input means clockwise and a negative number means counter clockwise
    	initAngle = Robot.navx.getAngle();
    	changeAngle = 90.0;
    	if(changeAngle >= 0) {
    		clockWise = true;
    	}
    	else {
    		clockWise = false;
    	}
    	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(clockWise == true && Robot.navx.getAngle() < initAngle+changeAngle) {
    		Robot.drivetrain.getDriveTrain().driveCartesian(0,0,0.5);
    	}
    	else if(Robot.navx.getAngle > initAngle+changeAngle) {
    		Robot.drivetrain.getDriveTrain().driveCartesian(0,0,-0.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(clockWise == true && Robot.navx.getAngle() >= angle+setAngle) {
    		isDone = true;
    	}
    	if(clockWise == false && Robot.navx.getAngle() <= angle+setAngle) {
    		isDone = true;
    	}
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
