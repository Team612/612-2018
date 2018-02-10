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
	
	/**
	 * Sets the default command for a subsystem to <code>DefaultLift()</code>.
	 */
    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	lift_talon.selectProfileSlot(0, 0);
    	lift_talon.config_kF(0, 0.2, 5000); //The 5000 is the time out 
    	lift_talon.config_kP(0, 0.2, 5000);//for setting  the configuration.(in milliseconds).
    	lift_talon.config_kI(0, 0, 5000);
    	lift_talon.config_kD(0, 0,5000);
    	
    }
    /**
     * Returns a WPI_TalonSRX <code>lift_talon</code>
     * @return WPI_TalonSRX <code>lift_talon</code>
     */
    public WPI_TalonSRX getTalon() {
    	return lift_talon;
    }
}

