package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenGearHandlerInAuto extends Command {
	private double m_outSpeed = 0.4;
	private double m_inSpeed = -0.2;
    public OpenGearHandlerInAuto() {
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
    		Robot.gearHandler.moveRightGearHandler(m_outSpeed);
    	}
    }

    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.gearHandler.shouldStopMovingLeft()&&Robot.gearHandler.shouldStopMovingRight()){
        	return true;
        }else{
        	return false;
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