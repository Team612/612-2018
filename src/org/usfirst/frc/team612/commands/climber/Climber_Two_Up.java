package org.usfirst.frc.team612.commands.climber;

import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climber_Two_Up extends Command {

    public Climber_Two_Up() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.dropper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.getClimber(2).set(.5);
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
