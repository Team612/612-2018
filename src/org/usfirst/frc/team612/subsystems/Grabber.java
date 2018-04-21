
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
	
	/* The solenoid used for the grabber is a double solenoid; this means that it can be
	 * set with kFoward (grabber engaged), kReverse (grabber disengaged), or kOff (no power
	 * applied to solenoid). The default position should be kOff; otherwise, the solenoid
	 * will continue to use air pressure even when not necessary.
	 */
	
	/* Although we want the grabber to continually function during the match,
	 * all of the functionality is handled inside OI with button mapping. So,
	 * there is no default command for the grabber.
	 */
    public void initDefaultCommand() {
        
    }
    /**
     * Gets a Solenoid.
     * @param num Used to determine what Solenoid to return.
     * @return solenoid_R
     * @return solenoid_L
     */
    public DoubleSolenoid getSolenoid() {
    	return solenoid_G;
    	// Allows us to access the doublesolenoid while keeping it private
    }

}
