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
    	requires(Robot.dropper);
    }
    protected void initialize() {
    	
    }
    protected void execute() {
    	if(OI.DROPPER_POS) {
    		Robot.dropper.getSolenoid().set(Value.kReverse);
    	} else {
    		Robot.dropper.getSolenoid().set(Value.kForward);	
    	}
    	OI.DROPPER_POS = !OI.DROPPER_POS;
     }
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    }
    protected void interrupted() {
    }
}
