// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4905.M31;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4905.M31.commands.*;
import org.usfirst.frc4905.M31.groupCommands.GroupCloseHopperFireVision;
import org.usfirst.frc4905.M31.groupCommands.GroupDoNothing;
import org.usfirst.frc4905.M31.groupCommands.GroupLiftVisionLeft;
import org.usfirst.frc4905.M31.groupCommands.GroupLiftVisionRight;
import org.usfirst.frc4905.M31.groupCommands.GroupMiddleLiftVision;
import org.usfirst.frc4905.M31.groupCommands.GroupMovePastBaseline;
import org.usfirst.frc4905.M31.groupCommands.GroupShootFromStartCrossBaseLine;
import org.usfirst.frc4905.M31.subsystems.*;

import com.ctre.CANTalon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	SendableChooser autoChooser;
	SendableChooser sideChooser;
	private int m_side = 1;
	
    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static FuelIntake fuelIntake;
    public static RopeClimber robotClimber;
    public static GearHandler gearHandler;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private boolean m_initialAngleReadingSet = false;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
       
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        gearHandler = new GearHandler();
        robotClimber = new RopeClimber();
        fuelIntake = new FuelIntake();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        
        /*Camera code!
        If cameras exceed bandwidth, lower resolution but keep 16:9 Aspect Ratio
        Or lower refresh/frame rate NO LOWER THAN TEN FPS!*/
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
       // UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture();
        	//Camera 1
        camera.setResolution(150, 150);
        camera.setFPS(20);
        	//Camera 2
      /*  camera1.setResolution(150, 150);
        camera1.setFPS(20);*/
       //end camera code
        
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


        autonomousCommand = new AutonomousCommand();
        
        autoChooser = new SendableChooser();
        sideChooser = new SendableChooser();
        
        autoChooser.addObject("Move Forward", new GroupMovePastBaseline());
        autoChooser.addObject("Left Lift With Vision", new GroupLiftVisionLeft());
        autoChooser.addObject("Right Lift With Vision", new GroupLiftVisionRight());
        autoChooser.addObject("Middle Lift With Vision", new GroupMiddleLiftVision());
        autoChooser.addObject("Shoot From Start, Cross Baseline", new GroupShootFromStartCrossBaseLine());
        autoChooser.addObject("Close Hopper, Fire" , new GroupCloseHopperFireVision());
        autoChooser.addDefault("Do Nothing", new GroupDoNothing());
        
        
        sideChooser.addObject("Red", m_side = 1);
        sideChooser.addObject("Blue", m_side = -1);
        
        SmartDashboard.putData("Auto Mode Chooser", autoChooser);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
       
        
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        
        // As per section 21.18 of the Talon SRX Software manual,
        // set an innocuous setting (such as the profile, which
        // we only use profile 0).
    	RobotMap.driveTrainFrontLeft.setProfile(0);
    	RobotMap.driveTrainBackRight.setProfile(0);
    	RobotMap.driveTrainFrontRight.setProfile(0);
    	RobotMap.driveTrainBackLeft.setProfile(0);
    }

    public void autonomousInit() {
    	setInitialAngle();
        // schedule the autonomous command (example)
    	autonomousCommand = (Command) autoChooser.getSelected();
    	if (autonomousCommand != null) autonomousCommand.start();
    }

    private void setInitialAngle() {
    	if(!m_initialAngleReadingSet) {
    		RobotMap.getNavxGyro().setInitialAngleReading();
    		m_initialAngleReadingSet = true;
    		System.out.println("Initial Angle Set Is Calibrating = " + 
    				RobotMap.getNavxGyro().isCalibrating());
    	}
		
	}

	/**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        setInitialAngle();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    
    public int getSide(){
    	return m_side;
    }
}
