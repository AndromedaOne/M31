package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NewGHopenClose extends Command {

	private double m_speed = 0;
	
    public NewGHopenClose(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.newGH);
    	
    	m_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//negative closes, positive opens
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(m_speed > 0){
    		if(Robot.newGH.getGHopenState() == true){
    			Robot.newGH.clawOpenCLose(0);
    		}
    		else{
    			Robot.newGH.clawOpenCLose(m_speed);
    		}
    	}
    	else{
    		if(Robot.newGH.getGHcloseState() == true){
    			Robot.newGH.clawOpenCLose(0);
    		}else{
    			Robot.newGH.clawOpenCLose(m_speed);
    		}
    	}
    	
    	Robot.newGH.clawOpenCLose(m_speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (m_speed > 0) {
    		//assuming positive is opening it
    		if (Robot.newGH.getGHopenState() == true){
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else {
    		if (Robot.newGH.getGHcloseState() == false){
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
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
