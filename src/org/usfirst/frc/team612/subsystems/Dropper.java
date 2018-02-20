package org.usfirst.frc.team612.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc.team612.robot.RobotMap;

/**
 *
 */
public class Dropper extends Subsystem {
	DoubleSolenoid solenoid_drop = new DoubleSolenoid(RobotMap.PCM_solenoid_D,RobotMap.solenoid_D_one,RobotMap.solenoid_D_two);
    public void initDefaultCommand() {
    	
    }
    public DoubleSolenoid getSolenoid() {
    	return solenoid_drop;
    }
}

