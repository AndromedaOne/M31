package org.usfirst.frc4905.M31.subsystems;

import org.usfirst.frc4905.M31.RobotMap;
import org.usfirst.frc4905.M31.commands.TeleopNewGH;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NewGH extends Subsystem {
//Declarations
	private final static Spark clawSP = RobotMap.newGHclaw;
	private final static Talon raiselowerSP = RobotMap.newGHraiselower;
	private final static DigitalInput newGHopen = RobotMap.newGHopen;
	private final static DigitalInput newGHclose = RobotMap.newGHclose;
	private final static DigitalInput newGHup = RobotMap.newGHup;
	private final static DigitalInput newGHdown = RobotMap.newGHdown;
	private final static DigitalInput newGHoptical = RobotMap.newGHoptical;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TeleopNewGH());
    }
    
    
    
    
    
    
    public void moveUpDown(double speed) {
    	raiselowerSP.setSpeed(speed);
    	
    }
    
    public void clawOpenCLose(double speed) {
    	clawSP.setSpeed(speed);
    }
    
    //all these limit switches are true when not hit, false when hit
    
    public boolean getGHopenState(){
    	//We don't know if true is open or close!!!
    	return !newGHopen.get();
    }
    
    public boolean getGHcloseState(){
    	//We don't know if true is open or close!!!
    	return !newGHclose.get();
    }
    
    public boolean getGHupState(){
    	//We don't know if true is open or close!!!
    	return !newGHup.get();
    }
    
    public boolean getGHdownState(){
    	//We don't know if true is open or close!!!
    	return !newGHdown.get();
    }
}

