package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.commands.MoveUsingUltrasonic;
import org.usfirst.frc4905.M31.commands.MoveX;
import org.usfirst.frc4905.M31.commands.MoveY;
import org.usfirst.frc4905.M31.commands.TurnDeltaAngleDegree;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupLeftLiftFarHopperNoVision extends CommandGroup {

    public GroupLeftLiftFarHopperNoVision() {
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
    	
    	//Position A
    	//Start With Gear Handler Facing the Left Lift
    	addSequential(new MoveX(5));
    	addSequential(new TurnDeltaAngleDegree(30));
    	addSequential(new MoveUsingUltrasonic(6));
    	//This puts the gear on the lift ^
    	addSequential(new MoveX(-2));
    	addSequential(new TurnDeltaAngleDegree(-30));
    	addSequential(new MoveX(3));
    	addSequential(new MoveY(2));
    	
    	
    }
}
