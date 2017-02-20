package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.commands.MoveX;
import org.usfirst.frc4905.M31.commands.MoveY;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupCloseHopperFireVision extends CommandGroup {

    public GroupCloseHopperFireVision() {
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
    	
    	//Start With Gear Handler Facing Foward
    	addSequential(new MoveX(-10));
    	//Use Vision to put the gear on the hook
    	//Activate Gear Pusher
    	Timer.delay(1.5);
    	addSequential(new MoveX(2));
    	addSequential(new MoveY (-2.2));
    	addSequential(new MoveX (10));
    }
}
