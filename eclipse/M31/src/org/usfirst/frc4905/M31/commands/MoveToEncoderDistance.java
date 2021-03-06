package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveToEncoderDistance extends Command {
	
	private double m_distanceToMove;

    public MoveToEncoderDistance(double distanceToMove) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	m_distanceToMove = distanceToMove;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.initializeYEncoderPID(m_distanceToMove);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.isDoneMovingToYEncoderRevolutions();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	Robot.driveTrain.stopMovingToYEncoderRevolutions();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
