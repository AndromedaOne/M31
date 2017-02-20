package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseGearHandlerInAuto extends Command {
	private int m_delay = 0;
    public CloseGearHandlerInAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearHandler);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearHandler.moveGearHandlerTogether(-0.3);
    	m_delay++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(m_delay < 50){
        	return false;
        }else{
        	return true;
        }
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
