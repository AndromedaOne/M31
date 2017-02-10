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
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    public void initNetworkTable(String table){
    	m_networkTable = NetworkTable.getTable(table);
    	m_robotCommands = NetworkTable.getTable("RobotCommmands");
    }
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
    	data.angletoTurn = m_networkTable.getDouble("radiansToTurnLift");
    	data.distanceAwayForward = m_networkTable.getDouble("distanceToDriveForwardLift");
    	data.distanceAwayLateral = m_networkTable.getDouble("distanceToDdriveLaterallyLift");
    	data.timestamp = m_networkTable.getDouble("timestampLift");
    	timestamp = data.timestamp;
    	return data;
    }
    public dataForBoiler getDataForBoiler(){
    	dataForBoiler data = new dataForBoiler();
    	data.angleToTurn = m_networkTable.getDouble("radiansToTurnHighGoal");
    	data.distanceAway = m_networkTable.getDouble("distanceAwayHighGoal");
    	data.foundBoiler = m_networkTable.getBoolean("foundHighGoalTarget", false);
    	data.timestamp = m_networkTable.getDouble("timestampHighGoal");
    	timestamp = data.timestamp;
    	return data;
    }
    public void turnOffPi(){
    	m_robotCommands.putBoolean("TurnOff", true);
    }
    public void saveTimestamp(double timestamp){
    	m_robotCommands.putBoolean("TimestampRet", true);
    	m_robotCommands.putDouble("Timestamp", timestamp);
    }
    public double getLastTimestamp(){
    	return timestamp;
    }
}

