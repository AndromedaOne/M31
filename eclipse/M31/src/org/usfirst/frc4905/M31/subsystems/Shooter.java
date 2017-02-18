package org.usfirst.frc4905.M31.subsystems;

import org.usfirst.frc4905.M31.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public VictorSP feederMotor = RobotMap.feederMotor;
	public CANTalon shooterMotor = RobotMap.shooterMotor;
	public DigitalInput safetySwitch = RobotMap.shooterSafetySwitch;
	private boolean m_amAtSpeed = false;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void spinFeederCW(){
    	//clockwise is the way we want to go to feed. it is a negative speed
    	feederMotor.set(-0.75);
    }
    
    public void spinFeederCCW(){
    	feederMotor.set(0.25);
    }
    
    public void stopFeeder(){
    	feederMotor.set(0);
    }
    
    public void setShooterTargetSpeed(int speed){
    	shooterMotor.changeControlMode(TalonControlMode.Speed);
    	shooterMotor.set(speed);
    	
    }
    
    public void stopShooter(){
    	shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
    	shooterMotor.set(0);
    }
    
    public CANTalon getShooterMotor(){
    	return shooterMotor;
    }
    
    public void setWhetherAmAtSpeed(){
    	if(shooterMotor.getError() < 7 && shooterMotor.getError() > -7){
    		m_amAtSpeed = true;
    	}
    	else{
    		m_amAtSpeed = false;
    	}
    }
    public void setWhetherAmAtSpeed(boolean amAtSpeed){
    	m_amAtSpeed = amAtSpeed;
    }
    public boolean getWhetherAmAtSpeed(){
    	return m_amAtSpeed;
    }
    
    public boolean getSafetySwitch(){
    	return safetySwitch.get();
    }
    
    public void moveFeederUntilSwitchPressed(){



    	if(safetySwitch.get()){

    		feederMotor.set(-0.3);

    	}
    	else{
    		feederMotor.set(0);

    	}
    }



}
