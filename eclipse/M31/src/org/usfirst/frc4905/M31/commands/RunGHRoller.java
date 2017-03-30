package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunGHRoller extends Command {
	private double m_speed = 0;
    public RunGHRoller(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (Robot.newGH);
    	m_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.newGH.spinRoller(m_speed);
    }
    
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end(){
    	
    	
    	Robot.newGH.spinRoller(0);
    	
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    	
    	
    }
}
