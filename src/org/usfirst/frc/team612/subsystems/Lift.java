package org.usfirst.frc.team612.subsystems;

import org.usfirst.frc.team612.commands.DefaultLift;
import org.usfirst.frc.team612.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {
	WPI_TalonSRX lift_talon = new WPI_TalonSRX(RobotMap.talon_lift);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new DefaultLift());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public WPI_TalonSRX getTalon() {
    	return lift_talon;
    }
}

