package org.usfirst.frc.team612.commands.pneumatic;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;
/**
 *
 */
public class DefaultDropper extends Command {

    public DefaultDropper() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.dropper); // Prevents two commands from sending two different instructions to same part at the same time
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.DROPPER_POS) { // Figure out what position the dropper is in, and toggle it
    		Robot.dropper.getSolenoid().set(Value.kReverse);
    	} else {
    		Robot.dropper.getSolenoid().set(Value.kForward);	
    	}
    	OI.DROPPER_POS = !OI.DROPPER_POS; // Then update the variable
     }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true; // Only has to be run once, so immediately returns true
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
