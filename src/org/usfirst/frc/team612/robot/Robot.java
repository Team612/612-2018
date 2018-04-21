package org.usfirst.frc.team612.robot;

import edu.wpi.first.wpilibj.CameraServer;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.usfirst.frc.team612.commands.autonomous.ReplayGroupAuto;
import org.usfirst.frc.team612.subsystems.Drivetrain;
import org.usfirst.frc.team612.subsystems.Lift;
import org.usfirst.frc.team612.subsystems.Dropper;
import org.usfirst.frc.team612.subsystems.Grabber;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    public static double encoder_multi = -2; // Multiplier used to change the rate at which encoder target
    										 // changes for Lift PID
    public static DriverStation driverstation = DriverStation.getInstance(); // Instance of the Driver Station (never used)
    public static OI oi; // Instance of the OI class for autonomous (everything else is accessed statically)
    public static Drivetrain drivetrain = new Drivetrain(); // Drivetrain object
    public static AHRS navx = new AHRS(I2C.Port.kMXP); // Navx (accelerometer) object, currently not on robot
    public static Grabber grabber = new Grabber(); // Grabber object
    public static Lift lift = new Lift(); // Lift object
    public static Dropper dropper = new Dropper(); // Dropper object
    public static Compressor compressor = new Compressor(0); // Compressor object. Only used in robotInit

    //public AnalogInput analogpressure = new AnalogInput(0);
    public boolean pressuregood; // Used to contain the status of air pressure on robot for pneumatics
    public boolean pressurelow;
    public boolean pressurecritical;
    Command autonomousCommand; // Eventually holds the command that the robot executes in robotics
    String game_data, start_position;
    /* game_data is of the form 'RRR', where each character represents the side of the
     * scale or switch that is for OUR alliance; first character is out switch, etc.
     * Start_poisition is R, L, or C; where the robot starts the match
     */
    SendableChooser < String > start_pos; // This lets us get the start_position string from smartdashboard
    SendableChooser < String > priority; // This lets us get the priority (scale or switch) from smartdashboard
    SendableChooser < String > score_amount; // And this will let us (hopefully) pick 1 or 2 cube auto
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    	start_pos = new SendableChooser < > (); // Create the chooser for our starting position
        start_pos.addDefault("Simple Auto", "s"); // If something messes up, it should just run simple auto
        start_pos.addObject("Start in Center", "c"); // Otherwise, pick starting in the center,
        start_pos.addObject("Start on Right", "D"); // or on the right
        start_pos.addObject("Start on Left", "F"); // or on the left
        
        //start_pos.addObject("Start on Left", "l");
        //start_pos.addObject("Start on Right", "r");
        
        //start_pos.addObject("Start on Left --SCALE", "A"); // A for scale
        //start_pos.addObject("Start on Right --SCALE", "B"); // B for scale
        
        //Robot.lift.getTalon().setSensorPhase(true);
        
        priority = new SendableChooser < > (); // Another SendableChooser for our priority
        priority.addDefault("Switch", "s"); // Either switch (low/close)
        priority.addObject("Scale", "c"); // Or scale (high/far)
        
        score_amount = new SendableChooser < > (); // Third SendableChooser for number of points
        score_amount.addDefault("1 block", "q"); // 1 cube
        score_amount.addObject("2 blocks", "z"); // or 2

        Robot.lift.getTalon().getSensorCollection().setQuadraturePosition(0, 0); // Reset lift encoder so it's calibrated to 0
        compressor.setClosedLoopControl(true); // And this makes the compressor automatically start gaining pressure
        try {
            oi = new OI(); // This has to be inside try/catch block because it does file stuff for autonomous
        } catch (IOException e) {
            //e.printStackTrace();
        }
        autonomousCommand = new ReplayGroupAuto(); // The autonomous command is always ReplayGroupAuto; below selects which auto to replay

        CameraServer.getInstance().startAutomaticCapture(0); // Start capturing frames from the webcam so drive team can see


        /* The three lines below actually put the three SendableChoosers created above on SmartDashboard.
         * Without these lines, it creates the objects in code but the drivers can't select any options
         */
        SmartDashboard.putData("Starting Position", start_pos);
        SmartDashboard.putData("Score Priority", priority);
        SmartDashboard.putData("Score Amount", score_amount);

    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
    	// We don't do anything in disabledInit
    }

    public void disabledPeriodic() {
    	/* The only thing our robot does in disabledPeriodic is repeatedly query for the game data.
    	 * There is some delay/buffer between sending the data and us getting it; this helps reduce
    	 * the delay. It might not actually affect the matches, but it seemed to help with debugging
    	 * so I'm leaving it in.
    	 */
        game_data = driverstation.getGameSpecificMessage();
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {

        game_data = driverstation.getGameSpecificMessage(); // This is what actually gets the game message during a match
        start_position = start_pos.getSelected(); // And this gets the starting position (L, R, C) from the SendableChooser
        if (game_data.length() > 0) { // If we did get game data
            System.out.println("Game Data: " + game_data); // Print out the game data
            System.out.println("Position: " + start_position); // And print out the position
            //OI.ALLOW_RECORDING = false;
            
            /*
            Dictionary<String, String> d = new Hashtable<String, String>();
            
            d.put("D", "center_L_S.txt")
            
            String score_num = score_amount.getSelected(); // 1 is 1, 2 is 0
            char pos_num = start_position.charAt(0); // 1 is left, 0 is right
            String priority_num = priority.getSelected(); // 1 is switch, 0 is scale
            char scale_num = game_data.charAt(1); // 1 is left, 0 is right
            char switch_num = game_data.charAt(0); // 1 is left, 0 is right
            
            //position, priority, 
            String key = pos_num + priority_num + switch_num + scale_num + score_num;
            
            */
            
            //int total = score_num + 2*pos_num + 4*priority_num + 8*scale_num + 16*switch_num;
            
            //Drive forward
            if (start_position.charAt(0) == 's') { // Start position is default (simple auto)
                OI.AUTO_FILE_NAME = "simple.txt";
                //Score on Switch - Center (left or right)
            } else if (start_position.charAt(0) == 'c') {
                if (game_data.charAt(0) == 'L') {
                    OI.AUTO_FILE_NAME = "center_L_S.txt";
                } else if (game_data.charAt(0) == 'R') {
                    OI.AUTO_FILE_NAME = "center_R_S"; // sorry
                }
             // Start on right
            } else if (start_position.charAt(0) == 'D') {
            	if(priority.getSelected() == "s") { // Switch
	                if (game_data.charAt(0) == 'R') { // Our switch on right
	                    OI.AUTO_FILE_NAME = "right_R_S.txt"; // Get that switch
	                } else if (game_data.charAt(1) == 'R') { // else if scale is on our side
	                	if (score_amount.getSelected() == "q") { // And we want to get one block
            				OI.AUTO_FILE_NAME = "right_R_C_FIX.txt"; // Put that one block bb
            			} else { // OR do two
            				OI.AUTO_FILE_NAME = "yeet.txt"; // TODO: Have correct one
            			}
	                } else {
	                    OI.AUTO_FILE_NAME = "simple.txt";
	                }
            	} else { // scale prority
            		if (game_data.charAt(1) == 'R') { // Scale is on our side
            			if (score_amount.getSelected() == "q") { // And one block
            				OI.AUTO_FILE_NAME = "right_R_C_FIX.txt";
            			} else  { // Or two blocks
            				OI.AUTO_FILE_NAME = "yeet.txt"; // TODO: Have correct one
            			}
            			
            		} else if (game_data.charAt(0) == 'R') {
	                    OI.AUTO_FILE_NAME = "right_R_S.txt";
	                } else {
	                    OI.AUTO_FILE_NAME = "simple.txt";
	                }
            	}

            }
          //Either Scale or Switch - Left Side 
            else if (start_position.charAt(0) == 'F') {
            	OI.REFLECT_AUTO = true;
            	if(priority.getSelected() == "s") {
	                if (game_data.charAt(0) == 'L') {
	                    OI.AUTO_FILE_NAME = "right_R_S.txt";
	                } else if (game_data.charAt(1) == 'L') {
	                    OI.AUTO_FILE_NAME = "right_R_C_FIX.txt";
	                } else {
	                    OI.AUTO_FILE_NAME = "simple.txt";
	                }
            	} else {
            		if (game_data.charAt(1) == 'L') {
            			OI.AUTO_FILE_NAME = "right_R_C_FIX.txt";
            		} else if (game_data.charAt(0) == 'L') {
	                    OI.AUTO_FILE_NAME = "right_R_S.txt";
	                } else {
	                    OI.AUTO_FILE_NAME = "simple.txt";
	                }
            	}

            }
            else {
            	//If all these fail drive forward
                OI.AUTO_FILE_NAME = "simple.txt";
            }
        }
        
        System.out.println("Replaying file" + OI.AUTO_FILE_NAME + ", reflection is " + OI.REFLECT_AUTO);
        // Print out the file selection and whether the file is being reflected for post-match debugging

        if (autonomousCommand != null) {
            autonomousCommand.start(); // Then actually start the autonomous playback

        }
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        // Nothing special
    }

    /**
     * Makes sure that the autonomous stops running when teleop starts running.
     */
    @Override
    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel(); // if autonomous still exists, stop running autonomous
        }
        dropper.getSolenoid().set(Value.kOff); // Turn off dropper so it doesn't leak airpressure
        //navx.reset();
        //navx.zeroYaw();

    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        /* Block below figures out how our pressure is and sends it to the drivers.
         * Currently not used because we don't have enough weight on the robot, but
         * we're not deleting it in case we put it back on.
         */

        /*double analogvoltage = analogpressure.getAverageVoltage();
        double scalefactor = 125.0;
        double bias = 0.0;
        //int analogbits = analogpressure.getValue();
        //double analogscalefactor = analogvoltage / analogbits;
        double analogpressure = (scalefactor * analogvoltage - bias);
        //System.out.println(analogvoltage);
        //System.out.println(analogpressure);
        if (analogpressure > 90) {
            pressuregood = true;
            pressurelow = false;
            pressurecritical = false;
        } else if (analogpressure > 60) {
            pressurelow = true;
            pressuregood = false;
            pressurecritical = false;
        } else {
            pressurecritical = true;
            pressurelow = false;
            pressuregood = false;
        }
        SmartDashboard.putBoolean("Pressure Good", pressuregood);
        SmartDashboard.putBoolean("Pressure Low", pressurelow);
        SmartDashboard.putBoolean("Pressure Critical", pressurecritical);*/
        
        // Rest of the debugging statements
		
		SmartDashboard.putNumber("Wheel FL", drivetrain.getTalon(1).get());
		SmartDashboard.putNumber("Wheel FR", drivetrain.getTalon(2).get());
		SmartDashboard.putNumber("Wheel RR", drivetrain.getTalon(3).get());
		SmartDashboard.putNumber("Wheel RL", drivetrain.getTalon(4).get());
		//SmartDashboard.putBoolean("Grabber Solenoid", grabber.getSolenoid().get());
		/*SmartDashboard.putNumber("Lift Talon", lift.getTalon().get());
		SmartDashboard.putNumber("NAVX: Yaw", navx.getYaw());*/
		SmartDashboard.putBoolean( "Lift Limit FWD SW", !lift.getTalon().getSensorCollection().isFwdLimitSwitchClosed());
		SmartDashboard.putBoolean( "Lift Limit REV SW", !lift.getTalon().getSensorCollection().isRevLimitSwitchClosed());
		SmartDashboard.putNumber("Lift Encoder Position", -1 * (lift.getTalon().getSelectedSensorPosition(0)));
		SmartDashboard.putNumber("Lift Target", lift.target);
		SmartDashboard.putNumber("Lift %V", lift.getTalon().getMotorOutputPercent());
		SmartDashboard.putBoolean("Is Lift Motor Stalling", OI.IS_MOTOR_STALLED);
		//
		SmartDashboard.putNumber("Lift Motor Temp (C)", lift.getTalon().getTemperature());

        //SmartDashboard.putBoolean("Is compressor low pressure?", compressor.getPressureSwitchValue());
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
    	// We don't do anything in test
    }
}
