package NavXGyro;

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
	private static final double gyroEncoderOutputMax = 1.0; 
	private double m_initialAngleReading = 0;
	
	private static AHRS m_navX;
	public NavxGyro() {
		try {
			/* Communicate w/navX MXP via the MXP SPI Bus.                                     */
			/* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
			/* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
			m_navX = new AHRS(SPI.Port.kMXP);
			System.out.println("Created NavX instance");
			SmartDashboard.putBoolean("NavXgyro Connected", m_navX.isConnected());
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), 
					true);
		}
	}
	public static AHRS getAHRS() {
		return m_navX;
	}
	public void setInitialAngleReading() {
		m_initialAngleReading = m_navX.getAngle();
	}
	
	public PIDController getPIDcontroller() {
		return m_gyroEncoderPID; 
	}
	
	public double getRobotAngle() {
		return m_navX.getAngle() - m_initialAngleReading;
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
		GyroPIDin gyroPIDin = new GyroPIDin();
		m_gyroEncoderPID = new PIDController(gyroEncoderKp, gyroEncoderKi, 
				gyroEncoderKd, gyroEncoderKf, gyroPIDin, gyroPIDout);
		m_gyroEncoderPID.setOutputRange(-gyroEncoderOutputMax, gyroEncoderOutputMax);
		m_gyroEncoderPID.setAbsoluteTolerance(gyroEncoderTolerance);
		LiveWindow.addActuator("DriveTrain", "GyroPID", m_gyroEncoderPID);
	}
	
	public void turnWithGyroPID(double AngleToTurnTo) {
		m_gyroEncoderPID.setSetpoint(AngleToTurnTo);
		m_gyroEncoderPID.enable();
	}
	
	public boolean isDoneGyroPID() {
		System.out.println("angle = " + getRobotAngle());
		return m_gyroEncoderPID.onTarget();
	}
	public void stopGyroPID() {
		m_gyroEncoderPID.disable();
		
	}
	
}






