package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class readyCam extends Command {

    public readyCam() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	Robot.driveTrain.getCam0();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       /*UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(16, 9);
        camera.setFPS(20);
        //camera.setExposureAuto();
        camera1.setResolution(16, 9);
        camera1.setFPS(20);
        //camera1.setExposureAuto(); */
    	System.out.println("Dana's a [DATA EXPUNGED FOR THE BENEFIT OF CHILDREN]");
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
