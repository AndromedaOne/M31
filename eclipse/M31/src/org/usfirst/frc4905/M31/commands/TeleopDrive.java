// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4905.M31.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4905.M31.OI;
import org.usfirst.frc4905.M31.Robot;
import org.usfirst.frc4905.M31.RobotMap;

/**
 *
 */
public class TeleopDrive extends Command {
	private final double kDeadzone = 0.15;
	private int m_delay = 25;
	private boolean slowModeEnabled = false;
	double mod = 1;  	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public TeleopDrive() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=R	OBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("unused")
	protected void execute() {
    	
    	
    	Joystick driveGamepad = Robot.oi.getDriveController();
    	
    	
    	
    	
    	
    	double xIn = OI.getLeftStickHorizontal(driveGamepad);
		double yIn = OI.getLeftStickVertical(driveGamepad);
		double rotation = OI.getRightStickHorizontal(driveGamepad);
		if(xIn < kDeadzone && xIn > -kDeadzone) {
			xIn = 0;
		}
		if(yIn < kDeadzone && yIn > -kDeadzone) {
			yIn = 0;
		}
		if(rotation < kDeadzone && rotation > -kDeadzone) {
			rotation = 0;
		}
		//24 = about half a second
		if(m_delay > 24 && Robot.oi.getDriveController().getRawButton(5)){
			m_delay = 0;
			if(!slowModeEnabled){
				mod = 0.2;
				slowModeEnabled = true;
				System.out.println("Slowmode started");
			}else{
				mod = 1;
				slowModeEnabled = false;
				System.out.println("Slowmode ended");
			} 
		}
		//System.out.print("xIn: " + xIn * mod + " yIn: " + yIn * mod + " rotation " + rotation * mod + "\n");
		m_delay++;
		if(Robot.oi.getDriveController().getRawButton(7)){
			xIn = 0;
		}
		//the below code accounts for strafing drift from untuned pid speed control loops on mecanum wheels. may be irrelevant after
		//tuning the loops and or adding a wheel in the middle of the robot
		if(Robot.oi.getDriveController().getRawButton(6)){
			Robot.driveTrain.useStrafeProfile();
			//xIn = xIn* 0.4;
			if(Robot.oi.getDriveController().getRawAxis(0) > 0.15){
				yIn = 0.06; 
			}else{
				yIn = -0.07;
			}
		}else{
			Robot.driveTrain.useNormalProfile();
		}
		//System.out.println("xIn: " + xIn + "yIn: " + yIn + "rotation" + rotation);
		//System.out.println(Robot.Ul)
		
			Robot.driveTrain.mecanumDrive(xIn * mod, yIn * mod, rotation * mod);
		
		
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.stop();
    }
}
