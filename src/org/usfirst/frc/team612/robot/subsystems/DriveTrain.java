package org.usfirst.frc.team612.robot.subsystems;

import org.usfirst.frc.team612.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void Move(double voltage ) {
    	RobotMap.left.set(voltage);
    	RobotMap.right.set(voltage);

    }
}

