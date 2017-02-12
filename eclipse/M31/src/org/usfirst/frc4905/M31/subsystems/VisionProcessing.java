package org.usfirst.frc4905.M31.subsystems;



import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class VisionProcessing extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private NetworkTable m_networkTable;
	private NetworkTable m_robotCommands;
	private double timestamp = 0;
	public boolean m_foundTargetLift = false;
	public double m_angleToTurnLift = 0;
	public double m_distanceToDriveLaterally = 0;
	public double m_distanceToDriveForwardLift = 0;
	public double m_liftTimestamp = 0;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    public void initNetworkTable(String table){
    	m_networkTable = NetworkTable.getTable(table);
    	m_robotCommands = NetworkTable.getTable("RobotCommmands");
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
    		long delay = 2*1000; //2 seconds
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
    	return(0);
    }
    public double getLateralDistance(){
    	return(0);
    }
    public double getForwardDistance(){
    	return(0);
    }
    //end of public interface methods
    
    public class dataForLift{
    	public boolean foundLift;
    	public double angletoTurn;
    	public double distanceAwayForward;
    	public double distanceAwayLateral;
    	public double timestamp;
    }
    public class dataForBoiler{
    	public boolean foundBoiler;
    	public double angleToTurn;
    	public double distanceAway;
    	public double timestamp;
    }
    public dataForLift getDataForLift(){
    	dataForLift data = new dataForLift();
    	data.foundLift = m_networkTable.getBoolean("foundLiftTarget", false);
    	data.angletoTurn = m_networkTable.getNumber("radiansToTurnLift", 0);
    	data.distanceAwayForward = m_networkTable.getNumber("distanceToDriveForwardLift",0);
    	data.distanceAwayLateral = m_networkTable.getNumber("distanceToDdriveLaterallyLift",0);
    	data.timestamp = m_networkTable.getNumber("timestampLift",0);
    	timestamp = data.timestamp;
    	return data;
    }
    public dataForBoiler getDataForBoiler(){
    	dataForBoiler data = new dataForBoiler();
    	data.angleToTurn = m_networkTable.getNumber("radiansToTurnHighGoal",0);
    	data.distanceAway = m_networkTable.getNumber("distanceAwayHighGoal",0);
    	data.foundBoiler = m_networkTable.getBoolean("foundHighGoalTarget", false);
    	data.timestamp = m_networkTable.getNumber("timestampHighGoal",0);
    	timestamp = data.timestamp;
    	return data;
    }
    public void turnOffPi(){
    	m_robotCommands.putBoolean("TurnOff", true);
    }
    public void saveTimestamp(double timestamp){
    	m_robotCommands.putBoolean("TimestampRet", true);
    	m_robotCommands.putNumber("Timestamp", timestamp);
    }
    public double getLastTimestamp(){
    	return timestamp;
    }
    public void saveDataForLift(boolean foundLift ,double angletoTurn,double distanceAwayForward,
			double distanceAwayLateral, double timestamp){
    	m_foundTargetLift = foundLift;
    	m_angleToTurnLift = angletoTurn;
    	m_distanceToDriveForwardLift = distanceAwayForward;
    	m_distanceToDriveLaterally = distanceAwayLateral;
    	m_liftTimestamp = timestamp;
    }
}

