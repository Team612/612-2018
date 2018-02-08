package org.usfirst.frc.team612.commands;

import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetDisplacement extends Command {

	/**
	 * Does nothing (?)
	 */
    public ResetDisplacement() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    /**
     * Called just before <code>ResetDisplacement()</code> runs the first time.</code>
     * @deprecated <code>initialize</code> does <b>absolutely nothing</b> in ResetDisplacement.java.
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
     * Resets displacement?
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.navx.resetDisplacement();
    }

    /**
     * Stops <code>execute()</code>
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }
    
    /**
     * Called once after <code>isFinished()</code> returns <code>true</code>.
     * @deprecated <code>end()</code> does <b>absolutely nothing</b> in ResetDisplacement.java.
     */
    // Called once after isFinished returns true
    protected void end() {
    }
    
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     * @deprecated <code>interruped()</code> does <b>absolutely nothing</b> in ResetDisplacement.java.
     */
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
