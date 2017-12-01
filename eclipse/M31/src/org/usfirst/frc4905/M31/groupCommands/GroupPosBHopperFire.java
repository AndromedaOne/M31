package org.usfirst.frc4905.M31.groupCommands;

import org.usfirst.frc4905.M31.commands.MoveX;
import org.usfirst.frc4905.M31.commands.MoveY;
import org.usfirst.frc4905.M31.commands.ShootFromTheBoiler;
import org.usfirst.frc4905.M31.commands.TurnToCompassHeading;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroupPosBHopperFire extends CommandGroup {

    public GroupPosBHopperFire() {
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
    
	//never used in competition (or ever)
    	addSequential(new MoveY(11));
    	addSequential(new TurnToCompassHeading(90));
    	addSequential(new MoveY(21));
    	addSequential(new TurnToCompassHeading(0));
    	addSequential(new MoveY(5));
    	addSequential(new TurnToCompassHeading(180));
    	addSequential(new MoveX(-5));
    	Timer.delay(3);
    	addSequential(new MoveX(5));
    	addSequential(new MoveY(5));
    	addSequential(new TurnToCompassHeading(45));
    	addSequential(new MoveY(5));
    	addSequential(new ShootFromTheBoiler(75));
    	
    	
    	
    }
}
	
