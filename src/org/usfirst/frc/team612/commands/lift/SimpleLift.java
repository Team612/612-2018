package org.usfirst.frc.team612.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
/**
 *
 */
public class SimpleLift extends Command {
	int height;
	final int DEADZONE = 500;
	boolean done;
	
    /**
     * Requires <code>lift</code>
     */
  
    public SimpleLift(int h) {
    	height= h;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.LIFT_PID) {
    		Robot.lift.getTalon().set(ControlMode.Position,height);
    		done = true;
    	} else {
    		if(Math.abs(Robot.lift.getTalon().getSensorCollection().getQuadraturePosition()-height) > DEADZONE){
    			if(Robot.lift.getTalon().getSensorCollection().getQuadraturePosition() > height) {
    				Robot.lift.getTalon().set(-0.5);
    			} else {
    				Robot.lift.getTalon().set(0.5);
    			}
    		} else {
    			done = true;
    		}
    	}
    }
    
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
