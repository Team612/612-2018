package org.usfirst.frc.team612.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import org.usfirst.frc.team612.commands.drive.DefaultDrive;
import org.usfirst.frc.team612.robot.RobotMap;
/**
 *
 */
public class Drivetrain extends Subsystem {
	/* This is the class for the drivetrain used on the robot for 2018.
	 * It consists of a four-wheel mecanum drive, powered with TalonSRX motor controller.
	 */
	WPI_TalonSRX talon_FR = new WPI_TalonSRX(RobotMap.talon_FR);
	WPI_TalonSRX talon_FL = new WPI_TalonSRX(RobotMap.talon_FL);
	WPI_TalonSRX talon_RR = new WPI_TalonSRX(RobotMap.talon_RR);
	WPI_TalonSRX talon_RL = new WPI_TalonSRX(RobotMap.talon_RL);
	/* The talon objects take in as their only parameter the id they are connected to the
	 * power distribution panel.
	 */
	MecanumDrive drivetrain;
	/**
	 * Initializes MecanumDrive <code>drivetrain</code>.
	 */
	public Drivetrain() {
		//talon_FR.setInverted(true);
		//talon_FL.setInverted(true);
		//talon_RR.setInverted(true);
		//talon_RL.setInverted(true);
		
		/* The commented out lines above are for in case some of the wheels run in reverse
		 * for some reason; simply uncomment the correct line and this will fix the issue
		 * in code. The EZ-connector in the Talon can also be switched (red->black instead
		 * of red->red) to fix this problem manually.
		 */
		talon_FR.setNeutralMode(NeutralMode.Brake);
		talon_FL.setNeutralMode(NeutralMode.Brake);
		talon_RR.setNeutralMode(NeutralMode.Brake);
		talon_RL.setNeutralMode(NeutralMode.Brake);
		/* The talons are set in brake-mode when power isn't applied (neutral) so that the
		 * robot can stop effectly and allows greater mobility when turning. The other
		 * option is coasting.
		 */
		drivetrain = new MecanumDrive(talon_FL, talon_RL, talon_FR, talon_RR);
	}
	/**
	 * Sets default command for a subsystem.
	 */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    		setDefaultCommand(new DefaultDrive());
    		/* If there isn't a special command specifically run that requires
    		 * the drivetrain object, the DefaultDrive will run indefinitely
    		 * while the robot is enabled.
    		 */
    }
    
    /**
     * Gets MecanumDrive <code>drivetrain</code>.
     * @return MecanumDrive <code>drivetrain</code>.
     */
    public MecanumDrive getDriveTrain() {
    	return drivetrain;
    	// This allows us to access the drivetrain object while keeping it private.
    }
    
    /**
     * Gets a Talon, allowing us to access the talon objects while keeping them private.
     * @param num A number used to determine what Talon to use.
     * @return talon_FL
     * @return talon_FR
     * @return talon_RR
     * @return talon_RL
     */
    public WPI_TalonSRX getTalon(int num) {
    	switch(num) {
    	case 1:
    		return talon_FL;
    	case 2:
    		return talon_FR;
    	case 3:
    		return talon_RR;
    	case 4:
    		return talon_RL;
    	default:
    		return null;
    	}
    }
}

