package org.usfirst.frc.team612.commands.lift;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TogglePID extends Command {

    public TogglePID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	OI.LIFT_PID = false;
    	//OI.LIFT_PID = OI.LIFT_PID ? false : true;
    		System.out.println("PID Disabled");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.target = Robot.lift.getTalon().getSelectedSensorPosition(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
