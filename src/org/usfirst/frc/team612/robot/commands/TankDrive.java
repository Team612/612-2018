package org.usfirst.frc.team612.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team612.robot.Robot;

/**
 *
 */
public class TankDrive extends Command {
	public TankDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		/*if(Robot.oi.bongoC().getRawButton(1)) {
			Robot.drivetrain.Move(.4);
		}
		else {
			Robot.drivetrain.Move(0);
		}*/
		//for(int i = 1;i <= 16;i++) {
		//	if(Robot.oi.bongoC().getRawButton(i)) {
		//		System.out.println(i);
		//	}
	//	}
	   Robot.drivetrain.Move(Robot.oi.Driver().getY(Hand.kLeft));
		}
	

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
