package org.usfirst.frc.team612.subsystems;

import org.usfirst.frc.team612.commands.DefaultPivotArm;
import org.usfirst.frc.team612.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PivotArm extends Subsystem {
	
	WPI_TalonSRX pivot = new WPI_TalonSRX(RobotMap.talon_pivot);

    // here. Call these from Commands.
	public WPI_TalonSRX getTalon() {
	return pivot;
	
}
    public void initDefaultCommand() {
    	setDefaultCommand(new DefaultPivotArm());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

