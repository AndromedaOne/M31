package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToRadianAngle extends Command {
	
	private double m_degreesAngleToTurnTo;

    public TurnToRadianAngle(double radianAngleToTurnTo) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	m_degreesAngleToTurnTo = radianAngleToTurnTo * 180 / Math.PI; 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.initializeGyroPID(m_degreesAngleToTurnTo);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.doneTurningWithGyro();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	Robot.driveTrain.stopGyroPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
