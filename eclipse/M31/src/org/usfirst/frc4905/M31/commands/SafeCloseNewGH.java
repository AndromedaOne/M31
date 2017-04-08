package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SafeCloseNewGH extends Command {
	
	private boolean startingAllOpen = false;
	private static int m_delay = 27;
	private int m_counter = 0;
    public SafeCloseNewGH() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.newGH);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.newGH.getGHopenState() == true){
    		startingAllOpen = true;
    	}else{
    		startingAllOpen = false;
    	}
    	
    	m_counter = 0;
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!startingAllOpen){
    		//do nothing...
    	}
    	else{
    		if(m_counter < m_delay){
    			Robot.newGH.clawOpenCLose(-0.9);
    		}else{
    			Robot.newGH.clawOpenCLose(0);
    		}
    		m_counter++;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       if(!startingAllOpen){
    	   return true;
       }
       
       if(m_counter < m_delay){
    	   return false;
       }
       else{
    	   return true;
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
