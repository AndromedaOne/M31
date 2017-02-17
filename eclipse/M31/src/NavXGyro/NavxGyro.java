package NavXGyro;

import org.usfirst.frc4905.M31.commands.TurnToCompassHeading;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavxGyro {

	// gyroEncoder PID controller
	private PIDController m_gyroEncoderPID;
	// gyroEncoder PID controller variables
	private static final double gyroEncoderKp = 0.012;
	private static final double gyroEncoderKi = 0.000;
	private static final double gyroEncoderKd = 0.000;
	private static final double gyroEncoderKf = 0.000;
	private static final double gyroEncoderTolerance = 1.0;
	private static final double gyroEncoderOutputMax = 1.0/2; 
	private double m_initialAngleReading = 0;

	private AHRS m_navX;
	public NavxGyro() {
		try {
			/* Communicate w/navX MXP via the MXP SPI Bus.                                     */
			/* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
			/* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
			m_navX = new AHRS(SPI.Port.kMXP);
			System.out.println("Created NavX instance");
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), 
					true);
		}
	}
	public AHRS getAHRS() {
		return m_navX;
	}
	public void setInitialAngleReading() {
		m_initialAngleReading = m_navX.getAngle();
	}

	public PIDController getPIDcontroller() {
		return m_gyroEncoderPID; 
	}

	private int m_getRobotAngleCount = 0;
	
	public double getRobotAngle() {
		double correctedAngle = m_navX.getAngle() - m_initialAngleReading;
		if((m_getRobotAngleCount % 10) == 0) {
			SmartDashboard.putNumber("Raw Anlge", m_navX.getAngle());
			SmartDashboard.putNumber("Get Robot Angle", correctedAngle);
		}
	
		return correctedAngle;
	}

	private class GyroPIDin implements PIDSource {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {

		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			return getRobotAngle();
		}

	}

	public void initializeGyroPID(PIDOutput gyroPIDout) {
		System.out.println("InitGyroPID");
		GyroPIDin gyroPIDin = new GyroPIDin();
		m_gyroEncoderPID = new PIDController(gyroEncoderKp, gyroEncoderKi, 
				gyroEncoderKd, gyroEncoderKf, gyroPIDin, gyroPIDout);
		m_gyroEncoderPID.setOutputRange(-gyroEncoderOutputMax, gyroEncoderOutputMax);
		m_gyroEncoderPID.setAbsoluteTolerance(gyroEncoderTolerance);
		LiveWindow.addActuator("DriveTrain", "GyroPID", m_gyroEncoderPID);
	}

	public void turnWithGyroPID(double deltaAngle) {
		double finalAngle = getRobotAngle() + deltaAngle;
		m_gyroEncoderPID.setSetpoint(finalAngle);
		m_gyroEncoderPID.enable();
	}

	public boolean isDoneGyroPID() {
		//System.out.println("angle = " + getRobotAngle());
		return m_gyroEncoderPID.onTarget();
	}
	public void stopGyroPID() {
		m_gyroEncoderPID.disable();

	}
	public boolean isCalibrating() {
		
		return m_navX.isCalibrating();
	}
	
	public float getWorldLinearAccelX()  {
		return m_navX.getWorldLinearAccelX();
	}
	
	public float getWorldLinearAccelY()	 {
		return m_navX.getWorldLinearAccelY();
	}
	
	public float getWorldLinearAccelZ()  {
		return m_navX.getWorldLinearAccelZ();
	}
}






