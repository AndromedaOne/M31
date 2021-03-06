package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveX extends Command {

	private double m_distance = 0;
	
    public MoveX() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }
    
    public MoveX(double distance) {
    	
    	requires (Robot.driveTrain);
    	setDistanceToMoveX(distance);
    }
    
    public void setDistanceToMoveX(double distance){
    	m_distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.driveTrain.moveToXEncoderRevolutions(m_distance);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return Robot.driveTrain.isDoneMovingToXEncoderRevolutions();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stopMovingToXEncoderRevolutions();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
