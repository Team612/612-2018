package org.usfirst.frc.team612.commands;

import java.io.IOException;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReplayRobot extends Command {
	/*
    public ReplayRobot() throws NumberFormatException, IOException {
    	for(String line; (line = OI.bf.readLine()) != null; ) {
    		String[] parts = line.split(",");
    		float driver_magnitude = Float.parseFloat(parts[0]);
    		float driver_angle = Float.parseFloat(parts[1]);
    		float driver_rotation = Float.parseFloat(parts[2]);
    		float seconds = Float.parseFloat(parts[3]);
    		double timer = OI.data_timer.get();
    		if (timer >= seconds) {
    		Robot.drivetrain.getDriveTrain().drivePolar(driver_magnitude, driver_angle, driver_rotation);
    		}
    	}
    }
    */

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
