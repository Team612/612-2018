package org.usfirst.frc.team612.commands;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class OmniDrive extends Command {
	private  static double ratio = 0.9;
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
    	double right = OI.driver.getY(Hand.kRight) * -1;
    	double left = OI.driver.getY(Hand.kLeft) * -1;
    	if(right == 0) right=0.01;
    	if(left == 0) left=0.01;
    	//SmartDashboard.putNumber("right",  right);
    	//SmartDashboard.putNumber("left", left);
    	if (right/left < ratio || left/right < 1/ratio) {
    		SmartDashboard.putBoolean("inIf", true);
    		Robot.drivetrain.getTalon(2).set(right);
    		Robot.drivetrain.getTalon(1).set(left);
    		Robot.drivetrain.getTalon(3).neutralOutput();
       		Robot.drivetrain.getTalon(4).neutralOutput();
       		} else {
       		SmartDashboard.putBoolean("inIf", false);
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
