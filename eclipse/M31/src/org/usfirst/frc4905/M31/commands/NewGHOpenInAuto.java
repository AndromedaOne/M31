package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NewGHOpenInAuto extends Command {

    public NewGHOpenInAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.newGH);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.newGH.getGHopenState() == true){
			Robot.newGH.clawOpenCLose(0);
    		Robot.newGH.moveUpDown(0);
		}
		else{
			Robot.newGH.clawOpenCLose(0.8);
    		Robot.newGH.moveUpDown(0);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.newGH.getGHopenState();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.newGH.clawOpenCLose(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
