
package org.usfirst.frc.team612.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;
import java.io.IOException;
import org.usfirst.frc.team612.subsystems.Climber;
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
	public static OI oi;
	public static Drivetrain drivetrain = new Drivetrain();
	public static AHRS navx = new AHRS(SerialPort.Port.kMXP, SerialDataType.kRawData, (byte)200);	
	public static Climber climber=new Climber();
	public static Grabber grabber = new Grabber();
	public static Lift lift = new Lift();
	public static Dropper dropper = new Dropper();
	public static Compressor compressor = new Compressor(0);
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		compressor.setClosedLoopControl(true);
		try {
			oi = new OI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		//chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", chooser);
		//CameraServer.getInstance().startAutomaticCapture(0);
		//CameraServer.getInstance().startAutomaticCapture(1);
		
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
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		//if (autonomousCommand != null)
			//autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
	}
	
	/**
	 * Makes sure that the autonomous stops running when teleop starts running.
	 */
	@Override
	public void teleopInit() {
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
		//SmartDashboard.putBoolean("Grabber Solenoid", grabber.getSolenoid().get());
		SmartDashboard.putNumber("Lift Talon", lift.getTalon().get());
		SmartDashboard.putNumber("NAVX: Yaw", navx.getYaw());
		SmartDashboard.putNumber("NAVX: Accel X", navx.getWorldLinearAccelX());
		SmartDashboard.putNumber("NAVX: Accel Y", navx.getWorldLinearAccelY());
		SmartDashboard.putNumber("NAVX: Accel Z", navx.getWorldLinearAccelZ());
		//SmartDashboard.putNumber("Climber Talon 1", climber.getClimber(1).get());
		//SmartDashboard.putNumber("Climber Talon 2", climber.getClimber(2).get());
		SmartDashboard.putNumber("Lift Encoder Position",lift.getTalon().getSensorCollection().getQuadraturePosition());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		//LiveWindow.run();
	}
}
