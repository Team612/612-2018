package org.usfirst.frc.team612.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team612.robot.Robot;
/**
 *
 */
public class Climber_One extends Command {

    public Climber_One() {
    	requires(Robot.climber);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.gunner.getPOV() ==0) {
    		Robot.climber.getClimber(1).set(0.5);
    	}else if(Robot.oi.gunner.getPOV()==180) {
    		Robot.climber.getClimber(1).set(-0.5);
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
