package org.usfirst.frc.team612.subsystems;

import org.usfirst.frc.team612.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {

	Solenoid solenoid_R = new Solenoid(RobotMap.solenoid_R);
	Solenoid solenoid_L = new Solenoid(RobotMap.solenoid_L);
	
	/**
	 * Sets the default command for a subsystem.
	 * @deprecated <code>initDefaultCommand()</code> does <b>absolutely nothing</b> in Grabber.java.
	 */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    /**
     * Gets a Solenoid.
     * @param num Used to determine what Solenoid to return.
     * @return solenoid_R
     * @return solenoid_L
     */
    public Solenoid getSolenoid(int num) {
    	switch(num) {
    	case 1:
    		return solenoid_R;
    	case 2:
    		return solenoid_L;
    	default:
    		return null;
    	}
    
    }
}

