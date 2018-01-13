package org.usfirst.frc.team612.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import org.usfirst.frc.team612.robot.RobotMap;
import org.usfirst.frc.team612.commands.DefaultDrive;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	Talon talon_FR = new Talon(RobotMap.talon_FR);
	Talon talon_FL = new Talon(RobotMap.talon_FL);
	Talon talon_RR = new Talon(RobotMap.talon_RR);
	Talon talon_RL = new Talon(RobotMap.talon_RL);
	MecanumDrive drivetrain = new MecanumDrive(talon_FL, talon_RL, talon_FR, talon_RR);
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DefaultDrive());
    }
    
    public MecanumDrive getDriveTrain() {
    	return drivetrain;
    }
}

