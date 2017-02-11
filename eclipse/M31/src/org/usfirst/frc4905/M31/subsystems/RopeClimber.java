package org.usfirst.frc4905.M31.subsystems;

import org.usfirst.frc4905.M31.RobotMap;
import org.usfirst.frc4905.M31.commands.climbRope;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class RopeClimber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//declarations
	public VictorSP ropeMotor;
	public void climbRope() {
		ropeMotor.setSpeed(0.4);
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new climbRope());
    }
}

