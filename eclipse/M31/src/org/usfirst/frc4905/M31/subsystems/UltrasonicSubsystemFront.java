// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4905.M31.subsystems;

import java.util.Vector;

import org.usfirst.frc4905.M31.RobotMap;
import org.usfirst.frc4905.M31.commands.*;

import Utilities.Trace;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Ultrasonic;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 *
 */
public class UltrasonicSubsystemFront extends Subsystem {

	private PIDController m_ultrasonicPID;
	private double m_P=.03;
	private double m_I=.00000;
	private double m_D=.0;
	private double m_maxSpeed=0.2;
	private double m_f=0;
	private double m_tolerance=.5;
	private String m_traceFrontUltrasonicFileName = "FrontUltrasonicPID";

	private double m_distanceOld = 0;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private Ultrasonic m_ultrasonic;

	public UltrasonicSubsystemFront() {
		m_ultrasonic = new Ultrasonic(4, 5);
		m_ultrasonic.setEnabled(true);
		m_ultrasonic.setAutomaticMode(true);
		System.out.println("Ultrasonic Constructed");
		Trace.getInstance().addTrace(m_traceFrontUltrasonicFileName, 
				"PIDOutput",
				"Avg Error",
				"Ultra Distance",
				"Ultra Distance Raw");
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public Ultrasonic getUltrasonic() {
		return(m_ultrasonic);
	}

	public double getUltrasonicDistance(){
		double currentDistance = m_ultrasonic.getRangeInches();
		if((currentDistance - m_distanceOld) > 200) {
			return(m_distanceOld);
		}
		m_distanceOld = currentDistance;
		return currentDistance;
	}

	public void intializeUltrasonicPID(PIDOutput ultrasonicPIDout) {
		UltrasonicPIDin pdIn=new UltrasonicPIDin();
		m_ultrasonicPID=new PIDController(m_P, m_I, m_D, m_f,
				pdIn, ultrasonicPIDout);
		m_ultrasonicPID.setAbsoluteTolerance(m_tolerance);
		m_ultrasonicPID.setOutputRange(-m_maxSpeed, m_maxSpeed);
		LiveWindow.addActuator("UltraPID", "ultrasonicPIDFront", m_ultrasonicPID);
	}

	private class UltrasonicPIDin implements PIDSource {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {	
		}

		@Override
		public PIDSourceType getPIDSourceType() {

			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			return getUltrasonicDistance();
		}

	}
	public void moveWithUltrasonicPID(double distanceToGoTo) {
		m_ultrasonicPID.setSetpoint(distanceToGoTo);
		m_ultrasonicPID.enable();

	}

	public boolean doneUltrasonicPID() {
		Trace.getInstance().addEntry(m_traceFrontUltrasonicFileName, 
				m_ultrasonicPID.get() * 100,
				m_ultrasonicPID.getAvgError(),
				getUltrasonicDistance(),
				m_ultrasonic.getRangeInches());
		return m_ultrasonicPID.onTarget();
	}

	public void stopUltrasonicPID() {
		m_ultrasonicPID.disable();

	}
}

