package org.usfirst.frc.team612.subsystems;

import org.usfirst.frc.team612.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PivotArm extends Subsystem {
	
	Talon pivot = new Talon(RobotMap.talon_pivot);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Talon getPivot() {
	return pivot;
	
}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

