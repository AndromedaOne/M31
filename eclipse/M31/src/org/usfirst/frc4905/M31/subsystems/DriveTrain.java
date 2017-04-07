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

import java.util.Vector;

import org.usfirst.frc4905.M31.OI;
import org.usfirst.frc4905.M31.Robot;
import Utilities.*;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveTrain extends Subsystem {	
	
	public void useNormalProfile() {
		m_motorsFrontLeft.setProfile(0);
		m_motorsFrontRight.setProfile(0);
		m_motorsBackLeft.setProfile(0);
		m_motorsBackRight.setProfile(0);
	}
	
	public void useStrafeProfile() {
		m_motorsFrontLeft.setProfile(1);
		m_motorsFrontRight.setProfile(1);
		m_motorsBackLeft.setProfile(1);
		m_motorsBackRight.setProfile(1);
	}
	
	private void setNormalPIDParameters() {
		//                        P     I  D  F      Izone RampRate Profile
		m_motorsFrontLeft.setPID( 0.15, 0, 0, 0.214, 0,    0,       0);
		m_motorsFrontRight.setPID(0.15, 0, 0, 0.214, 0,    0,       0);
		m_motorsBackLeft.setPID(  0.15, 0, 0, 0.214, 0,    0,       0);
		m_motorsBackRight.setPID( 0.15, 0, 0, 0.214, 0,    0,       0);
		// Page 88 in CTR Documentation for f 
		
	}
	
	private void setStrafePIDParameters() {
		//                        P               I       D  F                          Izone           RampRate Profile
		m_motorsFrontLeft.setPID( 102.3/27.5/10,  0.004,  0, 1023.0*600.0/4096.0/550.0, 50*4096/600,    0,       1);
		m_motorsFrontRight.setPID(102.3/75.0/3.5, 0.0046, 0, 1023.0*600.0/4096.0/620.0, 50*4096/600,    0,       1);
		m_motorsBackLeft.setPID(  102.3/70.0/3.5, 0.0043, 0, 1023.0*600.0/4096.0/590.0, 50*4096/600,    0,       1);
		m_motorsBackRight.setPID( 102.3/32.5/12,  0.003,  0, 1023.0*600.0/4096.0/570.0, 50*4096/600,    0,       1);
		// 700/60/10*4096 = 4778.67  1023/4778.67 
		// Page 88 in CTR Documentation for f 
		
	}
	
	private void setCommonMotorParameters(CANTalon motorController) {
		motorController.reverseSensor(false);
		motorController.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		motorController.setPosition(0);
		motorController.configNominalOutputVoltage(0, 0);
		motorController.configPeakOutputVoltage(12.0, -12.0);
		motorController.enableBrakeMode(true);
		motorController.setVoltageRampRate(48);
		motorController.changeControlMode(TalonControlMode.Speed);
		motorController.set(0);
	}


	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final CANTalon frontLeft = RobotMap.driveTrainFrontLeft;
	private final CANTalon backRight = RobotMap.driveTrainBackRight;
	private final CANTalon frontRight = RobotMap.driveTrainFrontRight;
	private final CANTalon backLeft = RobotMap.driveTrainBackLeft;
	private final RobotDrive robotDrive = RobotMap.driveTrainRobotDrive;
	private final SpeedController climber = RobotMap.driveTrainClimber;
	private final Encoder omniWheelEncoder = RobotMap.omniWheelEncoder;
	private double m_moveYEncoderP;
	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	private final CANTalon m_motorsFrontLeft = frontLeft;
	private final CANTalon m_motorsBackRight = backRight;
	private final CANTalon m_motorsFrontRight = frontRight;
	private final CANTalon m_motorsBackLeft = backLeft;

	private int m_iterationsSinceRotationCommanded = 0;
	private int m_iterationsSinceRotationAndYMoveCommanded = 0;
	private double m_desiredHeading;
	private double m_desiredOmniWheelEncoderTick;

	private double m_RPMConversion = 883;

	private final boolean kNoisyDebug = false;
	StringBuilder m_sb = new StringBuilder();
	boolean gyroEnabled = true;
    private String m_frontLeftTrace = "frontLeftMotor";
	private String m_frontRightTrace = "frontRightMotor";
	private String m_backLeftTrace = "backLeftMotor";
	private String m_backRightTrace = "backRightMotor";
	private String m_traceFileName = "mecanumDrive";
	private String m_traceOmniFileName = "OmniWheel";
	
	private double m_omniWheelPIDOutput = 0.0;
	// Preferences Code
	Preferences prefs = Preferences.getInstance();

	private void writeTraceData(String filename, CANTalon talon, boolean invertSetpoint) {
		Trace traceInstance = Trace.getInstance();
		traceInstance.addTrace(filename,
		new TracePair("Speed",talon.getSpeed()),
		new TracePair("Motor Output", talon.getOutputVoltage() / talon.getBusVoltage() * 100),
		new TracePair("Closed Loop Error", talon.getClosedLoopError() * 600.0 / 4096),
		new TracePair("Setpoint", invertSetpoint ? -talon.getSetpoint() : talon.getSetpoint()),
		new TracePair("I Value", (double) talon.GetIaccum()/300));
	}

	public DriveTrain() {

		setNormalPIDParameters();
		setStrafePIDParameters();
		
		//Front Left Motor PID
		setCommonMotorParameters(m_motorsFrontLeft);

		//Front Right Motor PID
		setCommonMotorParameters(m_motorsFrontRight);

		//Back Left Motor PID
		setCommonMotorParameters(m_motorsBackLeft);

		//Back Right Motor PID
		setCommonMotorParameters(m_motorsBackRight);

		robotDrive.setMaxOutput(m_RPMConversion);
		GyroPIDoutput gyroPIDoutPut = new GyroPIDoutput(0.08);
		RobotMap.getNavxGyro().initializeGyroPID(gyroPIDoutPut);
		UltrasonicPIDOutput ultraPIDOutput= new UltrasonicPIDOutput();
		
		RobotMap.getUltrasonicSubsystem().intializeUltrasonicPID(ultraPIDOutput);
		

		initializeYEncoderPID(500);
		initializeXEncoderPID(500);
	}

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
		Trace traceInst = Trace.getInstance();
		double gyroReading = RobotMap.getNavxGyro().getRobotAngle();
		double motorOutput = frontRight.getOutputVoltage() / frontRight.getBusVoltage();
		if (kNoisyDebug) {
			m_sb.append("\tout:");
			m_sb.append(motorOutput);
			m_sb.append("\tspd:");
			m_sb.append(frontRight.getSpeed() );
			m_sb.append("\terr:");
			m_sb.append(frontRight.getClosedLoopError() * 600 / 4096);
			m_sb.append("\ttrg:");
			m_sb.append(-yIn*m_RPMConversion);
			if (++m_loops >= 10) {
				m_loops = 0;
				System.out.println(m_sb.toString());
			}
			m_sb.setLength(0);
		}
		
		// Greatest Regards to 1519
		// update count of iterations since rotation last commanded
		if (Robot.oi.getDriveController().getRawButton(6) && false){
			//This is strafe only mode
			yIn = 0;
			rotation = 0;
			m_iterationsSinceRotationAndYMoveCommanded++;
			if(m_iterationsSinceRotationAndYMoveCommanded == 1){
				initializeOmniWheelEncoderPID();
			}
			else if(m_iterationsSinceRotationAndYMoveCommanded == 5){
				// Need to make sure that the omni wheel has stopped 
				// spinning(if we were turning moving etc.) so we wait till 5
				m_desiredOmniWheelEncoderTick = getOmniWheelEncoderTicks();
				enableOmniWheelPID(m_desiredOmniWheelEncoderTick);
			}
			else if(m_iterationsSinceRotationAndYMoveCommanded > 5){
				yIn = m_omniWheelPIDOutput;
			}
			
			traceInst.addTrace(m_traceOmniFileName, 
					new TracePair("xIn", xIn),
					new TracePair("yIn", yIn),
					new TracePair("m_desiredOmniWheelEncoderTick", m_desiredOmniWheelEncoderTick),
					new TracePair("omniWheelEncoderTicks", getOmniWheelEncoderTicks()/100),
					new TracePair("AverageError", getOmniWheelPIDContoller().getAvgError()));		
		}
		else{
			if(m_iterationsSinceRotationAndYMoveCommanded != 0){
				stopMovingUsingOmniWheel();
			}
			m_iterationsSinceRotationAndYMoveCommanded = 0;
		}
		
		
		
		if (gyroEnabled) {
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
				rotation = (m_desiredHeading - gyroReading) / 50.0;
			}
		}
		
		
		if(prefs.getBoolean("Mecanum Logging", false)) {
			SmartDashboard.putNumber("Front Left Speed", Robot.driveTrain.getM1Speed());
			SmartDashboard.putNumber("Back Right Speed", -Robot.driveTrain.getM2Speed());
			SmartDashboard.putNumber("Front Right Speed", -Robot.driveTrain.getM3Speed());
			SmartDashboard.putNumber("Back Left Speed", Robot.driveTrain.getM4Speed());
			SmartDashboard.putNumber("Y Commanded Speed",yIn);
			SmartDashboard.putNumber("X Commanded Speed", xIn);
			SmartDashboard.putNumber("Rotation", rotation);
		}

		traceInst.addTrace(m_traceFileName, 
				new TracePair("Front Left Speed", Robot.driveTrain.getM1Speed()),
				new TracePair("Back Left Speed", Robot.driveTrain.getM2Speed()),
				new TracePair("Front Right Speed", Robot.driveTrain.getM3Speed()),
				new TracePair("Back Right Speed", Robot.driveTrain.getM4Speed()),
				new TracePair("Y commanded Speed", yIn),
				new TracePair("X commanded Speed", xIn),
				new TracePair("Rotation", rotation));
		
		writeTraceData(m_frontLeftTrace, frontLeft, false);
		writeTraceData(m_frontRightTrace, frontRight, true);
		writeTraceData(m_backLeftTrace, backLeft, false);
		writeTraceData(m_backRightTrace, backRight, true);
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
		if ((Math.abs(output) < minimumOutput) && (output != 0)) {
			double minVal = minimumOutput; 
			if (output < 0) {
				output = -minVal;
			} else {
				output = minVal;				
			}
		}
		return output;
	}
	private PIDController m_omniWheelEncoder;
	
	public PIDController getOmniWheelPIDContoller(){
		return m_omniWheelEncoder;
	}
	
	public double getOmniWheelEncoderTicks(){
		return omniWheelEncoder.getDistance();
	}
	
	private class OmniWheelPIDIn implements PIDSource{
		
		public void setPIDSourceType(PIDSourceType pidSource){
			
		}
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
		public double pidGet(){
			return getOmniWheelEncoderTicks();
		}
		
	}
	
	public class OmniWheelPIDOut implements PIDOutput{
		public void pidWrite(double output) {
			//output = raiseOutputAboveMin(output,0.03);
			m_omniWheelPIDOutput = output;
			
		}
	}
	
	public void initializeOmniWheelEncoderPID(){

		double omniWheelEncoderKp = 0.25;
		double omniWheelEncoderKi = 0.0;
		double omniWheelEncoderKd = 0.0;
		double omniWheelEncoderKf = 0.0;
		double omniWheelEncoderTolerance = 0.1;
		double omniWheelEncoderOutputMax = 0.3;

		resetOmniWheelEncPos();
		OmniWheelPIDIn encoderPIDin = new OmniWheelPIDIn();
		OmniWheelPIDOut encoderPIDout = new OmniWheelPIDOut();
		m_omniWheelEncoder = new PIDController(omniWheelEncoderKp, omniWheelEncoderKi, 
				omniWheelEncoderKd, encoderPIDin, encoderPIDout);
		m_omniWheelEncoder.setOutputRange(-omniWheelEncoderOutputMax, omniWheelEncoderOutputMax);
		m_omniWheelEncoder.setAbsoluteTolerance(omniWheelEncoderTolerance);
		LiveWindow.addActuator("DriveTrain", "OmniWheelEncoderPID", m_omniWheelEncoder);
	}
	
	public void enableOmniWheelPID(double revolutionsToMove) {
		m_omniWheelEncoder.setSetpoint(revolutionsToMove);
		m_omniWheelEncoder.enable();
	}
	
	public boolean isDoneMovingUsingOmniWheel() {
		if (kNoisyDebug) {
			System.out.println("encoder distance = " + getEncoderDistance());
		}
		return m_omniWheelEncoder.onTarget();
	}
	
	public void stopMovingUsingOmniWheel() {
		m_omniWheelEncoder.reset();
	}
	private void resetOmniWheelEncPos(){
		omniWheelEncoder.reset();
	}
	// Encoder PID code

	// Encoder PID controller
	private PIDController m_moveToTheYEncoderPID;


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
			mecanumDrive(0, -output, 0);
			if (kNoisyDebug) {
				last_y_commanded_speed = output * m_RPMConversion;
				System.out.println("Encoder Output = " + output 
						+ " Average Error = " + m_moveToTheYEncoderPID.getAvgError());
			}
		}

	}


	public void initializeYEncoderPID(double distanceToMove) {

		// Encoder PID controller variables
		double yEncoderKp = prefs.getDouble("YEncoderP", 0.25);
		double yEncoderKi = prefs.getDouble("YEncoderI", 0.000);
		double yEncoderKd = prefs.getDouble("YEncoderD", 0.000);
		double yEncoderKf = prefs.getDouble("YEncoderF", 0.000);
		double yEncoderTolerance = prefs.getDouble("YEncoderTolerance", 0.1);
		double yEncoderOutputMax = prefs.getDouble("YEncoderOutputMax", 0.3);
		m_moveYEncoderP = yEncoderKp;

		resetEncPos();
		MovingInTheYEncoderPIDin encoderPIDin = new MovingInTheYEncoderPIDin();
		MovingInTheYEncoderPIDout encoderPIDout = new MovingInTheYEncoderPIDout();
		m_moveToTheYEncoderPID = new PIDController(yEncoderKp, yEncoderKi, 
				yEncoderKd, yEncoderKf, encoderPIDin, encoderPIDout);
		m_moveToTheYEncoderPID.setOutputRange(-yEncoderOutputMax, yEncoderOutputMax);
		m_moveToTheYEncoderPID.setAbsoluteTolerance(yEncoderTolerance);
		LiveWindow.addActuator("DriveTrain", "YEncoderPID", m_moveToTheYEncoderPID);
	}
	public double getYEncoderKp(){
		return m_moveYEncoderP;
	}
	public void moveToYEncoderRevolutions(double revolutionsToMove) {
		resetEncPos();
		m_moveToTheYEncoderPID.setSetpoint(revolutionsToMove);
		m_moveToTheYEncoderPID.enable();
	}
	public void setI(double i){
		m_moveToTheYEncoderPID.setPID(prefs.getDouble("YEncoderP", 0.25), i, prefs.getDouble("YEncoderD", 0.000));
	}
	public void setTolerance(double tol){
		m_moveToTheYEncoderPID.setAbsoluteTolerance(tol);
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
			output = raiseOutputAboveMin(output,0.08);
			last_x_commanded_speed = output * m_RPMConversion;
			
			mecanumDrive(output, 0, 0);
			if (kNoisyDebug) {
				System.out.println("Encoder Output = " + output 
						+ " Average Error = " + m_moveToTheXEncoderPID.getAvgError());
			}
		}

	}


	public void initializeXEncoderPID(double distanceToMove) {

		// Encoder PID controller variables
		double xEncoderKp = prefs.getDouble("XEncoderP", 0.25);
		double xEncoderKi = prefs.getDouble("XEncoderI", 0.000);
		double xEncoderKd = prefs.getDouble("XEncoderD", 0.000);
		double xEncoderKf = prefs.getDouble("XEncoderF", 0.000);
		double xEncoderTolerance = prefs.getDouble("XEncoderTolerance", 0.1);
		double xEncoderOutputMax = prefs.getDouble("XEncoderOutputMax", 0.3);

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
		Robot.driveTrain.useStrafeProfile();
		m_moveToTheXEncoderPID.setSetpoint(revolutionsToMove);
		m_moveToTheXEncoderPID.enable();
	}

	public boolean isDoneMovingToXEncoderRevolutions() {
		Robot.driveTrain.useNormalProfile();
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

		private double m_minimumOutput = 0.30;

		@Override
		public void pidWrite(double output) {
			output = raiseOutputAboveMin(output,m_minimumOutput);
			robotDrive.mecanumDrive_Cartesian(0, 0, output, 0);
			m_iterationsSinceRotationCommanded = 0;
		}

	}

	public void initializeGyroPID(double deltaAngle) {
		GyroPIDoutput gyroPIDout = new GyroPIDoutput(0.18);
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
		if (angleMod < 0) {
			//Correcting Negative Modulus
			angleMod = angleMod + 360; 
		}
		double deltaAngle = angle - angleMod;
		if (Math.abs(deltaAngle) > 180) { 
			// Turn to the minimal angle
			boolean neg = (deltaAngle < 0);
			deltaAngle = Math.abs(deltaAngle) - 360;
			if (neg) {
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
		if (currentPos <  target){
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
			if(Math.abs(output) < 0.12){
				if(output > 0){
					output = 0.12;
				}else{
					output = -0.12;
				}
				
			}
			robotDrive.mecanumDrive_Cartesian(-output, 0, 0, 0);

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
	
	private class UltrasonicPIDOutputFront implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			SmartDashboard.putNumber("Output", output);
			SmartDashboard.putNumber("Distance",
					RobotMap.getUltrasonicFront().getUltrasonicDistance());
			if(Math.abs(output) < 0.12){
				if(output > 0){
					output = 0.12;
				}else{
					output = -0.12;
				}
				
			}
			robotDrive.mecanumDrive_Cartesian(0, output, 0, 0);

		}
	}

	public void intializeUltrasonicPIDFront(double distanceToDriveTo) {
		UltrasonicPIDOutputFront ultraPIDOutput= new UltrasonicPIDOutputFront();
		RobotMap.getUltrasonicFront().intializeUltrasonicPID(ultraPIDOutput);
		RobotMap.getUltrasonicFront().moveWithUltrasonicPID(distanceToDriveTo);

	}

	public boolean doneMovingWithUltrasoncPIDFront() {
		return RobotMap.getUltrasonicFront().doneUltrasonicPID();
	}

	public void stopUltrasonicPIDFront() {
		RobotMap.getUltrasonicFront().stopUltrasonicPID();

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

