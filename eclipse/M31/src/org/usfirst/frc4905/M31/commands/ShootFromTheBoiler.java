package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootFromTheBoiler extends Command {
	private int m_speed;
	private int m_safetyCount;
    public ShootFromTheBoiler(int speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Shooter);
    	m_speed = speed;
    	m_safetyCount = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		Robot.Shooter.setShooterTargetSpeed(m_speed);
    	
    	System.out.print("Shooter Speed" + Robot.Shooter.getShooterMotor().getSpeed());
    	System.out.println("Encoder Speed" + Robot.Shooter.getShooterMotor().getEncVelocity());
    	Robot.Shooter.setWhetherAmAtSpeed();
    	if(Robot.Shooter.getWhetherAmAtSpeed()){
    		if(Robot.Shooter.getSafetySwitch() == true){
        		m_safetyCount++;
        	}
        	else{
        		m_safetyCount = 0;
        	}
    		if(m_safetyCount < 25){
    			Robot.Shooter.spinFeederCW();
    		}
    		else{
    			Robot.Shooter.spinFeederCCW();
    		}
    	}else{
    		Robot.Shooter.stopFeeder();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.Shooter.stopShooter();
    	Robot.Shooter.setWhetherAmAtSpeed(false);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
