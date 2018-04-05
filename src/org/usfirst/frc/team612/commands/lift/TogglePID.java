package org.usfirst.frc.team612.commands.lift;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TogglePID extends Command {

    public TogglePID() {
    }
    protected void initialize() {
    }
    protected void execute() {
    	OI.LIFT_PID = false;
    	System.out.println("PID Disabled");
    }
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    	Robot.lift.target = Robot.lift.getTalon().getSelectedSensorPosition(0);
    }
    protected void interrupted() {
    }
}
