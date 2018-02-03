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
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
	public static int talon_FR = 0;
	public static int talon_FL = 1;
	public static int talon_RR = 5;
	public static int talon_RL = 3;
	public static int talon_lift = 6;
	public static int gunner_port = 1;
	public static int driver_port = 2;
	public static int solenoid_R = 0;
	public static int solenoid_L = 1;
	public static int solenoid_C = 3;
	public static int talon_pivot = 10;


	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
