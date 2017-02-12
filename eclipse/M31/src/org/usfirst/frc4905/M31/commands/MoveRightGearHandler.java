package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveRightGearHandler extends Command {
	private double m_speed;
	private double counter = 0;
	private boolean startConfig;
    public MoveRightGearHandler(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearHandler);
    	m_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startConfig = Robot.gearHandler.shouldStopMovingRight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearHandler.moveRightGearHandler(m_speed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.gearHandler.shouldStopMovingRight() == false){
    		counter++;
    		if(counter > 40){
    			
    			return true;
    		}
    		
    	}else{
    		counter = 0;
    	}
    	return false;
    	 // return Robot.gearHandler.shouldStopMovingRight();
      
    	  
       
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearHandler.stopMovingRight();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
