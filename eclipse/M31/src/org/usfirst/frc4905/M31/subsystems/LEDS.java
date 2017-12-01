package org.usfirst.frc4905.M31.subsystems;

import org.usfirst.frc4905.M31.RobotMap;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDS extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static PWM red = RobotMap.redVal;
	private static PWM blue = RobotMap.blueVal;
	private static PWM green = RobotMap.greenVal;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void setPurple(){
    	red.setRaw(255);
    	blue.setRaw(255);
    }
}

