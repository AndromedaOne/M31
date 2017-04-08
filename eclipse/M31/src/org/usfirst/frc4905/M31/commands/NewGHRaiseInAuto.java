package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NewGHRaiseInAuto extends Command {

    public NewGHRaiseInAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.newGH);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.newGH.getGHupState() == true){
			Robot.newGH.clawOpenCLose(0);
    		Robot.newGH.moveUpDown(0);
		}
		else{
			Robot.newGH.clawOpenCLose(0);
    		Robot.newGH.moveUpDown(-0.65);	
		}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.newGH.getGHupState();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.newGH.moveUpDown(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
