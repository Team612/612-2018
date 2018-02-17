package org.usfirst.frc.team612.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc.team612.robot.RobotMap;

/**
 *
 */
public class Dropper extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Solenoid solenoid_drop = new Solenoid(RobotMap.solenoid_D);

	/**
	 * Sets the default command for Dropper.java.
	 * @deprecated <code>initDefaultCommand()</code> does nothing in Dropper.java
	 */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Gets the dropper solenoid.
     * @return Dropper solenoid.
     */
    public Solenoid getSolenoid() {
    	return solenoid_drop;
    }
}

