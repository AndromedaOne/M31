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

import org.usfirst.frc4905.M31.subsystems.UltrasonicSubsystemFront;
import org.usfirst.frc4905.M31.subsystems.UltrasonicSubsystem;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.CANTalon;

import NavXGyro.NavxGyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static CANTalon driveTrainFrontLeft;
	public static CANTalon driveTrainBackRight;
	public static CANTalon driveTrainFrontRight;
	public static CANTalon driveTrainBackLeft;
	public static RobotDrive driveTrainRobotDrive;
	public static SpeedController driveTrainClimber;
	public static VictorSP gearHandlerRight;
	public static VictorSP gearHandlerLeft;
	
	//new gear handler things below!
	public static Spark newGHclaw;
	public static Talon newGHraiselower;
	public static DigitalInput newGHopen;
	public static DigitalInput newGHclose;
	public static DigitalInput newGHup;
	public static DigitalInput newGHdown;
	public static DigitalInput newGHoptical;
	public static Spark newGHRoller;
	
	
	public static DigitalInput gearHandlerRightSwitch;
	public static DigitalInput gearHandlerSensePoleSwitch;
   
	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private static NavxGyro m_navxGyro;
	private static UltrasonicSubsystem m_ultrasonicSubsystem;
	private static UltrasonicSubsystemFront m_ultrasonicFront;



	public static VictorSP intakeMotor;
	public static VictorSP ropeClimbMotor;
    public static VictorSP feederMotor;
    public static CANTalon shooterMotor;
    public static DigitalInput shooterSafetySwitch;
	public static void init() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		driveTrainFrontLeft = new CANTalon(1);
		LiveWindow.addActuator("DriveTrain", "FrontLeft", driveTrainFrontLeft);

		driveTrainBackRight = new CANTalon(4);
		LiveWindow.addActuator("DriveTrain", "BackRight", driveTrainBackRight);

		driveTrainFrontRight = new CANTalon(2);
		LiveWindow.addActuator("DriveTrain", "FrontRight", driveTrainFrontRight);

		driveTrainBackLeft = new CANTalon(3);
		LiveWindow.addActuator("DriveTrain", "BackLeft", driveTrainBackLeft);

		driveTrainRobotDrive = new RobotDrive(driveTrainFrontLeft, driveTrainBackLeft,
				driveTrainFrontRight, driveTrainBackRight);
		driveTrainRobotDrive.setSafetyEnabled(true);
		driveTrainRobotDrive.setExpiration(0.1);
		driveTrainRobotDrive.setSensitivity(0.5);
		driveTrainRobotDrive.setMaxOutput(1.0);

		driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

		m_navxGyro = new NavxGyro();
		LiveWindow.addSensor("DriveTrain", "NavxGyro", m_navxGyro.getAHRS());
		m_navxGyro.setInitialAngleReading();

		m_ultrasonicSubsystem = new UltrasonicSubsystem(6,7);
		LiveWindow.addSensor("UltraSonic", "Ultrasonic1", 
				m_ultrasonicSubsystem.getUltrasonic());
		
		m_ultrasonicFront = new UltrasonicSubsystemFront();
		LiveWindow.addSensor("UltraSonic", "Front Ultrasonic", m_ultrasonicFront.getUltrasonic());
		
		m_ultrasonicSubsystem.setAutoMode();

		//Gear Handler
		gearHandlerRight = new VictorSP(0);
		LiveWindow.addActuator("gearHandler", "gearHandlerRight", (VictorSP) gearHandlerRight);
		gearHandlerLeft = new VictorSP(1);
		LiveWindow.addActuator("gearHandler", "gearHandlerLeft", (VictorSP) gearHandlerLeft);
	
		
		gearHandlerSensePoleSwitch = new DigitalInput(8);
		LiveWindow.addSensor("gearHandler", "gearHandler sense pole switch", gearHandlerSensePoleSwitch);
		
		//End gear handler
		//Fuel Intake
		intakeMotor = new VictorSP(2);
		LiveWindow.addActuator("IntakeMotor", "FuelIntake", (VictorSP) intakeMotor);

		//Rope Climber
		ropeClimbMotor = new VictorSP(3);
		LiveWindow.addActuator("RopeClimber", "Climber", ropeClimbMotor);

       feederMotor = new VictorSP(9);
       LiveWindow.addActuator("Shooter", "Feeder Motor", feederMotor);
       
       shooterMotor = new CANTalon(6);
       LiveWindow.addActuator("Shooter", "Shooter Motor", shooterMotor);

       shooterSafetySwitch = new DigitalInput(9);
       LiveWindow.addSensor("Shooter", "Safety Switch", shooterSafetySwitch);
       
       //gear handler (new!)
       		//DANA THIS IS YOUR CONSCIENCE MAKE SURE THE OPTICAL SENSOR IS A DIO
       		//ALSO DOUBLE CHECK TO MAKE SURE PWM 4 AND 5 ARE OPEN FOR THE VSPs
       		//JILL WILL KILL YOU!!!!!!!!!!!!!!! THATS BAD!!!!!!!!!
       newGHclaw = new Spark(4);
       LiveWindow.addActuator("NewGH", "Gear Handler Claw Motor", newGHclaw);
       newGHraiselower = new Talon(5);
       LiveWindow.addActuator("NewGH", "Gear Handler Lower and Raise Motor", newGHraiselower);
       newGHopen = new DigitalInput (0);
       LiveWindow.addSensor("NewGH", "Gear Handler Open Limit Switch", newGHopen);
       newGHclose = new DigitalInput (1);
       LiveWindow.addSensor("NewGH", "Gear Handler Close Limit Switch", newGHclose);
       newGHup = new DigitalInput (2);
       LiveWindow.addSensor("NewGH", "Gear Handler Raised Limit Switch", newGHup);
       newGHdown = new DigitalInput (3);
       LiveWindow.addSensor("NewGH",  "Gear Handler Lowered Limit Switch", newGHdown);
       newGHoptical = new DigitalInput (10);
       LiveWindow.addSensor("NewGH",  "Gear Handler Optical Sensor", newGHoptical);
       newGHRoller = new Spark(6);
       LiveWindow.addActuator("NewGH", "NewGH Roller", newGHRoller);
       //end gear handler (new!)
	}

	public static NavxGyro getNavxGyro() {
		return m_navxGyro;
	}

	public static UltrasonicSubsystem getUltrasonicSubsystem(){
		return m_ultrasonicSubsystem;
	}
	public static UltrasonicSubsystemFront getUltrasonicFront(){
		return m_ultrasonicFront;
	}
	
}
