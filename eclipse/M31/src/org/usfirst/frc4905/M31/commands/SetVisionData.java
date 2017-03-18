package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetVisionData extends Command {
		private boolean m_isFinished = false; 
    public SetVisionData() {

    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.visionProcessing.isVisionReady()){
    		m_isFinished = false;
    		return;
    		
    		
    	}
    		
    		
	    System.out.println("Starting");
		Robot.visionProcessing.initDataForLift();
		System.out.println("Found target: " + Robot.visionProcessing.getFoundLift());
		if (Robot.visionProcessing.getFoundLift() == true){
			System.out.println("Angle to turn: " + Robot.visionProcessing.getDeltaAngle());
			System.out.println("Distance to move forward: " + Robot.visionProcessing.getForwardDistance());
			System.out.println("Distance to move laterally: " + Robot.visionProcessing.getLateralDistance());
		}
		else{
			Robot.visionProcessing.putTimestampOnNetworkTables();
		}
		m_isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return m_isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
