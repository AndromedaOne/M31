package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.Robot;
import org.usfirst.frc4905.M31.commands.AutoMove;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SideGearLift extends CommandGroup {

    public SideGearLift() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	requires(Robot.driveTrain);
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new AutoMove(12, 1, 0));
    }
}
