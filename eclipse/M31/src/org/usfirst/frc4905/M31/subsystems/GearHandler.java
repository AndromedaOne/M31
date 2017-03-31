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
	private final static VictorSP rightSP = RobotMap.gearHandlerRight;
	private final static VictorSP leftSP = RobotMap.gearHandlerLeft;
	private final DigitalInput rightSwitch = RobotMap.gearHandlerRightSwitch;

	private final static DigitalInput poleSensor = RobotMap.gearHandlerSensePoleSwitch;
	private static boolean m_ClosedState = true;
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
	
	public void moveGearHandlerTogether(double speed){
		moveRightGearHandler(speed);
		moveLeftGearHandler(speed);
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
	
	public void setClosedState(){
		m_ClosedState = !m_ClosedState;
	}
	
	public static boolean getClosedState(){
		return m_ClosedState;
	}
	
	public static void toggle(){
		
	}
	
	public static boolean getPoleSensorState(){
		return poleSensor.get();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new TeleopToggleGearHandler());
    	//need this to be default so we can always be reading joystick buttons within the command.
    }
    
    
}

