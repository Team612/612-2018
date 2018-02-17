package org.usfirst.frc.team612.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team612.robot.Robot;

/**
 *
 */
public class AutoDrive extends Command {

	/**
	 * Requires <code>drivetrain</code> in Robot
	 */
    public AutoDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    /**
     * Called just before <code>AutoDrive</code> runs the first time.
     * Makes the Robot abort after 5 seconds.
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(5); // Timeout should go in initialize
    }

    /**
     * Starts a Cartesian Drive
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.getDriveTrain().driveCartesian(0.5,0,0);
    	}
    

    /**
     * Determines whether or not the
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    /**
     * Called once after <code>isFinished()</code> returns <code>true</code>.
     * @deprecated <code>end()</code> does nothing in AutoDrive.java.
     */
    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     * @deprecated <code>interrupted()</code> does nothing in AutoDrive.java.
     */
    protected void interrupted() {
    }
}
