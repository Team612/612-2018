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

	public static int gunner_port = 1;
	public static int driver_port = 0;
	public static int talon_FR = 3;
	public static int talon_FL = 1;
	public static int talon_RR = 4;
	public static int talon_RL = 2;
	public static int talon_lift = 5;
	public static int solenoid_G = 1;//placeholder
	public static int solenoid_P = 3;//placeholder
  public static int climber_1 = 7;
  public static int climber_2 = 8;
	public static int solenoid_D = 6;//placeholder = 01100100 01100101 01100101 01111010 00100000 01101110 01110101 01110100 01110011
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
