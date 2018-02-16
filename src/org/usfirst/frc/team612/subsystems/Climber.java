package org.usfirst.frc.team612.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	Talon climber1=new Talon(1);
	Talon climber2=new Talon(1);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public Talon getClimber(int num) {
    	if(num == 1) {
    		return climber1;
    	}
    	else if(num == 2) {
    		return climber2;
    	}
    	else{
    		System.out.println("this should not be printed Climber.java");
    		return null;
    	}
    }
    
}

