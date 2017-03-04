package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseGearHandler extends Command {
	private double m_outSpeed = 0.4;
	private double m_inSpeed = -0.2;
	private int m_delay = 0;
    public CloseGearHandler() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearHandler);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gearHandler.getPoleSensorState()){
    		//if we hit the switch open gear handler
    		
    		m_delay = 0;
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
    	}else{
    		m_delay++;
    		if(m_delay >= 50){
    			Robot.gearHandler.moveGearHandlerTogether(-0.3);
    		}
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
