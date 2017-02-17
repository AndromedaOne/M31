package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleGearHandler extends Command {
	
	private int m_threshold = 0;
	private double m_outSpeed = 0.4;
	private double m_inSpeed = -0.2;
    public ToggleGearHandler() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//start from a state where both gates are closed.
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if we're closed, do this
    	if(Robot.gearHandler.getClosedState()){
    		//if we aren't hitting the switch...
    		if(!Robot.gearHandler.shouldStopMovingRight()){
    			Robot.gearHandler.moveRightGearHandler(m_outSpeed);
    		}else{
    			Robot.gearHandler.stopMovingRight();
    		}
    		//if we aren't hitting the switch...
    		if(Robot.gearHandler.shouldStopMovingLeft()){
    			Robot.gearHandler.moveLeftGearHandler(m_outSpeed);
    		}else{
    			Robot.gearHandler.stopMovingLeft();
    		}
    		if(Robot.gearHandler.shouldStopMovingLeft()&& Robot.gearHandler.shouldStopMovingRight()){
    			Robot.gearHandler.setClosedState();
    		}
    	}else{
    		Robot.gearHandler.moveLeftGearHandler(m_inSpeed);
    		Robot.gearHandler.moveRightGearHandler(m_inSpeed);
    		Robot.gearHandler.setClosedState();
    	}
    
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      return false;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	//if we're open, remain stopped
    	if(!Robot.gearHandler.getClosedState()){
    		Robot.gearHandler.stopMovingLeft();
        	Robot.gearHandler.stopMovingRight();
    	}
    	//if we're closed though, keep applying power.
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
