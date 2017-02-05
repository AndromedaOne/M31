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
	private final DigitalInput rightOpen = RobotMap.gearHandlerRightOpen;
	private final DigitalInput rightClosed = RobotMap.gearHandlerRightClosed;
	private final DigitalInput leftOpen = RobotMap.gearHandlerLeftOpen;
	private final DigitalInput leftClosed = RobotMap.gearHandlerLeftClosed;
	private final double speed = 0.1;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void openHandler() {
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
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

