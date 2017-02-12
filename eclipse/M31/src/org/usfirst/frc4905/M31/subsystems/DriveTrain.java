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
import org.usfirst.frc4905.M31.OI;
import org.usfirst.frc4905.M31.Robot;
import Utilities.*;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveTrain extends Subsystem {	
	


	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final CANTalon frontLeft = RobotMap.driveTrainFrontLeft;
	private final CANTalon backRight = RobotMap.driveTrainBackRight;
	private final CANTalon frontRight = RobotMap.driveTrainFrontRight;
	private final CANTalon backLeft = RobotMap.driveTrainBackLeft;
	private final RobotDrive robotDrive = RobotMap.driveTrainRobotDrive;
	private final SpeedController climber = RobotMap.driveTrainClimber;
	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	private final CANTalon m_motors[] = {frontLeft, backRight, frontRight, backLeft};
	
	private int m_iterationsSinceRotationCommanded = 0;
	private double m_desiredHeading;
	
	private double m_RPMConversion = 883;
	
	private final boolean kNoisyDebug = true;
	StringBuilder m_sb = new StringBuilder();
	
	
	
	public DriveTrain() {
		double kp = 0.15;
		double ki = 0.00015;
		double kd = 1.5;
		// 700/60/10*1 = 1.167  1023/1.167 -- Page 80 in CTR Documentation
		double kf = 0.214;
		int izone = 0;
		double ramprate = 36;
		int i;
		for (i = 0; i < m_motors.length; i++) {
			m_motors[i].reverseSensor(false);
			m_motors[i].setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
			m_motors[i].setPosition(0);
			m_motors[i].configNominalOutputVoltage(0, 0);
			m_motors[i].configPeakOutputVoltage(12.0, -12.0);
			m_motors[i].enableBrakeMode(true);
			m_motors[i].setVoltageRampRate(48);
			m_motors[i].setPID(kp, ki, kd, kf, izone, ramprate, 0);
			//m_motors[i].setCloseLoopRampRate(0.1);
			System.out.println("Current Ramp Rate is:" + m_motors[i].getCloseLoopRampRate());
			System.out.print(" Target Ramp Rate is: " + ramprate + " \n");
			
			m_motors[i].changeControlMode(TalonControlMode.Speed);
			m_motors[i].set(0);

		}
		robotDrive.setMaxOutput(m_RPMConversion);
		GyroPIDoutput gyroPIDoutPut = new GyroPIDoutput(0.08);
		RobotMap.getNavxGyro().initializeGyroPID(gyroPIDoutPut);
		UltrasonicPIDOutput ultraPIDOutput= new UltrasonicPIDOutput();
		RobotMap.getUltrasonicSubsystem().intializeUltrasonicPID(ultraPIDOutput);

		initializeYEncoderPID(500);
		initializeXEncoderPID(500);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		setDefaultCommand(new TeleopDrive());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	int m_loops = 0;
	public void mecanumDrive(double xIn, double yIn, double rotation){
		//Getting Gyro Angle Always, This Causes SmartDashBoard to Be updated
		//With Current Angle
		double gyroReading = RobotMap.getNavxGyro().getRobotAngle();
		double motorOutput = frontRight.getOutputVoltage() / frontRight.getBusVoltage();
		if(kNoisyDebug) {
			m_sb.append("\tout:");
			m_sb.append(motorOutput);
			m_sb.append("\tspd:");
			m_sb.append(frontRight.getSpeed() );
			m_sb.append("\terr:");
			m_sb.append(frontRight.getClosedLoopError() * 600 / 4096);
			m_sb.append("\ttrg:");
			m_sb.append(-yIn*m_RPMConversion);
			if(++m_loops >= 10) {
				m_loops = 0;
				System.out.println(m_sb.toString());
			}
			m_sb.setLength(0);
		}
		// Greatest Regards to 1519
		// update count of iterations since rotation last commanded
		if ((-0.01 < rotation) && (rotation < 0.01)) {
			// rotation is practically zero, so just set it to zero and
			// increment iterations
			rotation = 0.0;
			m_iterationsSinceRotationCommanded++;
		} else {
			// rotation is being commanded, so clear iteration counter
			m_iterationsSinceRotationCommanded = 0;
		}
		// preserve heading when recently stopped commanding rotations
		if (m_iterationsSinceRotationCommanded == 20) {
			m_desiredHeading = gyroReading;
		} else if (m_iterationsSinceRotationCommanded > 20) {
			rotation = (m_desiredHeading - gyroReading) / 40.0;
		}
		SmartDashboard.putNumber("Front Left Speed", Robot.driveTrain.getM1Speed());
    	SmartDashboard.putNumber("Back Right Speed", -Robot.driveTrain.getM2Speed());
    	SmartDashboard.putNumber("Front Right Speed", -Robot.driveTrain.getM3Speed());
    	SmartDashboard.putNumber("Back Left Speed", Robot.driveTrain.getM4Speed());
    	SmartDashboard.putNumber("Y Commanded Speed",yIn);
    	SmartDashboard.putNumber("X Commanded Speed", xIn);
		robotDrive.mecanumDrive_Cartesian(xIn, yIn, rotation, 0);
	}

		
	public void stop(){
		frontLeft.set(0);
		frontRight.set(0);
		backLeft.set(0);
		backRight.set(0);
	}

	public void getEncPos(){

		System.out.println("Back Left Pos:" + backLeft.getPosition());
		System.out.println("Back Right Pos:" + backRight.getPosition());
		System.out.println("Front Right Pos:" + frontRight.getPosition());
		System.out.println("Front Left Pos:" + frontLeft.getPosition());
	}

	public void resetEncPos(){
		backLeft.setPosition(0);
		backRight.setPosition(0);
		frontRight.setPosition(0);
		frontLeft.setPosition(0);


	}
	public double getEncoderDistance(){
		double sum = 0;
		sum += Math.abs(backLeft.getPosition());
		sum += Math.abs(backRight.getPosition());
		sum += Math.abs(frontLeft.getPosition());
		sum += Math.abs(frontRight.getPosition());
		return sum;

	}
	
	public void displayEncoderPosition() {
		SmartDashboard.putNumber("Encoder Value", getEncoderDistance());
	}

	private double raiseOutputAboveMin(double output, 
			double minimumOutput) {
		if((Math.abs(output) < minimumOutput) && (output != 0)) {
			double minVal = minimumOutput; 
			if(output < 0) {
				output = -minVal;
			} else {
				output = minVal;				
			}
		}
		return output;
	}
	
	// Encoder PID code

	// Encoder PID controller
	private PIDController m_moveToTheYEncoderPID;
	// Encoder PID controller variables
	private static final double yEncoderKp = 0.25;
	private static final double yEncoderKi = 0.000;
	private static final double yEncoderKd = 0.000;
	private static final double yEncoderKf = 0.000;
	private static final double yEncoderTolerance = 0.1;
	private static final double yEncoderOutputMax = 0.3;

	public PIDController getYPIDcontroller() {
		return m_moveToTheYEncoderPID;
	}

	
	private class MovingInTheYEncoderPIDin implements PIDSource {
		private double getEncoderPosition() {
			//Used when moving in y direction
			return (frontLeft.getPosition() + backLeft.getPosition()
			- frontRight.getPosition() - backRight.getPosition()) / 4;
		}
		
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {


		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			if (kNoisyDebug) {
				System.out.println("Encoder Position = " + getEncoderPosition());
			}
			return getEncoderPosition();
		}

	}
	public double last_y_commanded_speed = 0;
	private class MovingInTheYEncoderPIDout implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			//output = raiseOutputAboveMin(output,0.03);
			last_y_commanded_speed = output * m_RPMConversion;
			mecanumDrive(0, -output, 0);
			if (kNoisyDebug) {
					System.out.println("Encoder Output = " + output 
					+ " Average Error = " + m_moveToTheYEncoderPID.getAvgError());
			}
		}

	}


	public void initializeYEncoderPID(double distanceToMove) {
		resetEncPos();
		MovingInTheYEncoderPIDin encoderPIDin = new MovingInTheYEncoderPIDin();
		MovingInTheYEncoderPIDout encoderPIDout = new MovingInTheYEncoderPIDout();
		m_moveToTheYEncoderPID = new PIDController(yEncoderKp, yEncoderKi, 
				yEncoderKd, yEncoderKf, encoderPIDin, encoderPIDout);
		m_moveToTheYEncoderPID.setOutputRange(-yEncoderOutputMax, yEncoderOutputMax);
		m_moveToTheYEncoderPID.setAbsoluteTolerance(yEncoderTolerance);
		LiveWindow.addActuator("DriveTrain", "YEncoderPID", m_moveToTheYEncoderPID);
	}

	public void moveToYEncoderRevolutions(double revolutionsToMove) {
		resetEncPos();
		m_moveToTheYEncoderPID.setSetpoint(revolutionsToMove);
		m_moveToTheYEncoderPID.enable();
	}

	public boolean isDoneMovingToYEncoderRevolutions() {
		if (kNoisyDebug) {
			System.out.println("encoder distance = " + getEncoderDistance());
		}
		return m_moveToTheYEncoderPID.onTarget();
	}

	public void stopMovingToYEncoderRevolutions() {
		m_moveToTheYEncoderPID.disable();

	}
	
	//X stuff
	private PIDController m_moveToTheXEncoderPID;
	// Encoder PID controller variables
	private static final double xEncoderKp = 0.25;
	private static final double xEncoderKi = 0.000;
	private static final double xEncoderKd = 0.000;
	private static final double xEncoderKf = 0.000;
	private static final double xEncoderTolerance = 0.1;
	private static final double xEncoderOutputMax = 0.3;

	public PIDController getXPIDcontroller() {
		return m_moveToTheXEncoderPID;
	}
	
	private class MovingInTheXEncoderPIDin implements PIDSource {
		private double getEncoderPosition() {
			//Used when moving in x direction
			return (frontRight.getPosition() - backRight.getPosition()
					+ frontLeft.getPosition() - backLeft.getPosition()) / 4;
		}
		
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {


		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			if (kNoisyDebug){
				System.out.println("Encoder Position = " + getEncoderPosition());
			}
			return getEncoderPosition();
		}

	}

	public double last_x_commanded_speed;
	
	private class MovingInTheXEncoderPIDout implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			//output = raiseOutputAboveMin(output,0.03);
			last_x_commanded_speed = output * m_RPMConversion;
			mecanumDrive(output, 0, 0);
			if (kNoisyDebug) {
				System.out.println("Encoder Output = " + output 
					+ " Average Error = " + m_moveToTheXEncoderPID.getAvgError());
			}
		}

	}


	public void initializeXEncoderPID(double distanceToMove) {
		resetEncPos();
		MovingInTheXEncoderPIDin encoderPIDin = new MovingInTheXEncoderPIDin();
		MovingInTheXEncoderPIDout encoderPIDout = new MovingInTheXEncoderPIDout();
		m_moveToTheXEncoderPID = new PIDController(xEncoderKp, xEncoderKi, 
				xEncoderKd, xEncoderKf, encoderPIDin, encoderPIDout);
		m_moveToTheXEncoderPID.setOutputRange(-xEncoderOutputMax, xEncoderOutputMax);
		m_moveToTheXEncoderPID.setAbsoluteTolerance(xEncoderTolerance);
		LiveWindow.addActuator("DriveTrain", "XEncoderPID", m_moveToTheXEncoderPID);
	}

	public void moveToXEncoderRevolutions(double revolutionsToMove) {
		resetEncPos();
		m_moveToTheXEncoderPID.setSetpoint(revolutionsToMove);
		m_moveToTheXEncoderPID.enable();
	}

	public boolean isDoneMovingToXEncoderRevolutions() {
		if (kNoisyDebug) {
			System.out.println("encoder distance = " + getEncoderDistance());
		}
		return m_moveToTheXEncoderPID.onTarget();
	}

	public void stopMovingToXEncoderRevolutions() {
		m_moveToTheXEncoderPID.disable();
		
	}

	
	// Gyro PID code 
	private class GyroPIDoutput implements PIDOutput {
	
		public GyroPIDoutput(double minimumOutput) {
			m_minimumOutput = minimumOutput;
		}
		
		private double m_minimumOutput = 0;

		@Override
		public void pidWrite(double output) {
			output = raiseOutputAboveMin(output,m_minimumOutput);
			robotDrive.mecanumDrive_Cartesian(0, 0, output, 0);
			m_iterationsSinceRotationCommanded = 0;
		}

	}
	
	public void initializeGyroPID(double deltaAngle) {
		GyroPIDoutput gyroPIDout = new GyroPIDoutput(0.08);
		RobotMap.getNavxGyro().initializeGyroPID(gyroPIDout);
		RobotMap.getNavxGyro().turnWithGyroPID(deltaAngle);
	}
	
	public boolean doneTurningWithGyro() {
		// TODO Auto-generated method stub
		return RobotMap.getNavxGyro().isDoneGyroPID();
	}

	public void stopGyroPID() {
		RobotMap.getNavxGyro().stopGyroPID();
	} 

	public double getRobotAngle() {
		return RobotMap.getNavxGyro().getRobotAngle();
	}
	
	public double initializeTurnToCompass(double angle) {
		double initialAngle = getRobotAngle();
		double angleMod = initialAngle % 360;
		System.out.println("AngleMod = " + angleMod + 
				" Initial Angle = " + initialAngle);
		if(angleMod < 0) {
			//Correcting Negative Modulus
			angleMod = angleMod + 360; 
		}
		double deltaAngle = angle - angleMod;
		if(Math.abs(deltaAngle) > 180) { 
			// Turn to the minimal angle
			boolean neg = (deltaAngle < 0);
			 deltaAngle = Math.abs(deltaAngle) - 360;
			 if(neg) {
				 deltaAngle = -deltaAngle;
			 }
		}
		System.out.println("Delta = " + deltaAngle);
		return deltaAngle;
	}
	
	

	// End of Gyro PID Code

	public void moveInAuto(double sideways, double forward){

		robotDrive.mecanumDrive_Cartesian(sideways, forward, 0, 0);

	}
	public boolean isDoneAuto(double target, double currentPos){
		if(currentPos <  target){
			return false;
		}else{
			return true;
		}
	}

	// UltrasonnicPID code
	private class UltrasonicPIDOutput implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			SmartDashboard.putNumber("Output", output);
			SmartDashboard.putNumber("Distance",
					RobotMap.getUltrasonicSubsystem().getUltrasonicDistance());
			robotDrive.mecanumDrive_Cartesian(output, 0, 0, 0);

		}
	}

	public void intializeUltrasonicPID(double distanceToDriveTo) {
		UltrasonicPIDOutput ultraPIDOutput= new UltrasonicPIDOutput();
		RobotMap.getUltrasonicSubsystem().intializeUltrasonicPID(ultraPIDOutput);
		RobotMap.getUltrasonicSubsystem().moveWithUltrasonicPID(distanceToDriveTo);

	}

	public boolean doneMovingWithUltrasoncPID() {
		return RobotMap.getUltrasonicSubsystem().doneUltrasonicPID();
	}

	public void stopUltrasonicPID() {
		RobotMap.getUltrasonicSubsystem().stopUltrasonicPID();

	}
	public double getM1Speed(){
		return frontLeft.getSpeed();
				
	}
	public double getM2Speed(){
		return backRight.getSpeed();
	}
	public double getM3Speed(){
		return frontRight.getSpeed();
	}
	public double getM4Speed(){
		return backLeft.getSpeed();
	}
	
	
}

