package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleGearHandler extends Command {
	private int m_delay = 0;
	private int m_threshold = 0;
    public ToggleGearHandler() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//if we're starting from a state where the limits are pressed...
    	if(Robot.gearHandler.shouldStopMovingLeft()&&Robot.gearHandler.shouldStopMovingRight()){
    		m_threshold = 10;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gearHandler.getClosedState()){
    		if(!Robot.gearHandler.shouldStopMovingRight()){
    			Robot.gearHandler.moveRightGearHandler(0.25);
    			System.out.println("Going Out!");
    		}
    		else if(m_delay > m_threshold){
    			Robot.gearHandler.moveRightGearHandler(0);
    		}
    		if(!Robot.gearHandler.shouldStopMovingLeft()){
    			Robot.gearHandler.moveLeftGearHandler(0.25);
    			
    		}
    		else if(m_delay > m_threshold){
    			Robot.gearHandler.moveLeftGearHandler(0);
    		}
    	}
    	else{
    		if(!Robot.gearHandler.shouldStopMovingRight()){
    			Robot.gearHandler.moveRightGearHandler(-0.25);
    			System.out.println("Going In!");
    		}
    		else if(m_delay > m_threshold){
    		
    			Robot.gearHandler.moveRightGearHandler(0);
    		}
    		if(!Robot.gearHandler.shouldStopMovingLeft()){
    			Robot.gearHandler.moveLeftGearHandler(-0.25);
    		}
    		else if(m_delay > m_threshold){
    			Robot.gearHandler.moveLeftGearHandler(0);
    		}
    		
    	}
    	m_delay++;
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       if(m_delay < m_threshold){
    	   return false;
       }else{
    	   if(Robot.gearHandler.shouldStopMovingLeft() && Robot.gearHandler.shouldStopMovingRight()){
           	Robot.gearHandler.setClosedState();
           	System.out.println("Called isFinished!!!");
           	return true;
           }else{
           	System.out.println("Not Calling isFinished");
           	return false;
           } 
       }
    	
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
