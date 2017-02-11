package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;
import org.usfirst.frc4905.M31.subsystems.VisionProcessing.dataForLift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class getLiftVisionProcessingInformation extends Command {
	dataForLift data; //= new dataForLift();
    public getLiftVisionProcessingInformation() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	data = Robot.visionProcessing.getDataForLift();
    	Robot.visionProcessing.saveDataForLift(data.foundLift,data.angletoTurn,data.distanceAwayForward,
    			data.distanceAwayLateral, data.timestamp);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
