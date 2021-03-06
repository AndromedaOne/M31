package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenGearHandlerInAuto extends Command {
	private double m_outSpeed = 0.5;
	private double m_inSpeed = -0.2;
	private double m_delay = 0;
    public OpenGearHandlerInAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearHandler);
    	
    }


    // Called just before this Command runs the first time
    protected void initialize() {
    	m_delay = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_delay++;
    	
    		Robot.gearHandler.moveLeftGearHandler(m_outSpeed);
    	
    		Robot.gearHandler.moveRightGearHandler(m_outSpeed);
    	
    	
    }

    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(m_delay < 70){
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
