package org.usfirst.frc.team612.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	public static int bongo_port 	= 2; // Laptop USB port used for bongos
	public static int gunner_port   = 1; // Laptop USB port used for the gunner (lift) xbox controller
	public static int driver_port 	= 0; // Laptop USB port used for the driver xbox controller
	public static int talon_FR 		= 3; // Front right talon is plugged into port 3 on the PDP
	public static int talon_FL 		= 1; // Front left talon is plugged into port 1 on the PDP
	public static int talon_RR 		= 4; // Rear right talon is plugged into port 4 on the PDP
	public static int talon_RL 		= 2; // Rear left talon is plugged into port 2 on the PDP
	public static int talon_lift 	= 5; // The talon used for the lift is plugged into port 5 on the PDP
										 // Above are all the talons we need
    public static int climber_1 	= 6; // TODO: Rename/remove. Port for deprecated climber
    public static int climber_2 	= 7; // TODO: rename/remove. Another port for deprecated climber
    public static int PCM_solenoid_G= 1; // Module number for DoubleSolenoid used on the grabber
    public static int PCM_solenoid_D= 1; // Module number for DoubleSolenoid used on the dropper
	public static int solenoid_G_one= 0; // TODO: Rename. Forward channel number for grabber
	public static int solenoid_G_two= 1; // TODO: Rename. Reverse channel number for grabber
	public static int solenoid_D_one= 2; // TODO: Rename. Forward channel number for dropper
	public static int solenoid_D_two= 3; // TODO: Rename. Reverse channel number for dropper

	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
}
