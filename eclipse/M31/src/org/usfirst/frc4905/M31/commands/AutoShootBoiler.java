package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class AutoShootBoiler extends TimedCommand {
	private int m_speed;
	private int m_safetyCount;
	private int m_ccwSafetyCount;
    public AutoShootBoiler(double timeout, int speed) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.Shooter);
        m_speed = speed;
    	m_safetyCount = 0;
    	m_ccwSafetyCount = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
  	Robot.Shooter.setShooterTargetSpeed(m_speed);
    	
    	System.out.print("Shooter Speed" + Robot.Shooter.getShooterMotor().getSpeed());
    	System.out.println("Error" + Robot.Shooter.getShooterMotor().getError());
    	Robot.Shooter.setWhetherAmAtSpeed();
    	//if(Robot.Shooter.getWhetherAmAtSpeed()){
    	if(Robot.Shooter.getSafetySwitch() == true){
    		m_safetyCount++;
    		m_ccwSafetyCount++;
    	}
    	else{
    		m_safetyCount = 0;
    		m_ccwSafetyCount = 0;
    	}
    	if(m_safetyCount < 70){
    		Robot.Shooter.spinFeederCW();
    		m_ccwSafetyCount = 0;
    	}
    	else{
    		Robot.Shooter.stopFeeder();

    	}
    }
    /*else
    	{
    		Robot.Shooter.stopFeeder();


    	}*/


    

    // Called once after timeout
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
