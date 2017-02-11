package org.usfirst.frc4905.M31.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupShootFromStartCrossBaseLine extends CommandGroup {

    public GroupShootFromStartCrossBaseLine() {
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
    	
    	addSequential(new MoveY(1));
    	//Turn Towards the Boiler
    	addSequential(new TurnDeltaAngleDegree(90));
    	//Begin Shooting into the Boiler
    	addSequential(new TurnDeltaAngleDegree(-90));
    	addSequential(new MoveY(5));
    	
    }
}
