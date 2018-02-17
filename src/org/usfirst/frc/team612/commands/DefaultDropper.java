package org.usfirst.frc.team612.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team612.robot.Robot;
/**
 *
 */
public class DefaultDropper extends Command {

	/**
	 * Requires <code>dropper</code> in Robot.
	 */
    public DefaultDropper() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.dropper);
    }

    // Called just before this Command runs the first time
    /**
     * Called just before <code>DefaultDropper</code> runs the first time.
     */
    protected void initialize() {
    	
    }

    /**
     * Sets the dropper solenoid's value 60 times a second.
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dropper.getSolenoid().set(!Robot.dropper.getSolenoid().get());
        }

    /**
     * <code>isFinished()</code> will stop <code>execute()</code> when it returns <code>true</code>.
     * In DefaultDropper.java, <code>isFinished()</code> returns <code>false</code>.
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    /**
     * Called once after <code>isFinished()</code> returns <code>true</code>.
     * @deprecated <code>end()</code> does nothing in DefaultDropper.java.
     */
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     * @deprecated <code>interrupted()</code> does nothing in DefaultDropper.java.
     */
    protected void interrupted() {
    }
}
