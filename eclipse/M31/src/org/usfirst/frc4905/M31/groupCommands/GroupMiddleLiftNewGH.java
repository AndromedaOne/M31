package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupMiddleLiftNewGH extends CommandGroup {

    public GroupMiddleLiftNewGH() {
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
    	addParallel(new MoveY(7.7));
    	addSequential(new NewGHmoveUpDown(-0.5));
    	addSequential(new MoveUsingUltrasonicFront(10));
    	addParallel(new NewGHopenClose(0.4));
    	addParallel(new NewGHmoveUpDown(0.5));
    	addSequential(new MoveY(-2));
    }
}
