package org.usfirst.frc.team612.commands;

import org.usfirst.frc.team612.robot.Robot;
import org.usfirst.frc.team612.robot.OI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OmniDrive extends Command {
private static double deadzone = .3;
    public OmniDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double right= OI.joystick.getY(Hand.kRight);
    	double left = OI.joystick.getY(Hand.kLeft);
    	if (right +deadzone >= left ||right  -deadzone <= left) {
    		Robot.drivetrain.getTalon(2).set(right);
    		Robot.drivetrain.getTalon(1).set(left);
    		Robot.drivetrain.getTalon(3).set(0);
       		Robot.drivetrain.getTalon(4).set(0);
       		} else {
    		Robot.drivetrain.getTalon(2).set(right);
    		Robot.drivetrain.getTalon(3).set(right);
    		Robot.drivetrain.getTalon(1).set(left);
    		Robot.drivetrain.getTalon(4).set(left);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
