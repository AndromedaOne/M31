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

import org.usfirst.frc4905.M31.Robot;
import org.usfirst.frc4905.M31.RobotMap;
import org.usfirst.frc4905.M31.commands.*;
import org.usfirst.frc4905.M31.OI;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;


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


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TeleopDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
   public void teleopDrive(Joystick driveGamepad){
	   
	
	   	
	   double x = OI.getLeftStickHorizontal(driveGamepad);
	   double y = OI.getLeftStickVertical(driveGamepad);
	   double yaw = OI.getRightStickHorizontal(driveGamepad);
	   
	   
	   if(yaw > -0.05 && yaw < 0.05){ yaw = 0;}
	   robotDrive.mecanumDrive_Cartesian(x, y, yaw, 0);//that last 0 is a placeholder for a gyro reading
	   /*double rightHoriz = OI.getRightStickHorizontal(driveGamepad);
	   if(rightHoriz <-0.1 || rightHoriz > 0.1){
		   frontRight.set(rightHoriz);
		   frontLeft.set(rightHoriz);
		   backRight.set(-rightHoriz);
		   backLeft.set(-rightHoriz);
	   }*/
	   
	   //backLeft.changeControlMode(TalonControlMode.Position);
	
	   /* double x = OI.getLeftStickHorizontal(driveGamepad);
	   double y = -OI.getLeftStickVertical(driveGamepad);
	   double yaw = OI.getRightStickHorizontal(driveGamepad);
	   backLeft.set(-x + y + yaw);
	   frontLeft.set(x + y + yaw);
	   frontRight.set(x - y + yaw);
	   backRight.set(-x - y + yaw);*/
	   
	   //System.out.println(OI.getLeftStickHorizontal(driveGamepad));
	   
   }
   public void stop(){
	   frontLeft.set(0);
	   frontRight.set(0);
	   backLeft.set(0);
	   backRight.set(0);
   }
   
   public void getEncPos(){

	   System.out.println("Back Left Pos:" +backLeft.getPosition());
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
   public double getDistance(){
	   double sum = 0;
	   sum += Math.abs(backLeft.getPosition());
	   sum += Math.abs(backRight.getPosition());
	   sum += Math.abs(frontLeft.getPosition());
	   sum += Math.abs(frontRight.getPosition());
	   return sum;
   }

   // Gyro PID code 
   private class GyroPIDoutput implements PIDOutput {

	   @Override
	   public void pidWrite(double output) {

	   }

   }

   public void initializeGyroPID() {


   }

   public boolean doneTurningWithGyro() {
	   // TODO Auto-generated method stub
	   return false;
   }

   public void stopGyroPID() {
	   // TODO Auto-generated method stub

   
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
}

