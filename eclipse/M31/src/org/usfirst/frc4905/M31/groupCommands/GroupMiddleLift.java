package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.commands.MoveUsingUltrasonic;
import org.usfirst.frc4905.M31.commands.MoveX;
import org.usfirst.frc4905.M31.commands.MoveY;
import org.usfirst.frc4905.M31.commands.TurnDeltaAngleDegree;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupMiddleLift extends CommandGroup {
	
	private	boolean m_boiler = false;
    
	public GroupMiddleLift() {
    	
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
		
		//Facing Towards the AirShip
		addSequential(new MoveX(1));
		addSequential(new TurnDeltaAngleDegree(-90));
		addSequential(new MoveUsingUltrasonic(4));
		//Push Gear On
		addSequential(new MoveX(-1.5));
		addSequential(new MoveY(-3));
		addSequential(new MoveX(4));


    	
    	
    }
}
