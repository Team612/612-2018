package org.usfirst.frc.team612.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import java.io.IOException;
import java.util.ArrayList;
import org.usfirst.frc.team612.commands.autonomous.RecordMovement;
import org.usfirst.frc.team612.commands.autonomous.ReplayGroup;
import org.usfirst.frc.team612.commands.lift.TogglePID;
import org.usfirst.frc.team612.commands.pneumatic.CloseGrabber;
import org.usfirst.frc.team612.commands.pneumatic.DefaultDropper;
import org.usfirst.frc.team612.commands.pneumatic.DefaultGrabber;
import org.usfirst.frc.team612.commands.pneumatic.DisableDropper;
import org.usfirst.frc.team612.commands.pneumatic.DisableGrabber;
import org.usfirst.frc.team612.commands.pneumatic.OpenGrabber;
import org.usfirst.frc.team612.robot.RobotMap;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final boolean TANKDRIVE 			= false; // Variable for tankdrive vs. mecanum. Should be false
	public static final boolean XBOX 				= true;  // Variable for using xbox vs. flightstick. Should be true
	public static final boolean OMNI 				= false; // Variable for omnidrive (early in season). Should be false 
	public static final boolean DRIVER_PERSPECTIVE  = false; /* Variable for driver perspective driving (forward always drives
															    in the same direction regardless of rotation. Should be false */
	public static  boolean LIFT_PID 				= true;  /* Variable for manual vs. PID lift. Should be true, PID can be
															    be disabled manually in other ways, see below */
	public static final boolean FIX_DRIFT 			= false; // Attempted to manually fix driving; should DEFINITELY be false
	public static final boolean BONGO_MODE  		= false; // We using bongos? Should VERY DEFINITELY be false for competitions
	public static final boolean ALLOW_RECORDING		= false; // Whether we can record motion for playback later; should be false
	public static boolean GRABBER_POS   			= true;  // true means closed; stored here so that they can be accessed elsewhere
	public static boolean DROPPER_POS   			= true;  // true means off; stored here so that they can be accessed elsewhere
	public static final boolean PREVENT_TIPPING 	= false; // attempted to disable playback if robot tips too much, should be false
	public static boolean REFLECT_AUTO				= false; // Whether we try to reflect auto; should be false, Robot.java decides it
	public static final boolean NEW_GUNNER_CONTROL  = true;  // We changed gunner control partway through the season; should be true
	public static final double TIP_ANGLE            = 30;    // Tip angle that makes autonomous playback stop (in degrees).
	public static boolean IS_MOTOR_STALLED			= false; // Attempts to debug if motor is stalled (power but no movement); should be false
	public static String TEST_FILE_NAME 			= "data55.txt"; // random file name so we don't override something important
	public static String AUTO_FILE_NAME 			= "data55.txt"; // random file name so we don't override something important
	// simple.txt = drive 5 seconds, center_R_S, center_L_S.txt, left_L_S.txt, right_R_S.txt -redo
	public static XboxController bongo  = new XboxController(RobotMap.bongo_port);
	public static XboxController driver = new XboxController(RobotMap.driver_port);
	public static XboxController gunner = new XboxController(RobotMap.gunner_port);
	public static Joystick joy = new Joystick(1); // Flight controller, not used in competitions currently
	
	/* Code below creates objects for all the buttons on all the joysticks. These can then
	 * be attached to commands later on
	 */

	public static JoystickButton driver_button_A    	= new JoystickButton(driver,1);
	public static JoystickButton driver_button_B 		= new JoystickButton(driver,2);
	public static JoystickButton driver_button_X    	= new JoystickButton(driver,3);
	public static JoystickButton driver_button_Y    	= new JoystickButton(driver,4);
	public static JoystickButton driver_button_LB   	= new JoystickButton(driver,5);
	public static JoystickButton driver_button_RB   	= new JoystickButton(driver,6);
	public static JoystickButton driver_button_BCK  	= new JoystickButton(driver,7);
	public static JoystickButton driver_button_STRT 	= new JoystickButton(driver,8);
	public static JoystickButton driver_button_LJ   	= new JoystickButton(driver,9);
	public static JoystickButton driver_button_RJ   	= new JoystickButton(driver,10);
	//end of driver
    public static JoystickButton gunner_button_A    	= new JoystickButton(gunner, 1);
	public static JoystickButton gunner_button_B    	= new JoystickButton(gunner, 2);
    public static JoystickButton gunner_button_X    	= new JoystickButton(gunner,3);
	public static JoystickButton gunner_button_Y    	= new JoystickButton(gunner,4);
	public static JoystickButton gunner_button_LB   	= new JoystickButton(gunner,5);
	public static JoystickButton gunner_button_RB   	= new JoystickButton(gunner,6);
	public static JoystickButton gunner_button_BCK  	= new JoystickButton(gunner,7);
	public static JoystickButton gunner_button_STRT 	= new JoystickButton(gunner,8);
	public static JoystickButton gunner_button_LJ   	= new JoystickButton(gunner,9);
	public static JoystickButton gunner_button_RJ   	= new JoystickButton(gunner,10);
	
	public static JoystickButton bongo_button_Left_F    = new JoystickButton(bongo, 1); 
	public static JoystickButton bongo_button_Left_B    = new JoystickButton(bongo, 2);
	public static JoystickButton bongo_button_Right_F   = new JoystickButton(bongo, 4);
	public static JoystickButton bongo_button_Right_B   = new JoystickButton(bongo, 3);
	public static JoystickButton bongo_button_middle    = new JoystickButton(bongo, 10);

	public static ArrayList < Double > drive_data = new ArrayList < Double >(4);
	
	public OI() throws IOException {
		if(NEW_GUNNER_CONTROL) { // This is usually true
			/* One bumper closes the grabber while held, and turns it off when released. The other
			 * bumper opens the grabber while held, and turns off when released. The back button
			 * (hard to accidentally press) toggles the dropper just in case, and Y button turns
			 * of lift PID if stuff breaks.
			 */
			gunner_button_BCK.whenPressed(new DefaultDropper());
			gunner_button_BCK.whenReleased(new DisableDropper());
			gunner_button_RB.whenPressed(new OpenGrabber());
			gunner_button_RB.whenReleased(new DisableGrabber());
			gunner_button_LB.whenPressed(new CloseGrabber());
			gunner_button_LB.whenReleased(new DisableGrabber());
			gunner_button_Y.whenPressed(new TogglePID());	
		} else {
			// Old, bad control so I won't explain
			gunner_button_BCK.whenPressed(new DefaultDropper());
			gunner_button_BCK.whenReleased(new DisableDropper());
			gunner_button_RB.whenPressed(new DefaultGrabber());
			gunner_button_RB.whenReleased(new DisableGrabber());
		}
		/*if(BONGO_MODE) {
			bongo_button_middle.whenPressed(new DefaultDropper());
			bongo_button_middle.whenReleased(new DisableDropper());
			bongo_button_Right_F.whenPressed(new DefaultGrabber());
			bongo_button_Right_F.whenReleased(new DisableGrabber());
		}
		else {
		
			gunner_button_BCK.whenPressed(new DefaultDropper());
			gunner_button_BCK.whenReleased(new DisableDropper());
			gunner_button_RB.whenPressed(new DefaultGrabber());
			gunner_button_RB.whenReleased(new DisableGrabber());
		}*/
		
		if(ALLOW_RECORDING) { // If we can record, press B to record and A to playback
			driver_button_A.whenPressed(new ReplayGroup());
			driver_button_B.whenPressed(new RecordMovement());
		}
		
		

		
	}
	
}

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
