package org.usfirst.frc4905.M31.subsystems;

import org.usfirst.frc4905.M31.RobotMap;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FuelIntake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private final VictorSP intakeMotor = RobotMap.intakeMotor;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startIntake() {
    	
    	intakeMotor.setSpeed(0.2);
    	
    }   
    
    public void stopIntake() {
    	
    	intakeMotor.setSpeed(0);
    	
    }
    
    public void toggle(){
    	
    	if(intakeMotor.getSpeed()>0) {
    		stopIntake();
    	}
    	else{
    		startIntake();
    	}
    }
    
    
}

