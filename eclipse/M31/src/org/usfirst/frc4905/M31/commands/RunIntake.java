package org.usfirst.frc4905.M31.commands;



import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntake extends Command {
	private int m_delay = 25;
    public RunIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.fuelIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.fuelIntake.stopIntake();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getSubGamePad().getRawButton(3) && m_delay > 24){
    		Robot.fuelIntake.toggle();
    		m_delay = 0;
    	}
    	m_delay++;
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
