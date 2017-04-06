package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.commands.*;
import org.usfirst.frc4905.M31.commands.MoveUsingUltrasonicFront;
import org.usfirst.frc4905.M31.commands.TurnToCompassHeading;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupShootFromStartCrossBaseLineRed extends CommandGroup {

    public GroupShootFromStartCrossBaseLineRed() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Commandl'1(l00));
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	//CONFIGURED FOR RED
    	addSequential(new MoveY(4));
    	//Turn Towards the Boiler
    	addSequential(new TurnToCompassHeading(130));
    	addSequential(new MoveUsingUltrasonicFront(6));
    	addSequential(new AutoShootBoiler(6, 780));//6 before bedford playoffs
    	addSequential(new MoveY(-10));
    	
    	
    }
}
