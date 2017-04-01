package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.commands.CloseGearHandlerInAuto;
import org.usfirst.frc4905.M31.commands.MoveUsingUltrasonic;
import org.usfirst.frc4905.M31.commands.MoveX;
import org.usfirst.frc4905.M31.commands.MoveY;
import org.usfirst.frc4905.M31.commands.OpenGearHandlerInAuto;
import org.usfirst.frc4905.M31.commands.PlaceGearAutomatically;
import org.usfirst.frc4905.M31.commands.TurnDeltaAngleDegree;
import org.usfirst.frc4905.M31.commands.TurnToCompassHeading;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupLiftVisionLeft extends CommandGroup {

    public GroupLiftVisionLeft() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()a
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//Start with the robot's "front" Facing the left wall on the left side
    	addSequential(new MoveY(10.6));
    	addSequential(new TurnDeltaAngleDegree(60));
    	addSequential(new MoveY(8));
    	addSequential(new PlaceGearAutomatically(330));
    	addSequential(new MoveUsingUltrasonic(10));
    	addSequential(new OpenGearHandlerInAuto());
    	Timer.delay(0.5);
    	addSequential(new MoveUsingUltrasonic(18));
    	addSequential(new CloseGearHandlerInAuto());
    }
}
