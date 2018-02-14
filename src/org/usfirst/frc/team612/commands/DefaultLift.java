package org.usfirst.frc.team612.commands;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.subsystems.Lift;
import org.usfirst.frc.team612.robot.Robot;
import org.usfirst.frc.team612.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultLift extends Command {
    	
	int height;
	final int DEADZONE = 500;
	boolean done;
	
    /**
     * Requires <code>lift</code>
     */
    public DefaultLift(int h) {
    	height = h;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    	
    }
    
    /**
     * Called just before DefaultLift runs for the first time.
     * @deprecated <code>initialize()</code> does <b>absolutely nothing</b> in DefaultGrabber.java.
     */
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
     * Sets talon to value from joystick.
     */
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

    /**
     * Won't stop <code>execute()</code>
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }
    
    /**
     * Called once after <code>isFinished()</code> returns <code>true</code>.
     * @deprecated <code>end()</code> does <b>absolutely nothing</b> in DefaultLift.java.
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
