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
    private int DEADZONE = 100;
    //private int target = -10000;

	

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
    	/*if(deadzone < Robot.oi.gunner.getY(Hand.kLeft) && -deadzone  > Robot.oi.gunner.getY(Hand.kLeft)) {
    		move = 0;
    	}
    	else {
    		move = Robot.oi.gunner.getY(Hand.kLeft) * .8;
    	}
    	Robot.lift.getTalon().set(move);*/
    	/*if(Robot.lift.getTalon().getSensorCollection().isFwdLimitSwitchClosed()) {
    		 Robot.lift.getTalon().getSensorCollection().setQuadraturePosition(0, 0);
    		 Robot.lift.target =10;
    		 
    	}
    	else if(Robot.lift.getTalon().getSensorCollection().isRevLimitSwitchClosed()) {
    		MAX = Robot.lift.getTalon().getSelectedSensorPosition(0);
    		//Robot.lift.getTalon().getSensorCollection().setQuadraturePosition(MAX, 0);
    		
    	}*/

    	if(OI.LIFT_PID) {
    	
	    	if(Math.abs(Robot.oi.gunner.getY(Hand.kLeft)) > 0.1 ){
			    if (Robot.lift.getTalon().getSensorCollection().isFwdLimitSwitchClosed() || Robot.lift.getTalon().getSensorCollection().isRevLimitSwitchClosed()) {
	    			if(Robot.lift.getTalon().getSensorCollection().isFwdLimitSwitchClosed() && Robot.oi.gunner.getY(Hand.kLeft) > 0) {
	    	    		Robot.lift.target = Robot.lift.target;
	    			}
	    			else if(Robot.lift.getTalon().getSensorCollection().isRevLimitSwitchClosed() && Robot.oi.gunner.getY(Hand.kLeft) < 0) {
	    	    		Robot.lift.target = Robot.lift.target;
	    			}
	    			else {
		    			Robot.lift.target += (Robot.oi.gunner.getY(Hand.kLeft)*300) ;
		    			//Robot.lift.target += (Robot.oi.gunner.getY(Hand.kLeft)*200) * Robot.encoder_multi;
	    			}
		    		}
	    		else {
	    			Robot.lift.target += (Robot.oi.gunner.getY(Hand.kLeft)*300);
	    			//Robot.lift.target += (Robot.oi.gunner.getY(Hand.kLeft)*200) * Robot.encoder_multi;
	    		}
	    	} else {
	    		Robot.lift.target = Robot.lift.target;
	    	}
	    	
	    	if(Math.abs(Robot.lift.getTalon().getSelectedSensorPosition(0) - Robot.lift.target) > DEADZONE) {
	    		Robot.lift.getTalon().set(ControlMode.Position, Robot.lift.target);
	    	} else {
	    		Robot.lift.getTalon().set(ControlMode.Position, Robot.lift.getTalon().getSelectedSensorPosition(0));
	    	
	    }
    	}else {
    		Robot.lift.getTalon().set(OI.gunner.getY(Hand.kLeft));
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