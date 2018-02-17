package org.usfirst.frc.team612.commands;

import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climber_Two_Up extends Command {

	/**
	 * Is supposed to require something.
	 * @deprecated Besides a few comments, <code>Climber_Two_Up</code> has no code whatsoever in its body.
	 */
    public Climber_Two_Up() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    /**
     * Called just before <code>Climber_Two_Up</code> runs the first time.
     * @deprecated <code>initialize()</code> does nothing in Climber_Two_Up.java.
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
     * Gets Climber2, and sets its PWM value to 0.5, 60 times every second.
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.getCLimber2().set(.5);
    }

    /**
     * <code>isFinished()</code> will stop <code>execute()</code> when it returns <code>true</code>.
     * In Climber_Two_Up.java, <code>isFinished()</code> returns <code>false</code>.
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called once after <code>isFinished()</code> returns <code>true</code>.
     * @deprecated <code>end()</code> does nothing in Climber_Two_Up.java.
     */
    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     * @deprecated <code>interrupted()</code> does nothing in Climber_Two_Up.java.
     */
    protected void interrupted() {
    }
}
