package org.usfirst.frc4905.M31.commands;

import org.usfirst.frc4905.M31.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestSerial extends Command {
	private int m_delay = 0;
	private int[] measurements = {-99, -98};
	private int secondNum = 0;
	public TestSerial() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.lidarSystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(m_delay < 50){
			m_delay++;
		}else{
			Robot.lidarSystem.writeUltrasonic();
			Robot.lidarSystem.writeLidar();
			//int ultra = Integer.parseInt(Robot.lidarSystem.read());
			String vals = Robot.lidarSystem.read();
			String val1 = "";
			String val2 = "";
			
			int num1 = 0; 
			int num2 = 0;
			if(vals.length() > 6){
				val1 = vals.substring(0, 3);
				val2 = vals.substring(3, vals.length());
				num1 = Integer.parseInt(val1.trim());
				num2 = Integer.parseInt(val2.trim());
			}
			
		
		//	val1.replaceAll(" ", "");
		//	String val2 = vals.substring(4, vals.length());
		//	val2.replaceAll(" ", "");
			
			
			//measurements[0] = Integer.parseInt(val1);
			//measurements[1] = Integer.parseInt(val2);
			System.out.println("Ultrasonic Reading: " + num1);
			System.out.println("Lidar Reading: "+ num2);
			
			System.out.println("vals: " + vals);



			//int lidar = Integer.parseInt(Robot.lidarSystem.read());
			//System.out.println("Lidar: " +Robot.lidarSystem.read());
			m_delay = 0;
		}

	}


	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
