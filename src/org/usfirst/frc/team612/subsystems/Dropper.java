package org.usfirst.frc.team612.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc.team612.robot.RobotMap;

/**
 *
 */
public class Dropper extends Subsystem {
	Solenoid solenoid_drop = new Solenoid(RobotMap.solenoid_D_one,RobotMap.solenoid_D_two);
    public void initDefaultCommand() {
    	
    }
    public Solenoid getSolenoid() {
    	return solenoid_drop;
    }
}

