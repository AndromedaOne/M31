package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenGearHandler extends Command {
	private double m_outSpeed = 0.3;
	private double m_inSpeed = -0.2;
    public OpenGearHandler() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearHandler);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gearHandler.shouldStopMovingLeft()){
    		Robot.gearHandler.stopMovingLeft();
    	}
    	else{
    		Robot.gearHandler.moveLeftGearHandler(m_outSpeed);
    	}
    	if(Robot.gearHandler.shouldStopMovingRight()){
    		Robot.gearHandler.stopMovingRight();
    	}
    	else{
    		Robot.gearHandler.moveRightGearHandler(m_outSpeed + 0.1);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearHandler.stopMovingLeft();
    	Robot.gearHandler.stopMovingRight();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
