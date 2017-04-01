package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.commands.CloseGearHandlerInAuto;
import org.usfirst.frc4905.M31.commands.MoveUsingUltrasonic;
import org.usfirst.frc4905.M31.commands.MoveUsingUltrasonicFront;
import org.usfirst.frc4905.M31.commands.MoveY;
import org.usfirst.frc4905.M31.commands.OpenGearHandlerInAuto;
import org.usfirst.frc4905.M31.commands.PlaceGearAutomatically;
import org.usfirst.frc4905.M31.commands.TurnDeltaAngleDegree;
import org.usfirst.frc4905.M31.commands.TurnToCompassHeading;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightLiftVisionRun extends CommandGroup {

    public RightLiftVisionRun() {
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
    	addSequential(new MoveY(10.6));
    	addSequential(new TurnDeltaAngleDegree(-60));
    	addSequential(new MoveY(6.8));
    	addSequential(new MoveUsingUltrasonicFront(20));
    	addSequential(new PlaceGearAutomatically(210));
    	addSequential(new MoveUsingUltrasonic(10));
    	addSequential(new OpenGearHandlerInAuto());
		addSequential(new MoveUsingUltrasonic(30));
		addSequential(new CloseGearHandlerInAuto());
		addSequential(new TurnToCompassHeading(180));
		addSequential(new MoveY(-30));
    }
}
