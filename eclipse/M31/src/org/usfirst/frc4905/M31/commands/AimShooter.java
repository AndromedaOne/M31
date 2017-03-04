package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AimShooter extends Command {
	private double m_targetPot;
    public AimShooter(double targetPot) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Shooter);
    	m_targetPot = targetPot;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.Shooter.getPotReading() - m_targetPot < 0){
    		Robot.Shooter.moveShooterServo(-1);
    	}else{
    		Robot.Shooter.moveShooterServo(1);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.Shooter.IsPotCloseEnough(m_targetPot);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Shooter.moveShooterServo(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
