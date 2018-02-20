
package org.usfirst.frc.team612.commands.pneumatic;

import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultGrabber extends Command {
	private static int count =0;
	/**
	 * Requires a <code>grabber</code>
	 */
    public DefaultGrabber() {
    	
    	requires(Robot.grabber);
    }
    /**
     * Called just before DefaultGrabber runs the first time.
     * @deprecated <code>initialize()</code> does <b>absolutely nothing</b> in DefaultGrabber.java.
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
     * Gets solenoids
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (count == 1) {
    		Robot.grabber.getSolenoid().set(Value.kOff);
    	}
    	count =0;

    	if(Robot.grabber.getSolenoid().get() == Value.kForward) {
    		Robot.grabber.getSolenoid().set(Value.kReverse);
    		count =1;
    	} else if (Robot.grabber.getSolenoid().get() == Value.kReverse) {
    		Robot.grabber.getSolenoid().set(Value.kForward);	
    		count =1;
    	}
    	else {
    		Robot.grabber.getSolenoid().set(Value.kForward);
    		count =1;
    	}
    	
    	
    	
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
     * @deprecated <code>end()</code> does <b>absolutely nothing</b> in DefaultGrabber.java.
     */
    // Called once after isFinished returns true
    protected void end() {
    }
    
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     * @deprecated <code>interrupted()</code> does <b>absolutely nothing</b> in DefaultGrabber.java.
     */
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
