package org.usfirst.frc.team612.commands;

import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climber_Two_Down extends Command {

	/**
	 * Requires <code>climber</code> from Robot.
	 */
    public Climber_Two_Down() {
    	requires(Robot.climber);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    /**
     * Called just before <code>Climber_Two_Down</code> runs the first time.
     * @deprecated <code>initialize()</code> does nothing in Climber_Two_Down.java.
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
     * Gets Climber2, and sets its PWM value to -0.5, 60 times every second.
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.getCLimber2().set(-.5); //Why is it getCLimber() and not getClimber()?
    }

    /**
     * <code>isFinished()</code> will stop <code>execute()</code> when it returns <code>true</code>.
     * In Climber_Two_Down.java, <code>isFinished()</code> returns <code>false</code>.
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called once after <code>isFinished()</code> returns <code>true</code>.
     * @deprecated <code>end()</code> does nothing in Climber_Two_Down.java.
     */
    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     * @deprecated <code>interrupted()</code> does nothing in Climber_Two_Down.java.
     */
    protected void interrupted() {
    }
}
