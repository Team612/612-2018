package org.usfirst.frc.team612.commands.drive;


import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team612.commands.autonomous.RecordMovement;
import org.usfirst.frc.team612.robot.OI;
/**
 *
 */
public class DefaultDrive extends Command {
	
	final double DEADZONE = 0.07;
	double prev_magnitude = 0;
	double rate = 0.05;
	public static double magnitude;
	public static double angle;
	public static double rotation;
	final double RUMBLE_DEAD = 0.2;
	double direction_x;
	double direction_y;
	double yaw;
	boolean single_wheel = false;
	double prev_x = 0;
	double prev_y = 0;
	
    public DefaultDrive() {
    	requires(Robot.drivetrain);
    }
    protected void initialize() {
    	
    }
    
    protected void getInput() {
    	if(OI.XBOX) {
       	 	direction_x = OI.driver.getX(Hand.kLeft);
       	 	direction_y = OI.driver.getY(Hand.kLeft);
       	 	rotation    = OI.driver.getX(Hand.kRight); // This has to be inverted
       	}else {
           	 direction_x = OI.joy.getX();
           	 direction_y = OI.joy.getY();
           	 rotation = OI.joy.getTwist();
       	}
    }
    
    protected void doDeadzone() {
    	if(direction_x<DEADZONE&&direction_x>-DEADZONE) {
    		direction_x = 0;
    	}
    	if(direction_y<DEADZONE&&direction_y>-DEADZONE) {
    		direction_y = 0;
    	}
    	if(rotation<DEADZONE&&rotation>-DEADZONE) {
    		rotation = 0;
    	}
    	
    }
    
    protected void toPolar() {
    	magnitude = Math.sqrt(direction_x*direction_x+direction_y*direction_y);
    	if(magnitude > 1.0) {
    		magnitude = 1.0;
    	}
    	angle = Math.atan2(direction_y, direction_x)*180/Math.PI;
    	yaw = Robot.navx.getYaw();
    	if(OI.DRIVER_PERSPECTIVE) {
    		angle = angle-yaw;
    	}
    }
    /**
     * Called 60 times per second when this Command is scheduled to run.
     * Gets information from Xbox controller.
     */
    protected void execute() {
    	if(OI.TANKDRIVE) {
	    	Robot.drivetrain.getTalon(1).set(Robot.oi.driver.getY(Hand.kLeft));
			Robot.drivetrain.getTalon(4).set(Robot.oi.driver.getY(Hand.kLeft));
			Robot.drivetrain.getTalon(2).set(Robot.oi.driver.getY(Hand.kRight));
			Robot.drivetrain.getTalon(3).set(Robot.oi.driver.getY(Hand.kRight));
    	}
    	else  {
    	getInput();
    	
    	doDeadzone();
    	
    	toPolar();
    	
    	if(OI.FIX_DRIFT) {
    		double yaw_velocity = Robot.navx.getRate();
    		Robot.drivetrain.getDriveTrain().drivePolar(magnitude, angle, rotation+(rotation-yaw_velocity));
    	}
    	else{
    		Robot.drivetrain.getDriveTrain().drivePolar(magnitude, angle, rotation);
    	}
    	RecordMovement.magnitude = magnitude;
    	RecordMovement.angle = angle;
    	RecordMovement.rotation = rotation;
    	
    	Robot.drivetrain.getDriveTrain().drivePolar(magnitude, angle, rotation);
    	prev_x = direction_x;
    	prev_y = direction_y;
    	double delta_x = (prev_x-direction_x)/(1/60.0);
    	double delta_y = (prev_y-direction_y)/(1/60.0);
    	double diff_x = Robot.navx.getWorldLinearAccelX()-delta_x;
    	double diff_y = Robot.navx.getWorldLinearAccelY()-delta_y;
    	if(Math.abs(diff_x) > RUMBLE_DEAD || Math.abs(diff_y) > RUMBLE_DEAD) {
    		//OI.driver.setRumble(RumbleType.kLeftRumble, 0.5);
    		//OI.driver.setRumble(RumbleType.kRightRumble, 0.5);
    	}
    	}
    	/*if(magnitude > prev_magnitude) {
    		if(prev_magnitude+rate>magnitude) {
    			Robot.drivetrain.getDriveTrain().drivePolar(magnitude, angle, rotation);
    			prev_magnitude = magnitude;
    		}
    		prev_magnitude += rate;
    		Robot.drivetrain.getDriveTrain().drivePolar(prev_magnitude+rate, angle, rotation);
    	} else {
    		prev_magnitude = magnitude;
    		Robot.drivetrain.getDriveTrain().drivePolar(magnitude, angle, rotation);
    	}*/
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    	Robot.drivetrain.getDriveTrain().driveCartesian(0,  0,  0);
    }
    protected void interrupted() {
    	Robot.drivetrain.getDriveTrain().driveCartesian(0,  0,  0);
    }
}
