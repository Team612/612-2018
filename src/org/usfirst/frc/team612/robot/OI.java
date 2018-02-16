package org.usfirst.frc.team612.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import java.io.IOException;
import org.usfirst.frc.team612.commands.Climber_Two_Down;
import org.usfirst.frc.team612.commands.Climber_Two_Up;
import org.usfirst.frc.team612.commands.RecordMovement;
import org.usfirst.frc.team612.commands.ReplayRobot;
import org.usfirst.frc.team612.commands.DefaultDropper;
import org.usfirst.frc.team612.commands.DefaultGrabber;
import org.usfirst.frc.team612.robot.RobotMap;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static final boolean XBOX = true;	
	public static final boolean OMNI = false;
	public static final boolean DRIVER_PERSPECTIVE = false;
	public static final boolean LIFT_PID = false;
	public static XboxController driver = new XboxController(RobotMap.driver_port);
	public static XboxController gunner = new XboxController(RobotMap.gunner_port);
	public static Joystick joy = new Joystick(1); // Flight controller

	public static JoystickButton driver_button_A    = new JoystickButton(driver,1);
	public static JoystickButton driver_button_B 	= new JoystickButton(driver,2);
	public static JoystickButton driver_button_X    = new JoystickButton(driver,3);
	public static JoystickButton driver_button_Y    = new JoystickButton(driver,4);
	public static JoystickButton driver_button_LB   = new JoystickButton(driver,5);
	public static JoystickButton driver_button_RB   = new JoystickButton(driver,6);
	public static JoystickButton driver_button_BCK  = new JoystickButton(driver,7);
	public static JoystickButton driver_button_STRT = new JoystickButton(driver,8);
	public static JoystickButton driver_button_LJ   = new JoystickButton(driver,9);
	public static JoystickButton driver_button_RJ   = new JoystickButton(driver,10);
	//end of driver
    public static JoystickButton gunner_button_A    = new JoystickButton(gunner, 1);
	public static JoystickButton gunner_button_B    = new JoystickButton(gunner, 2);
    public static JoystickButton gunner_button_X    = new JoystickButton(gunner,3);
	public static JoystickButton gunner_button_Y    = new JoystickButton(gunner,4);
	public static JoystickButton gunner_button_LB   = new JoystickButton(gunner,5);
	public static JoystickButton gunner_button_RB   = new JoystickButton(gunner,6);
	public static JoystickButton gunner_button_BCK  = new JoystickButton(gunner,7);
	public static JoystickButton gunner_button_STRT = new JoystickButton(gunner,8);
	public static JoystickButton gunner_button_LJ   = new JoystickButton(gunner,9);
	public static JoystickButton gunner_button_RJ   = new JoystickButton(gunner,10);
	
	public OI() throws IOException {
		gunner_button_A.whenPressed(new Climber_Two_Down());
		gunner_button_Y.whenPressed(new Climber_Two_Up());
		driver_button_A.whenPressed(new ReplayRobot());
		driver_button_B.whenPressed(new RecordMovement());
		gunner_button_RB.whenPressed(new DefaultGrabber());
		gunner_button_LB.whenPressed(new DefaultDropper());
	}
}
	/*	//create new file
		//file stored on robo-rio
		//start second timer
		data_timer.start();
		//store values of opening file to objects
		file_to_open = new File(directory + "/" + file_name_open);
		fr = new FileReader(file_to_open);
		bf = new BufferedReader(fr);*/
		//start second timer

 /* BUTTON MAPPING (this should go in RobotMap)
  * 1: A
	2: B
	3: X
	4: Y
	5: Left Bumper
	6: Right Bumper
	7: Back
	8: Start
	9: Left Joystick
	10: Right Joystick */
  //// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
