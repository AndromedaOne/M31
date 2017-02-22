package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.commands.CloseGearHandlerInAuto;
import org.usfirst.frc4905.M31.commands.MoveUsingUltrasonic;
import org.usfirst.frc4905.M31.commands.MoveUsingUltrasonicFront;
import org.usfirst.frc4905.M31.commands.MoveX;
import org.usfirst.frc4905.M31.commands.MoveY;
import org.usfirst.frc4905.M31.commands.OpenGearHandlerInAuto;
import org.usfirst.frc4905.M31.commands.PlaceGearAutomatically;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupMiddleLiftVision extends CommandGroup {
	
    
	public GroupMiddleLiftVision() {
    	
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
		
		//Start on position B
		//Front faces towards airship
		addSequential(new MoveY(8.1));
		addSequential(new MoveUsingUltrasonicFront(16));
		addSequential(new PlaceGearAutomatically(270));
		addSequential(new MoveUsingUltrasonic(10));
		
		 //commented out because we want to sit and have Ben take gear out
		/*
		addSequential(new MoveUsingUltrasonic(18));
		*/

    }
}
