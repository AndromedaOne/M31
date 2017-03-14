package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ControlledFeederStop extends Command {
	private int m_safetyCount;
    public ControlledFeederStop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.Shooter.getSafetySwitch() == true){
    		m_safetyCount++;
    	}
    	else{
    		m_safetyCount = 0;
    	}
		if(m_safetyCount < 25){
			Robot.Shooter.moveFeederUntilSwitchPressed();
		}
		else{
			Robot.Shooter.spinFeederCCW();
		}
    	
    	Robot.Shooter.stopFeeder();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.Shooter.getSafetySwitch();
    	
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
