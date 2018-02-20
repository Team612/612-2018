package org.usfirst.frc.team612.commands.drive;


import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team612.commands.autonomous.RecordMovement;
import org.usfirst.frc.team612.robot.OI;
/**
 *
 */
public class DefaultDrive extends Command {
	
	double DEADZONE = 0.07;
	double prev_magnitude = 0;
	double rate = 0.05;
	public static double magnitude;
	public static double angle;
	public static double rotation;
	double direction_x;
	double direction_y;
	double yaw;
	boolean single_wheel = false;
	int id = 1;
	//double ROTATION_SCALE = 40;
	//double PROPORTION_VALUE = 3;
	
	/**
	 * Makes sure that that subsystem <code>drivetrain</code> is required.
	 */
    public DefaultDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }
    
    /**
     * Called just before this Command runs the first time.
     * @deprecated This Command does <b>absolutely nothing</b>.
     */
    // Called just before this Command runs the first time
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
    
    protected void singleWheel() {
    	Robot.drivetrain.getTalon((id%4)+1).set(OI.driver.getX(Hand.kLeft));
    	if(OI.driver_button_X.get()) {
    		id++;
    	}
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.driver_button_BCK.get()) { // Toggle control mode
    		single_wheel = !single_wheel;
    	}
    	if(single_wheel) {
    		singleWheel();
    		return; // Don't execute the rest of this function
    	}
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

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Makes sure <code>execute()</code> runs as long as this command returns <code>false</code>.
     * @return A <code>false</code> value, indicating that this command is running <code>execute()</code>.
     */
    protected boolean isFinished() {
        return false;
    }
    
    /**
     * Does Cartesian Drive when <code>isFinished()</code> returns <code>true</code>.
     */
    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.getDriveTrain().driveCartesian(0,  0,  0);
    }
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     * Does Cartesian Drive.
     */
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.getDriveTrain().driveCartesian(0,  0,  0);
    }
}
