package org.usfirst.frc4905.M31.subsystems;

import org.usfirst.frc4905.M31.RobotMap;
import org.usfirst.frc4905.M31.commands.climbRope;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class RopeClimber extends Subsystem {
	
	public boolean hitCurrentThreshold = false;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//declarations
	public VictorSP ropeMotor = RobotMap.ropeClimbMotor;
	
	
	public void stopClimber(){
		ropeMotor.setSpeed(0);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new climbRope());
    }
    public void climbRope() {
		ropeMotor.setSpeed(1);
		
	}
    public void stopClimbing() {
    	ropeMotor.setSpeed(0);
    }
    
    public boolean getHitCurrentMax(){
    	return hitCurrentThreshold;
    }
    
    public void setHitCurrentTrue(){
    	hitCurrentThreshold = true;
    }
}

