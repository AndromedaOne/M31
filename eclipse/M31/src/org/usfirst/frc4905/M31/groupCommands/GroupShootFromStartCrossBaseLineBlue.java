package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.commands.AutoShootBoiler;
import org.usfirst.frc4905.M31.commands.ControlledFeederStop;
import org.usfirst.frc4905.M31.commands.MoveUsingUltrasonicFront;
import org.usfirst.frc4905.M31.commands.MoveY;
import org.usfirst.frc4905.M31.commands.RunIntakeInAuto;
import org.usfirst.frc4905.M31.commands.StopIntake;
import org.usfirst.frc4905.M31.commands.TurnToCompassHeading;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupShootFromStartCrossBaseLineBlue extends CommandGroup {

    public GroupShootFromStartCrossBaseLineBlue() {
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
        
        //used in competition, mainly earlier in the season
    	addSequential(new MoveY(4.2));
    	addSequential(new TurnToCompassHeading(230));
    	addSequential(new MoveUsingUltrasonicFront(6.5));
    	addSequential(new AutoShootBoiler(6, 780));//6 before bedford playoffs
    	addSequential(new MoveY(-10));
    }
}
