package org.usfirst.frc.team612.commands.autonomous;

import org.usfirst.frc.team612.commands.pneumatic.DefaultDropper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReplayGroupAuto extends CommandGroup {

    public ReplayGroupAuto() {
    	addSequential(new ChangeFileName());
    	addSequential(new DefaultDropper());
    	addParallel(new ReplayArray());
    	addParallel(new ReplayRobot());
    }
}
