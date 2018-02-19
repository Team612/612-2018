package org.usfirst.frc.team612.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import org.usfirst.frc.team612.robot.RobotMap;
import org.usfirst.frc.team612.commands.DefaultDrive;
import org.usfirst.frc.team612.commands.MoveWheel;
/**
 *
 */
public class Drivetrain extends Subsystem {
	WPI_TalonSRX talon_FR = new WPI_TalonSRX(RobotMap.talon_FR);
	WPI_TalonSRX talon_FL = new WPI_TalonSRX(RobotMap.talon_FL);
	WPI_TalonSRX talon_RR = new WPI_TalonSRX(RobotMap.talon_RR);
	WPI_TalonSRX talon_RL = new WPI_TalonSRX(RobotMap.talon_RL);
	MecanumDrive drivetrain;
	/**
	 * Initializes MecanumDrive <code>drivetrain</code>.
	 */
	public Drivetrain() {
		//talon_FR.setInverted(true);
		//talon_FL.setInverted(true);
		//talon_RR.setInverted(true);
		//talon_RL.setInverted(true);
		drivetrain = new MecanumDrive(talon_FL, talon_RL, talon_FR, talon_RR);
	}
	/**
	 * Sets default command for a subsystem.
	 */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    		setDefaultCommand(new MoveWheel());
    }
    
    /**
     * Gets MecanumDrive <code>drivetrain</code>.
     * @return MecanumDrive <code>drivetrain</code>.
     */
    public MecanumDrive getDriveTrain() {
    	return drivetrain;
    }
    
    /**
     * Gets a Talon.
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

