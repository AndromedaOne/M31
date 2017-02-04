package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import NavXGyro.NavxGyro;
import Utilities.Pair;
import Utilities.TurnDirection;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToCompassHeading extends Command {

	private double m_heading;

	public TurnToCompassHeading(double heading) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		m_heading = heading;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("Initialize TurnToCompassHeading");
		double deltaAngle = Robot.driveTrain.initializeTurnToCompass(m_heading);
		Robot.driveTrain.initializeGyroPID(deltaAngle);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//m_heading = Robot.driveTrain.getRobotAngle() % 360 ;
	}


	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.driveTrain.doneTurningWithGyro();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stopGyroPID();
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
