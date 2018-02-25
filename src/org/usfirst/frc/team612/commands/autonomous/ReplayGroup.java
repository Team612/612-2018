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
    	//addSequential(new DisableDropper());

        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
