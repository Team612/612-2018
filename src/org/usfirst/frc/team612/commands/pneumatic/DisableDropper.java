package org.usfirst.frc.team612.commands.pneumatic;

import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DisableDropper extends Command {

    public DisableDropper() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.dropper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dropper.getSolenoid().set(Value.kOff); // Set dropper to kOff so it doesn't waste air pressure
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true; // Disabling the dropper happens immediately, so return true
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
