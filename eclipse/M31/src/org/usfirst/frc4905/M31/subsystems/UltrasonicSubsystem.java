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

import org.usfirst.frc4905.M31.RobotMap;
import org.usfirst.frc4905.M31.commands.*;

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

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private Ultrasonic m_ultrasonic;

	public UltrasonicSubsystem(int ping, int echo) {
		m_ultrasonic = new Ultrasonic(ping, echo);
		m_ultrasonic.setEnabled(true);
		//m_ultrasonic.setAutomaticMode(true);
		System.out.println("Ultrasonic Constructed");
	}
	
	public UltrasonicSubsystem(int ping, int echo, double p, double i, double d,  double f, double maxSpeed, double tolerance) {
		m_ultrasonic = new Ultrasonic(ping, echo);
		m_ultrasonic.setEnabled(true);
		m_P = p;
		m_I = i;
		m_D = d;
		m_maxSpeed = maxSpeed;
		m_tolerance = tolerance;
		//m_ultrasonic.setAutomaticMode(true);
		System.out.println("Ultrasonic Constructed");
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
		return m_ultrasonic.getRangeInches();
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
		return m_ultrasonicPID.onTarget();
	}

	public void stopUltrasonicPID() {
		m_ultrasonicPID.disable();

	}
}

