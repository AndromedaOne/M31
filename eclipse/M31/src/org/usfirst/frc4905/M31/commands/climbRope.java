package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;
import org.usfirst.frc4905.M31.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class climbRope extends Command {
	
	
	private double m_current = 0;

    public climbRope() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.robotClimber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_current = RobotMap.CtrPdp.getCurrent(2);
    	if (m_current > 35) {
    		Robot.robotClimber.setHitCurrentTrue();
    	}
    	
    	
    	if (Robot.robotClimber.getHitCurrentMax() == false){
    	Robot.robotClimber.climbRope();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.robotClimber.stopClimbing();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
