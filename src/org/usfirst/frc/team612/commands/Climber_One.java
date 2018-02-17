package org.usfirst.frc.team612.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team612.robot.Robot;
/**
 *
 */
public class Climber_One extends Command {
	
	/**
	 * Requires <code>climber</code> from Robot.
	 */
    public Climber_One() {
    	requires(Robot.climber);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    /**
     * Called just before <code>Climber_One</code> runs for the first time.
     * @deprecated <code>initialize()</code> does nothing in Climber_One.java
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Checks if <code>Robot.oi.gunner.getPOV()</code> is equal to 0 or 180.
     * If <code>Robot.oi.gunner.getPOV()</code> is equal to 0, it gets the first Climber, and sets its PWM value to 0.5.
     * If <code>Robot.oi.gunner.getPOV()</code> is equal to 180, it gets the first Climber, and sets its PWM value to -0.5.
     */
    protected void execute() {
    	if(Robot.oi.gunner.getPOV() ==0) {
    		Robot.climber.getClimber1().set(0.5);
    	}else if(Robot.oi.gunner.getPOV()==180) {
    		Robot.climber.getClimber1().set(-0.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * <code>isFinished()</code> will stop <code>execute()</code> when it returns <code>true</code>.
     * In Climber_One.java, <code>isFinished()</code> returns <code>false</code>.
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called once after <code>isFinished()</code> returns <code>true</code>.
     * @deprecated <code>end()</code> does nothing in Climber_One.java
     */
    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     * @deprecated <code>interrupted()</code> does nothing in Climber_One.java
     */
    protected void interrupted() {
    }
}
