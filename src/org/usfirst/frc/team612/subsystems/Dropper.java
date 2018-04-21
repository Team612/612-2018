package org.usfirst.frc.team612.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc.team612.robot.RobotMap;

/**
 *
 */
public class Dropper extends Subsystem {
	/* This is the subsystem used for the dropper. The dropper is only used at the start of
	 * the match. The dropper brings up the lift arm, keeping it within the bounds of the 
	 * robot; when the match starts the dropper disengages and the arm drops, and the arm
	 * stays dropped for the whole match. The dropper can be manually controlled in case
	 * any errors occur
	 */
	DoubleSolenoid solenoid_drop = new DoubleSolenoid(RobotMap.PCM_solenoid_D,RobotMap.solenoid_D_one,RobotMap.solenoid_D_two);
	/* The solenoid used for the dropper is a double solenoid; this means that it can be
	 * set with kFoward (dropper engaged), kReverse (dropper disengaged), or kOff (no power
	 * applied to solenoid). The default position should be kOff; otherwise, the solenoid
	 * will continue to use air pressure even when not necessary.
	 */
    public void initDefaultCommand() {
    	/* Since the dropper is only used at the start of the match, we don't want any functionality
    	 * during the match, so there is no default command
    	 */
    	
    }
    public DoubleSolenoid getSolenoid() {
    	return solenoid_drop;
    	// Allows us to access the doublesolenoid while keeping it private within the class
    }
}

