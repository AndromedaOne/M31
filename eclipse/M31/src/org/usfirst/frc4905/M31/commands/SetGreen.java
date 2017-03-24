package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetGreen extends Command {
	
	private int m_value = 0;
	
    public SetGreen(int value) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ledCtrl);
    	if (value < 255) {
    		m_value = value;
    	} else {
    		System.out.println("Uh-oh, you need to set a value of less than or equal to 255, or the RGB strands will not work!");
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ledCtrl.setGreen(m_value);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
