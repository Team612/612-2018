package org.usfirst.frc.team612.commands.lift;

import org.usfirst.frc.team612.robot.Robot;


import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultLift extends Command {
    	
	

    /**
     * Requires <code>lift</code>
     */
    public DefaultLift() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    	
    }
    
    /**
     * Called just before DefaultLift runs for the first time.
     *  <code>initialize()</code> does <b>absolutely nothing</b> in DefaultGrabber.java.
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
     * Sets talon to value from joystick.
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.getTalon().set(Robot.oi.gunner.getY(Hand.kLeft));
    }
    	

    /**
     * Won't stop <code>execute()</code>
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    
    /**
     * Called once after <code>isFinished()</code> returns <code>true</code>.
     *  <code>end()</code> does <b>absolutely nothing</b> in DefaultLift.java.
     */
    // Called once after isFinished returns true
    protected void end() {
    }
    
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     * @deprecated <code>interrupted()</code> does <b>absolutely nothing</b> in DefaultLift.java.
     */
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
