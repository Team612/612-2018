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
	
	final double DEADZONE = 0.07; // For joystick
	double prev_magnitude = 0;
	double rate = 0.05; // Used for ramp-up/ramp-down; currently not used
	public static double magnitude; // later contains final mangitude (0 to 1) of motion
	public static double angle; // Later contains final angle (0 is right, 180 is left) of motion
	public static double rotation; // Later contains final rate of ratation (angular velocity)
	double direction_x; // Speed in x direction
	double direction_y; // Speed in y direction; these two used to calculate angle/magnitude
	double yaw; // Yaw is angle of the robot from the navx, not used
	boolean single_wheel = false; // Had debug-mode to control single wheel; not used
	double prev_x = 0; // Used for ramp-up/rampdown; currently not used
	double prev_y = 0; // Used for ramp-up/rampdown; currently not used
	
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
    
    protected void getInput() { // Sets variables with numbers from joystick
    	if(OI.XBOX) {
       	 	direction_x = OI.driver.getX(Hand.kLeft); // Left joysick, x direction
       	 	direction_y = OI.driver.getY(Hand.kLeft); // Left joystick, y direction
       	 	rotation    = OI.driver.getX(Hand.kRight); // Right joystick, x direction
       	}else { // If we use the flightstick
           	 direction_x = OI.joy.getX();
           	 direction_y = OI.joy.getY();
           	 rotation = OI.joy.getTwist();
       	}
    }
    
    protected void doDeadzone() {
    	/* If any variable is between -DEADZONE and DEADZONE (i.e. close to 0)
    	 * assume that we don't want to move and set it to 0
    	 */
    	if(direction_x < DEADZONE && direction_x > -DEADZONE) {
    		direction_x = 0;
    	}
    	if(direction_y < DEADZONE && direction_y > -DEADZONE) {
    		direction_y = 0;
    	}
    	if(rotation < DEADZONE && rotation > -DEADZONE) {
    		rotation = 0;
    	}
    	
    }
    
    protected void toPolar() {
    	magnitude = Math.sqrt(direction_x*direction_x+direction_y*direction_y); // Magnitude is pythagorean theorem
    	if(magnitude > 1.0) { // And make sure it doesn't get too big
    		magnitude = 1.0;
    	}
    	// Angle is calculated direction_y, direction_x as legs of a triangle, angle is the one they form
    	angle = Math.atan2(direction_y, direction_x)*180/Math.PI;
    	/* yaw = Robot.navx.getYaw();
    	if(OI.DRIVER_PERSPECTIVE) {
    		angle = angle-yaw;
    	} */
    }
    /**
     * Called 60 times per second when this Command is scheduled to run.
     * Gets information from Xbox controller.
     */
    
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.TANKDRIVE) { // Usually won't run; if so, we set talons directly
	    	Robot.drivetrain.getTalon(1).set(Robot.oi.driver.getY(Hand.kLeft));
			Robot.drivetrain.getTalon(4).set(Robot.oi.driver.getY(Hand.kLeft));
			Robot.drivetrain.getTalon(2).set(Robot.oi.driver.getY(Hand.kRight));
			Robot.drivetrain.getTalon(3).set(Robot.oi.driver.getY(Hand.kRight));
    	}
    	else  {
	    	getInput(); // Broke these up into
	    	
	    	doDeadzone(); // Multiple functions
	    	
	    	toPolar(); // To make readable
	    	
	    	/* if(OI.FIX_DRIFT) {
	    		double yaw_velocity = Robot.navx.getRate();
	    		Robot.drivetrain.getDriveTrain().drivePolar(magnitude, angle, rotation+(rotation-yaw_velocity));
	    	}
	    	else{
	    		Robot.drivetrain.getDriveTrain().drivePolar(magnitude, angle, rotation);
	    	} */
	    	RecordMovement.magnitude = magnitude; // These are for recording; even though we don't always
	    	RecordMovement.angle = angle; 		  // Record, we always set them just so that 
	    	RecordMovement.rotation = rotation;   // We have to change less if we are recording
	    	
	    	Robot.drivetrain.getDriveTrain().drivePolar(magnitude, angle, rotation); // Actually move the drivetrain
    	}
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
    	// This shouldn't run, but it if does, just stop robot
    	Robot.drivetrain.getDriveTrain().driveCartesian(0,  0,  0);
    }
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     * Does Cartesian Drive.
     */
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// This shouldn't run, but if it does, just stop robot
    	Robot.drivetrain.getDriveTrain().driveCartesian(0,  0,  0);
    }
}
