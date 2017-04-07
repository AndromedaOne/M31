package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopNewGH extends Command {

    public TeleopNewGH() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.newGH);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Joystick subGamepad = Robot.oi.getSubGamePad();
    	int povReading = subGamepad.getPOV();
    	
    	if(povReading == -1){
    		//-1 means nothing is being pressed
    		Robot.newGH.clawOpenCLose(0);
    		Robot.newGH.moveUpDown(0);
    	}
    	if(povReading == 0){
    		//0 means pointing up, raise gh
    		if(Robot.newGH.getGHupState() == true){
    			Robot.newGH.clawOpenCLose(0);
        		Robot.newGH.moveUpDown(0);
    		}
    		else{
    			Robot.newGH.clawOpenCLose(0);
        		Robot.newGH.moveUpDown(-0.65);	
    		}
    		
    	}
    	if(povReading == 90){
    		//90 means we're pushed to the right, close ground gh (close cuz pushing towards the middle of the controller)
    		if(Robot.newGH.getGHcloseState() == true){
    			Robot.newGH.clawOpenCLose(0);
        		Robot.newGH.moveUpDown(0);
    		}else{
    			Robot.newGH.clawOpenCLose(-0.4);
        		Robot.newGH.moveUpDown(0);
    		}
    		
    	}
    	if(povReading == 180){
    		//180 means down, lower ground GH
    		if(Robot.newGH.getGHdownState() == true){
    			Robot.newGH.clawOpenCLose(0);
        		Robot.newGH.moveUpDown(0);
    		}
    		else{
    			Robot.newGH.clawOpenCLose(0);
        		Robot.newGH.moveUpDown(0.75);
    		}
    		
    	}
    	if(povReading == 270){
    		//270 means left, want to open ground gh
    		
    		if(Robot.newGH.getGHopenState() == true){
    			Robot.newGH.clawOpenCLose(0);
        		Robot.newGH.moveUpDown(0);
    		}
    		else{
    			Robot.newGH.clawOpenCLose(0.875);
        		Robot.newGH.moveUpDown(0);
    		}
    		
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.newGH.moveUpDown(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
