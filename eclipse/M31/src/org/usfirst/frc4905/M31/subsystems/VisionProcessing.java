package org.usfirst.frc4905.M31.subsystems;



import java.awt.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

import org.usfirst.frc4905.M31.Robot;
import org.usfirst.frc4905.M31.RobotEnableStatus;

import NavXGyro.NavxGyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class VisionProcessing extends Subsystem {
	public class RobotPoseSpecifics{
    	public double angle = 0;
    	public double frontLeftEncoder = 0;
    	public double frontRightEncoder = 0;
    	public double backLeftEncoder = 0;
    	public double backRightEncoder = 0;
    	public double robotTimestamp = 0;
    }
	public class DataForRobotPoseHistory{
    	public double deltaAngle;
    	public double deltaForwardDistance;
    	public double deltaLateralDistance;
    	public double timestamp;
    }
    RobotPoseSpecifics oldRobotPoseSpecifics = new RobotPoseSpecifics();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private NetworkTable m_networkTable;
	private double m_robotPoseHistoryMaxTimeLength = 1;
	private double m_timestamp = 0;
	private boolean m_foundTargetLift = false;
	private double m_angleToTurnLift = 0;
	private double m_distanceToDriveLaterally = 0;
	private double m_distanceToDriveForwardLift = 0;
	private double m_liftTimestamp = 0;
	public ArrayDeque<DataForRobotPoseHistory> robotPoseHistory = new ArrayDeque<DataForRobotPoseHistory>();
	public VisionProcessing() {
		initNetworkTable("VisionProcessing");
		m_networkTable.putBoolean("TimestampRet", false);
		
	}
	
    public void initDefaultCommand() {
        
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    private void initNetworkTable(String table){
    	//NetworkTable.setIPAddress("10.49.5.77");
    	m_networkTable = NetworkTable.getTable(table);
    }
    public void putTimestampOnNetworkTables(){
    	m_networkTable.putBoolean("TimestampRet", true);
    	m_networkTable.putNumber("Timestamp", m_timestamp);
    }
    public void resetTimestampOnNetworkTables(){
    	m_networkTable.putBoolean("TimestampRet", false);
    	m_networkTable.putNumber("Timestamp", 0.0);
    }
    //start of public interface methods
    public boolean doesVisionSeeTarget(){
    	return(false);
    }
    private boolean m_firstTime = true;
    private long m_endTime = 0;
    public boolean isVisionReady(){
    	if(m_firstTime){
    		long startTime = System.currentTimeMillis();
    		long delay = 1*1000; //1 seconds
    		m_endTime = startTime + delay;
    		m_firstTime = false;
    	}
    	if(System.currentTimeMillis() >= m_endTime){
    		m_firstTime = true;
    		return(true);
    	}
    	return (false);
    }
    public double getDeltaAngle(){
    	return m_angleToTurnLift;
    }
    public double getLateralDistance(){
    	return m_distanceToDriveLaterally;
    }
    public double getForwardDistance(){
    	return m_distanceToDriveForwardLift;
    }
    public boolean getFoundLift(){
    	return m_foundTargetLift;
    }
    //end of public interface methods
    
    private class DataForLift{
    	public boolean foundLift;
    	public double angletoTurn;
    	public double distanceAwayForward;
    	public double distanceAwayLateral;
    	public double timestamp;
    }

    public void initDataForLift(){
    	initNetworkTable("VisionProcessing") ;
    	DataForLift data = new DataForLift();
    	data.foundLift = m_networkTable.getBoolean("foundLiftTarget", false);
    	data.angletoTurn = m_networkTable.getNumber("radiansToTurnLift", 0);
    	data.distanceAwayForward = m_networkTable.getNumber("distanceToDriveForwardLift",0);
    	data.distanceAwayLateral = m_networkTable.getNumber("distanceToDriveLaterallyLift",0);
    	data.timestamp = m_networkTable.getNumber("timestampLift",0);
    	compensateLatency(data);
    	m_timestamp = data.timestamp;
    	m_foundTargetLift = data.foundLift;
    	m_angleToTurnLift = data.angletoTurn;
    	m_distanceToDriveForwardLift = data.distanceAwayForward;
    	m_distanceToDriveLaterally = data.distanceAwayLateral;
    	m_liftTimestamp = data.timestamp;
    }
    private class dataForBoiler{
    	public boolean foundBoiler;
    	public double angleToTurn;
    	public double distanceAway;
    	public double timestamp;
    }
    private dataForBoiler getDataForBoiler(){
    	dataForBoiler data = new dataForBoiler();
    	data.angleToTurn = m_networkTable.getNumber("radiansToTurnHighGoal",0);
    	data.distanceAway = m_networkTable.getNumber("distanceAwayHighGoal",0);
    	data.foundBoiler = m_networkTable.getBoolean("foundHighGoalTarget", false);
    	data.timestamp = m_networkTable.getNumber("timestampHighGoal",0);
    	m_timestamp = data.timestamp;
    	return data;
    }

    public void putEnableStatus(RobotEnableStatus enableStatus){
    	boolean boolEnableStatus;
    	if (enableStatus == RobotEnableStatus.DISABLED) {
    		boolEnableStatus = false;
    	} else {
    		boolEnableStatus = true;
    	}
    	m_networkTable.putBoolean("RobotEnabled", boolEnableStatus);
    }

    
    
    public RobotPoseSpecifics getRobotPoseSpecifics(){
    	RobotPoseSpecifics currentDataForRobotPoseHistory = new RobotPoseSpecifics();
    	currentDataForRobotPoseHistory.angle = Robot.driveTrain.getRobotAngle();
    	currentDataForRobotPoseHistory.frontLeftEncoder = Robot.driveTrain.getM1Speed();
    	currentDataForRobotPoseHistory.frontRightEncoder = Robot.driveTrain.getM3Speed();
    	currentDataForRobotPoseHistory.backLeftEncoder = Robot.driveTrain.getM4Speed();
    	currentDataForRobotPoseHistory.backRightEncoder = Robot.driveTrain.getM2Speed();
    	currentDataForRobotPoseHistory.robotTimestamp = Timer.getFPGATimestamp();
    	return currentDataForRobotPoseHistory;
    }
    
    public class DeltaRobotSpecifics{
    	public double deltaAngle;
    	public double deltaFrontLeftEncoder;
    	public double deltaFrontRightEncoder;
    	public double deltaBackLeftEncoder;
    	public double deltaBackRightEncoder;
    }
    
    private void setRobotPoseHistory(DataForRobotPoseHistory dataForRobotPoseHistory){
    	robotPoseHistory.add(dataForRobotPoseHistory);
    	if (dataForRobotPoseHistory.timestamp - robotPoseHistory.getFirst().timestamp > m_robotPoseHistoryMaxTimeLength){
    		robotPoseHistory.remove(0);
    	}
    }
    
    private DataForRobotPoseHistory getMovementDataForRobotPoseHistory(DeltaRobotSpecifics deltaRobotSpecifics) {
    	DataForRobotPoseHistory dataForRobotPoseHistory = new DataForRobotPoseHistory();
    	dataForRobotPoseHistory.deltaForwardDistance = (deltaRobotSpecifics.deltaBackLeftEncoder
    			+ deltaRobotSpecifics.deltaBackRightEncoder
    			+ deltaRobotSpecifics.deltaFrontLeftEncoder
    			+ deltaRobotSpecifics.deltaFrontRightEncoder)/4;
    	
    	dataForRobotPoseHistory.deltaLateralDistance = (-deltaRobotSpecifics.deltaFrontLeftEncoder
    			+ deltaRobotSpecifics.deltaFrontRightEncoder
    			+ deltaRobotSpecifics.deltaBackLeftEncoder
    			- deltaRobotSpecifics.deltaBackRightEncoder)/4;
    	
    	return dataForRobotPoseHistory;
	}
    
    private void setOldRobotPoseSpecifics(RobotPoseSpecifics robotPoseSpecifics){
    	oldRobotPoseSpecifics = robotPoseSpecifics;
    }
    private void updateNetworkTableRobotTimestamp(double timestamp){
    	m_networkTable.putNumber("RobotTimestamp", timestamp);
    }
    public void updateRobotPositionHistory(){
    	RobotPoseSpecifics currentRobotPoseSpecifics = new RobotPoseSpecifics();
    	
    	DeltaRobotSpecifics deltaRobotSpecifics = new DeltaRobotSpecifics();
    	DataForRobotPoseHistory dataForRobotPoseHistory = new DataForRobotPoseHistory();
    	currentRobotPoseSpecifics = getRobotPoseSpecifics();
    	updateNetworkTableRobotTimestamp(currentRobotPoseSpecifics.robotTimestamp);
    	deltaRobotSpecifics.deltaAngle = currentRobotPoseSpecifics.angle - oldRobotPoseSpecifics.angle;
    	deltaRobotSpecifics.deltaFrontLeftEncoder = (currentRobotPoseSpecifics.frontLeftEncoder 
    			- oldRobotPoseSpecifics.frontLeftEncoder);
    	deltaRobotSpecifics.deltaFrontRightEncoder = (currentRobotPoseSpecifics.frontRightEncoder 
    			- oldRobotPoseSpecifics.frontRightEncoder);
    	deltaRobotSpecifics.deltaBackLeftEncoder = (currentRobotPoseSpecifics.backLeftEncoder 
    			- oldRobotPoseSpecifics.backLeftEncoder);
    	deltaRobotSpecifics.deltaBackRightEncoder = (currentRobotPoseSpecifics.backRightEncoder 
    			- oldRobotPoseSpecifics.backRightEncoder);
    	
    	dataForRobotPoseHistory = getMovementDataForRobotPoseHistory(deltaRobotSpecifics);
    	dataForRobotPoseHistory.timestamp = currentRobotPoseSpecifics.robotTimestamp;
    	setRobotPoseHistory(dataForRobotPoseHistory);
    	setOldRobotPoseSpecifics(currentRobotPoseSpecifics);
    }
    
	public void compensateLatency(DataForLift dataForLift){
		for (DataForRobotPoseHistory head : robotPoseHistory){
			if (dataForLift.timestamp <= head.timestamp){
				break;
			}
			robotPoseHistory.removeFirst();
		}
		for (DataForRobotPoseHistory head : robotPoseHistory){
			dataForLift.distanceAwayForward += head.deltaForwardDistance * Math.cos(dataForLift.angletoTurn)
					- head.deltaLateralDistance* Math.sin(dataForLift.angletoTurn);
			dataForLift.distanceAwayLateral += head.deltaForwardDistance * Math.sin(dataForLift.angletoTurn)
					+ head.deltaLateralDistance * Math.cos(dataForLift.angletoTurn);
			dataForLift.angletoTurn -= head.deltaAngle;
		}
	}

}
