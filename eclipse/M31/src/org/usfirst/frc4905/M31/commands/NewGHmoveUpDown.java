package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 *
 *Written by Dana and Jonathan
 *
 *
 *
 */
public class NewGHmoveUpDown extends Command {

	private double m_speed = 0;

	public NewGHmoveUpDown(double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Robot.newGH);

		m_speed = speed;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//negative is up, positive is down
		
		if(m_speed < 0){
			if (Robot.newGH.getGHupState() == true){
				Robot.newGH.moveUpDown(0);

			}
			else {
				Robot.newGH.moveUpDown(m_speed);
			}

		}else{
			if (Robot.newGH.getGHdownState() == true){
				Robot.newGH.moveUpDown(0);

			}
			else {
				Robot.newGH.moveUpDown(m_speed);
			}

		}


	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(m_speed <  0) {
			//we think positive brings us towards being closed at the top
			if (Robot.newGH.getGHupState() == true){
				return true;

			}
			else {
				return false;
			}
		}
		else{
			//we think negative is bringing us to open @ the bottom!
			if (Robot.newGH.getGHdownState() == true) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		//written by Jonathan
		Robot.newGH.moveUpDown(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
