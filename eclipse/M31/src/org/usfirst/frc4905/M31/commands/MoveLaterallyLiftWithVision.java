package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLaterallyLiftWithVision extends Command {
    MoveY m_moveY;
    private double m_distance;
	public MoveLaterallyLiftWithVision() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double yEncoderKp = Robot.driveTrain.getYEncoderKp();
    	Robot.driveTrain.setI(yEncoderKp/18);
    	Robot.driveTrain.setTolerance(0.05);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.visionProcessing.initDataForLift();
    	double distance = 0;
    	if (Robot.visionProcessing.getFoundLift()){
    		distance = -(Robot.visionProcessing.getLateralDistance()*0.111); //add scale factor
    	}
    	else{
    		Robot.visionProcessing.putTimestampOnNetworkTables();
    	}
    	Robot.driveTrain.moveToYEncoderRevolutions(distance);
    	m_distance = distance;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.isDoneMovingToYEncoderRevolutions() && m_distance < 0.05 && m_distance > -0.05;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
