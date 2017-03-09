package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveY extends Command {
	
	private double m_distance = 0;

    public MoveY() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }
    
    public MoveY(double distance) {
    	
    	requires (Robot.driveTrain);
    	setDistanceToMoveY(distance);
    }
    
    public void setDistanceToMoveY(double distance) {
    	m_distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.driveTrain.moveToYEncoderRevolutions(m_distance);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Error:" +Robot.driveTrain.getYPIDcontroller().getError());
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.isDoneMovingToYEncoderRevolutions();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stopMovingToYEncoderRevolutions();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
