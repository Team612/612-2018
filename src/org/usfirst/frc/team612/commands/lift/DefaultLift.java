package org.usfirst.frc.team612.commands.lift;

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
    private int VEL_DEADZONE = 10;
    private double  MOTOR_DEADZONE = 0.1;	
    public DefaultLift() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    	
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
     * Sets talon to value from joystick.
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.LIFT_PID) {
	    	if(Math.abs(Robot.oi.gunner.getY(Hand.kLeft)) > 0.1 ){
			    if (Robot.lift.getTalon().getSensorCollection().isFwdLimitSwitchClosed() || Robot.lift.getTalon().getSensorCollection().isRevLimitSwitchClosed()) {
	    			if(Robot.lift.getTalon().getSensorCollection().isFwdLimitSwitchClosed() && Robot.oi.gunner.getY(Hand.kLeft) > 0) {
	    	    		Robot.lift.target = 0;
	    	    		Robot.lift.getTalon().getSensorCollection().setQuadraturePosition(0, 0);
	    			}
	    			else if(Robot.lift.getTalon().getSensorCollection().isRevLimitSwitchClosed() && Robot.oi.gunner.getY(Hand.kLeft) < 0) {
	    	    		
	    				Robot.lift.target = Robot.lift.target;
	    			}
	    			else {
		    			Robot.lift.target += (Robot.oi.gunner.getY(Hand.kLeft)*320) ;
		    			//Robot.lift.target += (Robot.oi.gunner.getY(Hand.kLeft)*200) * Robot.encoder_multi;
	    			}
		    		}
	    		else {
	    			Robot.lift.target += (Robot.oi.gunner.getY(Hand.kLeft)*320);
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
    		Robot.lift.getTalon().set(ControlMode.PercentOutput, OI.gunner.getY( Hand.kLeft));
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
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    }
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