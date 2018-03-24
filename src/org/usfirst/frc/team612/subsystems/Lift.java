package org.usfirst.frc.team612.subsystems;

import org.usfirst.frc.team612.commands.lift.DefaultLift;
import org.usfirst.frc.team612.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {
	WPI_TalonSRX lift_talon = new WPI_TalonSRX(RobotMap.talon_lift);
	public static double target = 0;	/**
	 * Sets the default command for a subsystem to <code>DefaultLift()</code>.
	 */
    public void initDefaultCommand() {
    	setDefaultCommand(new DefaultLift());
    	lift_talon.setNeutralMode(NeutralMode.Brake);
    	lift_talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 300);
    	lift_talon.selectProfileSlot(0, 0);
    	lift_talon.config_kF(0, 0.2, 100); //The 100 is the time out 
    	lift_talon.config_kP(0, 1, 100);//for setting  the configuration.(in milliseconds).
    	lift_talon.config_kI(0, 0, 100);
    	lift_talon.config_kD(0, 0,100);
    }
    /**
     * Returns a WPI_TalonSRX <code>lift_talon</code>
     * @return WPI_TalonSRX <code>lift_talon</code>
     */
    public WPI_TalonSRX getTalon() {
    	return lift_talon;
    }
}