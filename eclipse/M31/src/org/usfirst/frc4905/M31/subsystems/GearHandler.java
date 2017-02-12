package org.usfirst.frc4905.M31.subsystems;

import org.usfirst.frc4905.M31.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearHandler extends Subsystem {
	//Declarations
	private final VictorSP rightSP = RobotMap.gearHandlerRight;
	private final VictorSP leftSP = RobotMap.gearHandlerLeft;
	private final DigitalInput rightSwitch = RobotMap.gearHandlerRightSwitch;
	private final DigitalInput leftSwitch = RobotMap.gearHandlerLeftSwitch;
	private final DigitalInput poleSensor = RobotMap.gearHandlerSensePoleSwitch;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	/*public void openHandler() {
		if (rightOpen.get() && leftOpen.get()) {
			rightSP.setSpeed(speed);
			leftSP.setSpeed(speed);
		}
	}
	public void closeHandler() {
		if(rightClosed.get() && leftClosed.get()) {
			rightSP.setSpeed(-speed);
			leftSP.setSpeed(-speed);
		}
	}
	
	public boolean isDoneOpen() {
		if(!rightOpen.get() && !leftOpen.get()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isDoneClose() {
		if(!rightClosed.get() && !leftClosed.get()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void stopAll() {
		rightSP.setSpeed(0);
		leftSP.setSpeed(0);
	}
	*/
	
	public void moveRightGearHandler(double speed){
		rightSP.setSpeed(speed);
	}
	
	public void moveLeftGearHandler(double speed){
		leftSP.setSpeed(-speed);
	}
	
	public boolean shouldStopMovingLeft(){
		
		//if it is false, it is being pressed
		if(leftSwitch.get() == false){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	public boolean shouldStopMovingRight(){
		//if it is false, it is being pressed
		if(rightSwitch.get() == false){
			return true;
		}else{
			return false;
		}
	}
	
	public void stopMovingLeft(){
		leftSP.set(0);
	}
	
	public void stopMovingRight(){
		rightSP.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    
}

