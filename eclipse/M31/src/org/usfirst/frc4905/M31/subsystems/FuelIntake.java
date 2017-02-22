package org.usfirst.frc4905.M31.subsystems;

import org.usfirst.frc4905.M31.RobotMap;
import org.usfirst.frc4905.M31.commands.RunIntake;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FuelIntake extends Subsystem {
	private boolean intakeIsRunning = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private final VictorSP intakeMotor = RobotMap.intakeMotor;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new RunIntake());
    }
    
    public void startIntake() {
    	
    	intakeMotor.setSpeed(1.0);
    	
    }   
    
    public void stopIntake() {
    	
    	intakeMotor.setSpeed(0);
    	
    }
    
    public void reverseIntake(){
    	intakeMotor.setSpeed(-1);
    }
    
    public void toggle(){
    	
    	if(intakeIsRunning == false) {
    		startIntake();
    		intakeIsRunning = true;
    	}
    	else{
    		stopIntake();
    		intakeIsRunning = false;
    	}
    }
    
    
}

