package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupLiftLeftVisionCorrect extends CommandGroup {

    public GroupLiftLeftVisionCorrect() {
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
    	addSequential(new TurnDeltaAngleDegree(60));
    	addSequential(new MoveY(6.5));
    	addSequential(new MoveUsingUltrasonicFront(20));
    	addSequential(new PlaceGearAutomatically(330));
    	addSequential(new MoveUsingUltrasonic(10));
    	addSequential(new OpenGearHandlerInAuto());
		addSequential(new MoveUsingUltrasonic(20));
		addSequential(new CloseGearHandlerInAuto());
    	 //commented out because we want to sit and have Ben take gear out
    	 /*
    	addSequential(new OpenGearHandlerInAuto());
    	addSequential(new MoveUsingUltrasonic(18));*/
    	//GEAR SPIT COMMAND
    }
}
