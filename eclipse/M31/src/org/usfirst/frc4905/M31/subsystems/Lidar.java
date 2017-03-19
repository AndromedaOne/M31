package org.usfirst.frc4905.M31.subsystems;

import java.io.*;

import org.usfirst.frc4905.M31.commands.*;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lidar extends Subsystem {
	
	
	private static SerialPort arduino = new SerialPort(9600, SerialPort.Port.kUSB1);
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TestSerial());
    }
    
    public void writeUltrasonic(){
    	arduino.writeString("u");
    	
    }
    
    public void writeLidar(){
    	arduino.writeString("l");
    	
    }
    
    public void test(){
    	
    	
    }
    
    public String read(){
    	
    	String dist = arduino.readString();
    	return dist;
    }
}

