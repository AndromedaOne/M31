package org.usfirst.frc4905.M31.commands;



import org.usfirst.frc4905.M31.Robot;
import org.usfirst.frc4905.M31.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveUsingUltrasonic extends Command {

	private double m_distanceToDriveTo=0;

	public MoveUsingUltrasonic(double distanceToDriveTo) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		m_distanceToDriveTo = distanceToDriveTo;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.setOmniWheelEnabled(true);
		Robot.driveTrain.intializeUltrasonicPID(m_distanceToDriveTo);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.driveTrain.doneMovingWithUltrasoncPID();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
		Robot.driveTrain.stopUltrasonicPID();
		Robot.driveTrain.setOmniWheelEnabled(false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
