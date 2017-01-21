package navXgyro;


import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class NavXgyro implements LiveWindowSendable {
	// gyroEncoder PID controller
	private PIDController m_gyroEncoderPID;
	// gyroEncoder PID controller variables
	private static final double gyroEncoderKp = 0.017;
	private static final double gyroEncoderKi = 0.001;
	private static final double gyroEncoderKd = 0.008;
	private static final double gyroEncoderKf = 0.000;
	private static final double gyroEncoderTolerance = 2.0;
	private static final double gyroEncoderOutputMax = 0.75;
	private double m_initialAngleReading;
	private static AHRS m_navX;
	
	public NavXgyro() {
		try {
			/* Communicate w/navX MXP via the MXP SPI Bus.                                     */
			/* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
			/* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
			m_navX = new AHRS(SPI.Port.kOnboardCS0);
			System.out.println("Created NavX instance");
			SmartDashboard.putBoolean("NavXgyro Connected", m_navX.isConnected());
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), 
					true);
		}
		
	}

	public void setInitialHeading () {
		m_initialAngleReading = m_navX.getAngle();
	}
	
	private double m_headingToTurnTo = 0;

	// when we have both encoders working, left/gyro, we'll provide an average of the two
	// to the PID class. gyro now just send the gyro side, which happens to be working.
	public double m_gyroAngle = 0;
	private class GyroEncoderPIDsource implements PIDSource {

		@Override
		public double pidGet() {
			double gyroAngle = getRobotAngle();
			m_gyroAngle = gyroAngle;
			SmartDashboard.putNumber("Gyro angle", gyroAngle);
			System.out.println("Gyro Angle: " + gyroAngle);
			return gyroAngle;
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return PIDSourceType.kDisplacement;
		}

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			// TODO Auto-generated method stub
			
		}

	}

	public void initGyroEncoderPID(PIDOutput gyroPIDOutput) {
		GyroEncoderPIDsource gyroEncoderPIDsource = new GyroEncoderPIDsource();
		m_gyroEncoderPID = new PIDController(gyroEncoderKp, gyroEncoderKi, gyroEncoderKd, 
				gyroEncoderKf, gyroEncoderPIDsource, gyroPIDOutput);
		m_gyroEncoderPID.setOutputRange(-gyroEncoderOutputMax,  gyroEncoderOutputMax);
		m_gyroEncoderPID.setAbsoluteTolerance(gyroEncoderTolerance);
		LiveWindow.addActuator("DriveTrain", "gyroEncoderPID", m_gyroEncoderPID);
		LiveWindow.addSensor("navXgyro", 0, this);
	}

	public void startTurningUsingGyroPID(double headingToTurnTo) {
		m_headingToTurnTo = headingToTurnTo;
		m_gyroEncoderPID.setSetpoint(headingToTurnTo);
		m_gyroEncoderPID.enable();
		
	}
	public boolean doneTurningUsingGyro() {
		return m_gyroEncoderPID.onTarget();
		
	}
	public boolean gyroOnTarget(){
		if(Math.abs(m_headingToTurnTo - m_gyroAngle) <= gyroEncoderTolerance ){
			return true;
		}
		return false;
	}
	
	@Override
	public String getSmartDashboardType() {
		// TODO Auto-generated method stub
		return "navXgyro";
	}
	
	private ITable m_iTable; 
	
	@Override
	public ITable getTable() {
		// TODO Auto-generated method stub
		return m_iTable;
	}

	@Override
	public void initTable(ITable iTable) {
		// TODO Auto-generated method stub
		m_iTable = iTable;
		updateTable();
	}

	@Override
	public void startLiveWindowMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopLiveWindowMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTable() {
		// TODO Auto-generated method stub
		if(m_iTable != null){
			m_iTable.putNumber("Angle", getRobotAngle());
			//System.out.println("Update Table: angle = " + m_navX.getAngle());
		}
	}	
	
	public void endGyroPID () {
		m_gyroEncoderPID.disable();
	}
	
	public double getRobotAngle(){
		return m_navX.getAngle() - m_initialAngleReading;	
	}

}
