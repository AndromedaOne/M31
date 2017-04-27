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
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4905.M31.commands.*;
import org.usfirst.frc4905.M31.groupCommands.GroupPosCHopperFire;
import org.usfirst.frc4905.M31.groupCommands.GroupShootFromStartCrossBaseLineBlue;
import org.usfirst.frc4905.M31.groupCommands.GroupDoNothing;
import org.usfirst.frc4905.M31.groupCommands.GroupLiftLeftVisionCorrect;
import org.usfirst.frc4905.M31.groupCommands.GroupLiftRightNoVision;
import org.usfirst.frc4905.M31.groupCommands.GroupLiftVisionRight;
import org.usfirst.frc4905.M31.groupCommands.GroupMiddleLift;
import org.usfirst.frc4905.M31.groupCommands.GroupMiddleLiftVision;
import org.usfirst.frc4905.M31.groupCommands.GroupMovePastBaseline;
import org.usfirst.frc4905.M31.groupCommands.GroupShootFromStartCrossBaseLineRed;
import org.usfirst.frc4905.M31.groupCommands.LeftLiftNewGH;
import org.usfirst.frc4905.M31.groupCommands.MiddleLiftNewGH;
import org.usfirst.frc4905.M31.groupCommands.RightLiftNewGH;
import org.usfirst.frc4905.M31.subsystems.*;

import com.ctre.CANTalon.*;

import Utilities.SideOfField;


import Utilities.Trace;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	SendableChooser<CommandGroup> autoChooser;
	SendableChooser<SideOfField> sideChooser;
	private static SideOfField m_side = SideOfField.Red;
	
	private int m_counter = 0;
	
    Command autonomousCommand;
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static FuelIntake fuelIntake;
    public static RopeClimber robotClimber;
    public static Shooter Shooter;
    public static GearHandler gearHandler;
    private static UltrasonicSubsystem ultrasonicGh;
    private static UltrasonicSubsystemFront ultrasonicFront;
    public static NewGH newGH;
    public static NewGHRoller newGHRoller;
    public static LEDS leds;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static VisionProcessing visionProcessing;
    private boolean m_initialAngleReadingSet = false;
    private RobotEnableStatus m_robotenabled;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	// Next call redirects output
    	Trace trace = Trace.getInstance();
    	RobotMap.init();
       
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        gearHandler = new GearHandler();
        robotClimber = new RopeClimber();
        fuelIntake = new FuelIntake();
        Shooter = new Shooter();
        newGH  = new NewGH();
        newGHRoller = new NewGHRoller();
        leds = new LEDS();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        visionProcessing = new VisionProcessing();
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
        
        autoChooser = new SendableChooser<CommandGroup>();
        sideChooser = new SendableChooser<SideOfField>();
        
        autoChooser.addObject("Move Forward", new GroupMovePastBaseline());
        autoChooser.addObject("Left Lift With NO Vision", new GroupLiftLeftVisionCorrect());
        autoChooser.addObject("Right Lift With NO Vision", new GroupLiftRightNoVision());
        autoChooser.addObject("Middle Lift With NO Vision", new GroupMiddleLift());
        autoChooser.addObject("Shoot From Start RED, Cross Baseline", new GroupShootFromStartCrossBaseLineRed());
        autoChooser.addObject("Shoot From Start BLUE, Cross Baseline", new GroupShootFromStartCrossBaseLineBlue());
        autoChooser.addObject("Close Hopper, Fire" , new GroupPosCHopperFire());
        autoChooser.addObject("Left Lift WITH VISION", new GroupLiftLeftVisionCorrect());
        autoChooser.addObject("Middle lift WITH VISION", new GroupMiddleLiftVision());
        autoChooser.addObject("Right Lift WITH VISION", new GroupLiftVisionRight());
        autoChooser.addObject("MiddleLift NEWGH", new MiddleLiftNewGH());
        autoChooser.addObject("Left Lift NEWGH", new LeftLiftNewGH());
        autoChooser.addObject("Right Lift NEWGH", new RightLiftNewGH());
        autoChooser.addDefault("Do Nothing", new GroupDoNothing());
        
        
        sideChooser.addDefault("Red", m_side = SideOfField.Red);
        sideChooser.addObject("Blue", m_side = SideOfField.Blue);
        
        SmartDashboard.putData("Auto Mode Chooser", autoChooser);

        SmartDashboard.putData("Side Chooser", sideChooser);
        
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        Robot.Shooter.getShooterMotor().changeControlMode(TalonControlMode.Speed);
        Robot.Shooter.getShooterMotor().setFeedbackDevice(FeedbackDevice.QuadEncoder);
        Robot.Shooter.getShooterMotor().reverseSensor(false);
        Robot.Shooter.getShooterMotor().configEncoderCodesPerRev(48);
        Robot.Shooter.getShooterMotor().setProfile(0);
        Robot.Shooter.getShooterMotor().setP(5.2);
        Robot.Shooter.getShooterMotor().setI(0);
        Robot.Shooter.getShooterMotor().setD(0);
        Robot.Shooter.getShooterMotor().setF(3);
        Robot.Shooter.getShooterMotor().configPeakOutputVoltage(11.2, -11.2);
        
        
        
        
        leds.setPurple();
        }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	
    	Trace.getInstance().flushTraceFiles();
    	Robot.visionProcessing.putEnableStatus(RobotEnableStatus.DISABLED);
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
    	RobotMap.shooterMotor.setProfile(0);
    	visionProcessing.resetTimestampOnNetworkTables();
    }

    public void autonomousInit() {
    	Robot.visionProcessing.putEnableStatus(RobotEnableStatus.ENABLED);
    	setInitialAngle();

    	// schedule the autonomous command (example)
    	autonomousCommand = (Command) autoChooser.getSelected();
    	if (autonomousCommand != null) autonomousCommand.start();
    	System.out.println("Connected to FMS = " + DriverStation.getInstance().isFMSAttached());
    	if(DriverStation.getInstance().isFMSAttached()) {
    		Trace.getInstance().matchStarted();
    	}
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
    	Robot.visionProcessing.putEnableStatus(m_robotenabled.ENABLED);
        if (autonomousCommand != null) autonomousCommand.cancel();
        setInitialAngle();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	m_counter++;
    	if(m_counter %500 == 0){
    		Trace.getInstance().flushTraceFiles();
    		
    	}
    	
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    
    public static SideOfField getSide(){
    	return m_side;
    }
}
