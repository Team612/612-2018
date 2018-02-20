
package org.usfirst.frc.team612.commands.pneumatic;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultGrabber extends Command {
	private static boolean toggle = false;
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
    	if(OI.GRABBER_POS) {
    		Robot.grabber.getSolenoid().set(Value.kReverse);
    	} else  {
    		Robot.grabber.getSolenoid().set(Value.kForward);	
    	}
    	OI.GRABBER_POS = !OI.GRABBER_POS;
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
