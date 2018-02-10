package org.usfirst.frc.team612.commands;

import org.usfirst.frc.team612.robot.OI;
import org.usfirst.frc.team612.subsystems.Lift;
import org.usfirst.frc.team612.robot.Robot;
import org.usfirst.frc.team612.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultLift extends Command {
	WPI_TalonSRX lift_talon = new WPI_TalonSRX(RobotMap.talon_lift);
    int prev_pos=0;
    int height=0;
    int RATE=50;
    int DEADZONE=60;
    public DefaultLift(int h) {
    	height=h;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        	lift_talon.set(ControlMode.Position,height); 	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
