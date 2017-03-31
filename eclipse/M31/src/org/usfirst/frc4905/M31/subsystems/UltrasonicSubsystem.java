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

import Utilities.SuperUltrasonic;
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
public class UltrasonicSubsystem extends Subsystem {

	private PIDController m_ultrasonicPID;
	double m_P=.1;
	double m_I=.00000;
	double m_D=.0;
	double m_maxSpeed=0.25;
	double m_f=0;
	double m_tolerance=1.0;
	private String m_traceUltrasonicFileName = "LifterSideUltrasonicPID";
	private double m_distanceOld = 0;
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private SuperUltrasonic m_ultrasonic;

	public UltrasonicSubsystem(int ping, int echo) {
		m_ultrasonic = new SuperUltrasonic(ping, echo);
		m_ultrasonic.setEnabled(true);
		m_ultrasonic.setTimeDelayPing(0.007);
		//m_ultrasonic.setAutomaticMode(true);
		System.out.println("Ultrasonic Constructed");
		Trace traceInstance = Trace.getInstance();
		Vector<String> entry = new Vector<String>();
		entry.add("PIDOutput");
		entry.add("Avg Error");
		entry.add("Ultra Distance");
		entry.add("Ultra distance raw");
		traceInstance.addTrace(m_traceUltrasonicFileName, entry);
	}
	
	public UltrasonicSubsystem(int ping, int echo, double p, double i, double d,  double f, double maxSpeed, double tolerance) {
		m_ultrasonic = new SuperUltrasonic(ping, echo);
		m_ultrasonic.setEnabled(true);
		m_P = p;
		m_I = i;
		m_D = d;
		m_maxSpeed = maxSpeed;
		m_tolerance = tolerance;
		//m_ultrasonic.setAutomaticMode(true);
		System.out.println("Ultrasonic Constructed");
		LiveWindow.addActuator("UltraPID", "ultrasonicPID", m_ultrasonicPID);
		Trace traceInstance = Trace.getInstance();
		Vector<String> entry = new Vector<String>();
		entry.add("PIDOutput");
		entry.add("Avg Error");
		entry.add("Ultra Distance");
		traceInstance.addTrace(m_traceUltrasonicFileName, entry);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public SuperUltrasonic getUltrasonic() {
		return(m_ultrasonic);
	}

	public double getUltrasonicDistance(){
		double currentDistance = m_ultrasonic.getRangeInches();
		if((currentDistance - m_distanceOld) > 100) {
			return(m_distanceOld);
		}
		m_distanceOld = currentDistance;
		return currentDistance;
	}
	
	public void setAutoMode(){
		m_ultrasonic.setAutomaticMode(true);
	}

	public void intializeUltrasonicPID(PIDOutput ultrasonicPIDout) {
		UltrasonicPIDin pdIn=new UltrasonicPIDin();
		m_ultrasonicPID=new PIDController(m_P, m_I, m_D, m_f,
				pdIn, ultrasonicPIDout);
		m_ultrasonicPID.setAbsoluteTolerance(m_tolerance);
		m_ultrasonicPID.setOutputRange(-m_maxSpeed, m_maxSpeed);
		LiveWindow.addActuator("UltraPID", "ultrasonicPID", m_ultrasonicPID);
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
		Trace traceInstance = Trace.getInstance();
		Vector<Double> entry = new Vector<Double>();
		entry.add(m_ultrasonicPID.get() * 100);
		entry.add(m_ultrasonicPID.getAvgError());
		entry.add(getUltrasonicDistance());
		entry.add(m_ultrasonic.getRangeInches());
		traceInstance.addEntry(m_traceUltrasonicFileName, entry);
		return m_ultrasonicPID.onTarget();
	}

	public void stopUltrasonicPID() {
		m_ultrasonicPID.disable();

	}
}

