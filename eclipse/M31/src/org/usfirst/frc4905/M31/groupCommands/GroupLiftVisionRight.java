package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.Robot;
import org.usfirst.frc4905.M31.commands.CloseGearHandlerInAuto;
import org.usfirst.frc4905.M31.commands.MoveUsingUltrasonic;
import org.usfirst.frc4905.M31.commands.MoveUsingUltrasonicFront;
import org.usfirst.frc4905.M31.commands.MoveX;
import org.usfirst.frc4905.M31.commands.MoveY;
import org.usfirst.frc4905.M31.commands.OpenGearHandlerInAuto;
import org.usfirst.frc4905.M31.commands.PlaceGearAutomatically;
import org.usfirst.frc4905.M31.commands.TurnDeltaAngleDegree;

import Utilities.SideOfField;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupLiftVisionRight extends CommandGroup {

    public GroupLiftVisionRight() {
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
    	
    	//Start with the robot's "front" Facing the left wall on the rightS side
    	
    	addSequential(new MoveY(9.5));
    	addSequential(new TurnDeltaAngleDegree(-60));
    	addSequential(new MoveY(5.5));
    	addSequential(new MoveUsingUltrasonicFront(20));
    	addSequential(new PlaceGearAutomatically(210));
    	addSequential(new MoveUsingUltrasonic(8));
    	addSequential(new OpenGearHandlerInAuto());
		addSequential(new MoveUsingUltrasonic(20));
		addSequential(new CloseGearHandlerInAuto());

    	//commented out because we want to sit and have Ben take gear out
    	/*addSequential(new OpenGearHandlerInAuto());
    	addSequential(new MoveUsingUltrasonic(18));
    	addSequential(new CloseGearHandlerInAuto());*/
    	//vision code

    	
    }
}
