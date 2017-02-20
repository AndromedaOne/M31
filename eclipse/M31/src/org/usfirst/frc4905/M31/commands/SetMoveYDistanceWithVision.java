package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetMoveYDistanceWithVision extends Command {
	MoveY m_moveY; 
    public SetMoveYDistanceWithVision(MoveY moveY) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_moveY = moveY;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double distance = 0;
    	if (Robot.visionProcessing.getFoundLift()){
    		distance = Robot.visionProcessing.getLateralDistance()*1; //add scale factor
    	}
    	m_moveY.setDistanceToMoveY(distance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
