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

import org.usfirst.frc4905.M31.subsystems.UltrasonicSubsystem;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.CANTalon;

import NavXGyro.NavxGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
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

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private static NavxGyro m_navxGyro;
    private static UltrasonicSubsystem m_ultrasonicSubsystem;
   
    
    
    
    public static VictorSP intakeMotor;
    
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
        driveTrainClimber = new VictorSP(4);
        LiveWindow.addActuator("DriveTrain", "Climber", (VictorSP) driveTrainClimber);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        m_navxGyro = new NavxGyro();
        LiveWindow.addSensor("DriveTrain", "NavxGyro", m_navxGyro.getAHRS());
        m_navxGyro.setInitialAngleReading();
        
        m_ultrasonicSubsystem = new UltrasonicSubsystem();
        LiveWindow.addSensor("UltraSonic", "Ultrasonic1", 
        		m_ultrasonicSubsystem.getUltrasonic());
        
       intakeMotor = new VictorSP(2);
       LiveWindow.addActuator("IntakeMotor", "FuelIntake", (VictorSP) intakeMotor);
        
        
    }
    
    public static NavxGyro getNavxGyro() {
    	return m_navxGyro;
    }
    
    public static UltrasonicSubsystem getUltrasonicSubsystem(){
    	return m_ultrasonicSubsystem;
    }
}
