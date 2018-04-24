package org.usfirst.frc.team612.robot;

import edu.wpi.first.wpilibj.CameraServer;

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

import org.usfirst.frc.team612.commands.autonomous.ReplayGroupAuto;
import org.usfirst.frc.team612.subsystems.Drivetrain;
import org.usfirst.frc.team612.subsystems.Lift;
import org.usfirst.frc.team612.subsystems.Dropper;
import org.usfirst.frc.team612.subsystems.Grabber;

public class Robot extends IterativeRobot {
	
    public static double encoder_multi = -2;
    public static DriverStation driverstation = DriverStation.getInstance();
    public static OI oi;
    public static Drivetrain drivetrain = new Drivetrain();
    public static AHRS navx = new AHRS(I2C.Port.kMXP);
    public static Grabber grabber = new Grabber();
    public static Lift lift = new Lift();
    public static Dropper dropper = new Dropper();
    public static Compressor compressor = new Compressor(0);

    //public AnalogInput analogpressure = new AnalogInput(0);
    public boolean pressuregood;
    public boolean pressurelow;
    public boolean pressurecritical;
    Command autonomousCommand;
    String game_data;
	public static String start_position;
    SendableChooser < String > start_pos;
    SendableChooser < String > priority;
    SendableChooser < String > score_amount;
    SendableChooser < Integer > auto_delay_time;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    	start_pos = new SendableChooser < > ();
        start_pos.addDefault("Simple Auto", "s");
        start_pos.addObject("Start in Center", "c");
        start_pos.addObject("Start on Right", "D");
        start_pos.addObject("Start on Left", "F");
        priority = new SendableChooser < > ();
        priority.addDefault("Switch", "s");
        priority.addObject("Scale", "c");
        score_amount = new SendableChooser < > ();
        score_amount.addDefault("1 block", "q");
        score_amount.addObject("2 blocks", "z");
        auto_delay_time = new SendableChooser < > ();
        auto_delay_time.addDefault("0 second delay", 0);
        auto_delay_time.addObject("1 second delay", 1);
        auto_delay_time.addObject("3 second delay", 3);
        auto_delay_time.addObject("5 second delay", 5);
        Robot.lift.getTalon().getSensorCollection().setQuadraturePosition(0, 0);
        compressor.setClosedLoopControl(true);
        try {
            oi = new OI();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        autonomousCommand = new ReplayGroupAuto();
        CameraServer.getInstance().startAutomaticCapture(0);
        SmartDashboard.putData("Starting Position", start_pos);
        SmartDashboard.putData("Score Priority", priority);
        SmartDashboard.putData("Score Amount", score_amount);

        //Check if File has been created
        //Create File Writer object with file path
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {

    }

    public void disabledPeriodic() {
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
        //autonomousCommand;
        game_data = driverstation.getGameSpecificMessage();
        start_position = start_pos.getSelected();
        if (game_data.length() > 0) {
            System.out.println("Game Data: " + game_data);
            System.out.println("Position: " + start_position);
            //OI.ALLOW_RECORDING = false;
            // THIS IS THE ARRAY WAY TO DO IT
            
            /*
            String[] all_data = {"yeet.txt", "right_R_C.txt", "simple.txt", "yeet.txt", "right_R_S.txt", "right_R_S.txt", };
            
            if(start_position.charAt(0) == 's') {
            	OI.AUTO_FILE_NAME = "simple.txt";
            }
            if(start_position.charAt(0) == 'c') {
            	if(game_data.charAt(0) == 'L') {
            		OI.AUTO_FILE_NAME = "center_L_S.txt";
            	} else {
            		OI.AUTO_FILE_NAME = "center_R_S"; // whoops
            	}
            }
            
            
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
            if (start_position.charAt(0) == 's') {
                OI.AUTO_FILE_NAME = "simple.txt";
                //Score on Scale - Center (left or right)
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

        if (autonomousCommand != null) {
            autonomousCommand.start();

        }
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * Makes sure that the autonomous stops running when teleop starts running.
     */
    @Override
    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        dropper.getSolenoid().set(Value.kOff);
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //navx.reset();
        //navx.zeroYaw();
        //if (autonomousCommand != null)
        //	autonomousCommand.cancel();

    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
		SmartDashboard.putNumber("Wheel FL", drivetrain.getTalon(1).get());
		SmartDashboard.putNumber("Wheel FR", drivetrain.getTalon(2).get());
		SmartDashboard.putNumber("Wheel RR", drivetrain.getTalon(3).get());
		SmartDashboard.putNumber("Wheel RL", drivetrain.getTalon(4).get());
		SmartDashboard.putBoolean( "Lift Limit FWD SW", !lift.getTalon().getSensorCollection().isFwdLimitSwitchClosed());
		SmartDashboard.putBoolean( "Lift Limit REV SW", !lift.getTalon().getSensorCollection().isRevLimitSwitchClosed());
		SmartDashboard.putNumber("Lift Encoder Position", -1 * (lift.getTalon().getSelectedSensorPosition(0)));
		SmartDashboard.putNumber("Lift Target", lift.target);
		SmartDashboard.putNumber("Lift %V", lift.getTalon().getMotorOutputPercent());
		SmartDashboard.putBoolean("Is Lift Motor Stalling", OI.IS_MOTOR_STALLED);
		SmartDashboard.putNumber("Lift Motor Temp (C)", lift.getTalon().getTemperature());
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        //LiveWindow.run();
    }
}
