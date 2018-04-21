package org.usfirst.frc.team612.commands.lift;

import org.usfirst.frc.team612.commands.autonomous.RecordMovement;
import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultLift extends Command {
	public static int MAX = 500;
    private int DEADZONE = 100; // How close the encoder can be to the target before it stops moving
    private int VEL_DEADZONE = 10; // How close the encoder can be moving to 0; used to calculate stalling
    private double  MOTOR_DEADZONE = 0.1; // How close motor power has to be AWAY from 0 to say it is trying to move; used to calculate stalling

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
     *  <code>initialize()</code> does <b++>absolutely nothing</b> in DefaultGrabber.java.
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
     * Sets talon to value from joystick.
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.LIFT_PID) { // If we ARE using PID
    		// TODO: fix all the static stuff
	    	if(Math.abs(Robot.oi.gunner.getY(Hand.kLeft)) > 0.1){ // If we're trying to move
			    if (Robot.lift.getTalon().getSensorCollection().isFwdLimitSwitchClosed()
			     || Robot.lift.getTalon().getSensorCollection().isRevLimitSwitchClosed()) { // If either limit switch is closed
	    			if(Robot.lift.getTalon().getSensorCollection().isFwdLimitSwitchClosed()
	    			&& Robot.oi.gunner.getY(Hand.kLeft) > 0) { // If the forward one is closed and we're trying to go down
	    	    		Robot.lift.target = 0; // Don't move the target any lower
	    	    		Robot.lift.getTalon().getSensorCollection().setQuadraturePosition(0, 0); // And recallibrate the encoder just to be safe
	    			}
	    			else if(Robot.lift.getTalon().getSensorCollection().isRevLimitSwitchClosed()
	    				 && Robot.oi.gunner.getY(Hand.kLeft) < 0) { // Else if it's the reverse limit switch and we're going up
	    	    		
	    				Robot.lift.target = Robot.lift.target; // Keep the target the same
	    			}
	    			else { // If we're at one limit switch but trying to leave it
		    			Robot.lift.target += (Robot.oi.gunner.getY(Hand.kLeft)*320); // Move the target by the input times some constant
		    			//Robot.lift.target += (Robot.oi.gunner.getY(Hand.kLeft)*200) * Robot.encoder_multi;
	    			}
		    	} else { // If no limit switch is closed
	    			Robot.lift.target += (Robot.oi.gunner.getY(Hand.kLeft)*320); // Move the target by the input times some constant
	    			//Robot.lift.target += (Robot.oi.gunner.getY(Hand.kLeft)*200) * Robot.encoder_multi;
	    		}
	    	} else {
	    		Robot.lift.target = Robot.lift.target; // Else we're inside the deadzone; don't move
	    	}
	    	
	    	if(Math.abs(Robot.lift.getTalon().getSelectedSensorPosition(0) - Robot.lift.target) > DEADZONE) { // If the encoder position is outside deadzone
	    		Robot.lift.getTalon().set(ControlMode.Position, Robot.lift.target); // Set the talon in position mode
	    	} else { // Otherwise set it to current position so it doesn't move
	    		Robot.lift.getTalon().set(ControlMode.Position, Robot.lift.getTalon().getSelectedSensorPosition(0));
	    	
	    	}
    	} else { // If we AREN'T using PID
    		Robot.lift.getTalon().set(ControlMode.PercentOutput, OI.gunner.getY( Hand.kLeft)); // Set the talon in PercentMode with the controller
    	}
    	// Check if motor is stalled
    	if(Math.abs(Robot.lift.getTalon().getSelectedSensorVelocity(0)) < VEL_DEADZONE) {
    		// If the lift isn't moving
    		if(Math.abs(Robot.lift.getTalon().get()) > MOTOR_DEADZONE) {
    			OI.IS_MOTOR_STALLED = true;
    		}
    		
    	} else {
    		OI.IS_MOTOR_STALLED = false;
    	}
	    	
    }
    	

    /**s
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




















/*if(OI.BONGO_MODE) {
if(OI.bongo_button_Right_F.get()) {
	Robot.lift.target -= 10;
	Robot.lift.getTalon().set(ControlMode.Position, Robot.lift.target);
}
else if(OI.bongo_button_Right_B.get()) {
	Robot.lift.target += 10;
	Robot.lift.getTalon().set(ControlMode.Position, Robot.lift.target);
}
else {
	Robot.lift.getTalon().set(ControlMode.Position, Robot.lift.getTalon().getSelectedSensorPosition(0));
}
}*/