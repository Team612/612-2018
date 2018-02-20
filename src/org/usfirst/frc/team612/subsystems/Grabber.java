
package org.usfirst.frc.team612.subsystems;

import org.usfirst.frc.team612.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {
	DoubleSolenoid solenoid_G = new DoubleSolenoid(RobotMap.PCM_solenoid_G,RobotMap.solenoid_G_one,RobotMap.solenoid_G_two);
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
    public DoubleSolenoid getSolenoid() {
    	return solenoid_G;
    }

}
