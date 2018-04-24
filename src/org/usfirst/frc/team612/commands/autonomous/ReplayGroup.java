package org.usfirst.frc.team612.commands.autonomous;
import org.usfirst.frc.team612.commands.pneumatic.DefaultDropper;
import org.usfirst.frc.team612.commands.pneumatic.DisableDropper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReplayGroup extends CommandGroup {

    public ReplayGroup() {
    	addSequential(new DefaultDropper());
    	addParallel(new ReplayArray());
    	addParallel(new ReplayRobot());
    }
}
