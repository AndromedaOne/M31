package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDeltaAngleRadian extends Command {

	private double m_deltaAngleRadians;

	public TurnDeltaAngleRadian(double deltaAngleRadians) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		m_deltaAngleRadians = deltaAngleRadians * 180 / Math.PI; 
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.initializeGyroPID(m_deltaAngleRadians);
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
