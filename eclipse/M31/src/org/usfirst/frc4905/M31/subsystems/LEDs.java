package org.usfirst.frc4905.M31.subsystems;

import org.usfirst.frc4905.M31.Robot;
import org.usfirst.frc4905.M31.RobotMap;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDs extends Subsystem {
	private final PWM redValue = RobotMap.redValue;
	private final PWM blueValue = RobotMap.blueValue;
	private final PWM greenValue = RobotMap.greenValue;
	
	int curRVal = 255;
	int curGVal = 255;
	int curBVal = 255;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void setRed(int rValue) {
		redValue.setRaw(rValue);
	}
	
	public void setGreen(int gValue) {
		greenValue.setRaw(gValue);
	}
	
	public void setBlue(int bValue) {
		blueValue.setRaw(bValue);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    /**
     * Uses the three methods defined above to write a color to the LED strands.
     * @param red A value up to 255, controls the Red value
     * @param green A value up to 255, controls the Green value
     * @param blue A value up to 255, controls the Blue value
     */
    public void writeColor(int red, int green, int blue) {
    	setRed(red);
    	setGreen(green);
    	setBlue(blue);
    }
    public void modePurple() {
    	Robot.ledCtrl.writeColor(255, 0, 255);
    }
    
    public void modeRainbow() {
    	Robot.ledCtrl.writeColor(curRVal, curGVal, curBVal);
    	curRVal = curRVal - 10;
    	curGVal = curGVal - 10;
    	curBVal = curBVal - 10;
    	System.out.println(curRVal + curGVal + curBVal);
    	Timer.delay(.5);
    	if(curRVal <= 0 || curGVal <= 0 || curBVal <= 0) {
    		if(curRVal <= 0) {
    			curRVal = 255;
    		} else if(curGVal <= 0) {
    			curGVal = 255;
    		} else if(curBVal <= 0) {
    			curBVal = 255;
    		}
    	}
    }
}

