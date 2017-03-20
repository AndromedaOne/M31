package org.usfirst.frc4905.M31.subsystems;

import org.usfirst.frc4905.M31.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NewGH extends Subsystem {
//Declarations
	private final static VictorSP clawSP = RobotMap.newGHclaw;
	private final static VictorSP raiselowerSP = RobotMap.newGHraiselower;
	private final static DigitalInput newGHopen = RobotMap.newGHopen;
	private final static DigitalInput newGHclose = RobotMap.newGHclose;
	private final static DigitalInput newGHup = RobotMap.newGHup;
	private final static DigitalInput newGHdown = RobotMap.newGHdown;
	private final static DigitalInput newGHoptical = RobotMap.newGHoptical;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    
    
    
    
    public void moveUpDown(double speed) {
    	raiselowerSP.setSpeed(speed);
    }
    
    public void clawOpenCLose(double speed) {
    	clawSP.setSpeed(speed);
    }
    
    public boolean getGHopenState(){
    	//We don't know if true is open or close!!!
    	return newGHopen.get();
    }
    
    public boolean getGHcloseState(){
    	//We don't know if true is open or close!!!
    	return newGHclose.get();
    }
    
    public boolean getGHupState(){
    	//We don't know if true is open or close!!!
    	return newGHup.get();
    }
    
    public boolean getGHdownState(){
    	//We don't know if true is open or close!!!
    	return newGHdown.get();
    }
}

