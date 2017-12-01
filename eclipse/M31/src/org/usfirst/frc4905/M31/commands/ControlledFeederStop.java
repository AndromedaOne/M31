package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ControlledFeederStop extends Command {
	private int m_safetyCount = 0;
	private boolean m_stuck = false;
    public ControlledFeederStop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_stuck = false;
    	m_safetyCount = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	m_safetyCount++;
    	if(m_safetyCount < 80){
    		Robot.Shooter.spinFeederAtSpeed(-0.75);
    	}
    	else{
    		Robot.Shooter.stopFeeder();
    		m_stuck = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return !Robot.Shooter.getSafetySwitch() || m_stuck;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Shooter.stopFeeder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
