// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4905.M31;

import org.usfirst.frc4905.M31.commands.*;
import org.usfirst.frc4905.M31.groupCommands.*;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc4905.M31.subsystems.*;

import Utilities.SideOfField;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released  and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());


	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public JoystickButton getEncButton;
	public JoystickButton resetEncButton;
	public static Joystick driveController;
	public JoystickButton saveImageStateButton;
	public JoystickButton placeGearAutomatically;

	public JoystickButton openGroundGH;
	public JoystickButton closeGroundGH;
	
	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick subController;
    public JoystickButton closeHandler;
    public JoystickButton toggleHandler;
	public JoystickButton toggleButton;
	public JoystickButton climbButton;
	public JoystickButton shootFromBoilerButton;
	public JoystickButton safeCloseGroundGHButton;
	
	public static JoystickButton turnToLeftLift;
	public static JoystickButton turnToMiddleLift;
	public static JoystickButton turnToRightLift;
	public static JoystickButton RaiseGroundGH;
	public static JoystickButton LowerGroundGH;
	
	
	public static JoystickButton SpinNewGHRoller;
	
	public OI() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

		driveController = new Joystick(0);
        subController = new Joystick(1);

        //Drive Controllers
		
		
		placeGearAutomatically = new JoystickButton(driveController, 1);
		placeGearAutomatically.whenPressed(new LineUpForGearInTeleop());
		
		
		openGroundGH = new JoystickButton(driveController, 7);
		openGroundGH.whileHeld(new NewGHopenClose(0.4));
		closeGroundGH = new JoystickButton(driveController, 8);
		closeGroundGH.whileHeld(new NewGHopenClose(-0.4));
		
		
		turnToLeftLift = new JoystickButton(driveController, 3);
		turnToLeftLift.whenPressed(new TurnToCompassHeading(330));
		turnToMiddleLift = new JoystickButton(driveController, 4);
		turnToMiddleLift.whenPressed(new TurnToCompassHeading(270));
		turnToRightLift = new JoystickButton(driveController, 2);
		turnToRightLift.whenPressed(new TurnToCompassHeading(210));			
        //closeHandler = new JoystickButton(subController, 1);
		//closeHandler.whenPressed(new CloseGearHandler());
		

		// SmartDashboard Buttons
        SmartDashboard.putData("SetVisionData", new SetVisionData());
		SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
		SmartDashboard.putData("TeleopDrive", new TeleopDrive());
		SmartDashboard.putData("getEncPos", new GetEncPos());
		SmartDashboard.putData("resetEncPos", new ResetEncPos());
		SmartDashboard.putData("autoMove", new AutoMove());
		SmartDashboard.putData("comboMove", new ComboMove());
        SmartDashboard.putData("GetCurrentAngle", new CurrentAngleButton());
        SmartDashboard.putData("OpenGearHandler", new OpenGearHandler());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        SmartDashboard.putData("TurnToDegreeAngle", new TurnDeltaAngleDegree(40));
        SmartDashboard.putData("TurnToRadianAngle", new TurnDeltaAngleRadian(0.698132));
        SmartDashboard.putData("TurnToCompassHeading", new TurnToCompassHeading(40));
		SmartDashboard.putData("GoToTargetDistance", new MoveUsingUltrasonic(24));
		
		SmartDashboard.putData("Group Boiler Slide Lift Shoot", new GroupBoilerSideLiftShoot());
		SmartDashboard.putData("Group Position C Hopper & Fire", new GroupPosCHopperFire());
		SmartDashboard.putData("Group Left Lift With Vision", new GroupLiftLeftVisionCorrect());
		SmartDashboard.putData("Group Lift Vision Right", new GroupLiftVisionRight());
		SmartDashboard.putData("Group Middle Lift", new GroupMiddleLift());
		SmartDashboard.putData("Group Middle Lift Fire", new GroupMiddleLiftFire());
		SmartDashboard.putData("Group Middle Lift Fire Vision", new GroupMiddleLiftFireVision());
		SmartDashboard.putData("Group Middle Lift Vision", new GroupMiddleLiftVision());
		SmartDashboard.putData("Group Move Past Baseline", new GroupMovePastBaseline());
		SmartDashboard.putData("Group Right Lift No Vision", new GroupLiftRightNoVision());
		SmartDashboard.putData("Group Shoot From Start Cross Baseline", new GroupShootFromStartCrossBaseLineRed());
		SmartDashboard.putData("MoveForward with ultrasonic to 6 inches", new TestMoveUltraFront());
	
		subController = new Joystick(1);		
		toggleButton = new JoystickButton(subController, 3);
		
		safeCloseGroundGHButton = new JoystickButton(subController, 5);
		safeCloseGroundGHButton.whenPressed(new SafeCloseNewGH());
		
		climbButton = new JoystickButton(subController, 4);
		climbButton.whileHeld(new climbRope());
		shootFromBoilerButton = new JoystickButton(subController, 6);
		shootFromBoilerButton.whileHeld(new ShootFromTheBoiler(720));
		shootFromBoilerButton.whenReleased(new ControlledFeederStop());
		saveImageStateButton = new JoystickButton(subController, 1);
		saveImageStateButton.whenPressed(new SaveVisionState());
		toggleHandler = new JoystickButton(subController, 2);
       	toggleHandler.whileHeld(new OpenGearHandler());
        toggleHandler.whenReleased(new CloseGearHandler());
        RaiseGroundGH = new JoystickButton(subController, 7);
        RaiseGroundGH.whileHeld(new NewGHmoveUpDown(-0.50));
        LowerGroundGH = new JoystickButton(subController, 8);
        LowerGroundGH.whileHeld(new NewGHmoveUpDown(0.50));
        
        SpinNewGHRoller = new JoystickButton(subController, 3);
	
        SpinNewGHRoller.whileHeld(new RunGHRoller(1));
	
	}
	


	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
	public Joystick getDriveController() {
		return driveController;
	}


	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
	public Joystick getSubGamePad() {
		return subController;
	}



	public static boolean getAButton(Joystick gamepad) {

		return gamepad.getRawButton(1);

	}

	public static boolean getBButton(Joystick gamepad) {

		return gamepad.getRawButton(2);

	}

	public static boolean getXButton(Joystick gamepad) {

		return gamepad.getRawButton(3);

	}

	public static boolean getYButton(Joystick gamepad) {

		return gamepad.getRawButton(4);

	}

	public static boolean getLeftButton(Joystick gamepad) {

		return gamepad.getRawButton(5);

	}

	public static boolean getRightButton(Joystick gamepad) {

		return gamepad.getRawButton(6);

	}

	public static boolean getBackButton(Joystick gamepad) {

		return gamepad.getRawButton(7);

	}

	public static boolean getStartButton(Joystick gamepad) {

		return gamepad.getRawButton(8);

	}

	public static boolean getLeftStickButton(Joystick gamepad) {

		return gamepad.getRawButton(9);

	}

	public static boolean getRightStickButton(Joystick gamepad) {

		return gamepad.getRawButton(10);

	}

	public static double getLeftStickHorizontal (Joystick gamepad) {

		return gamepad.getRawAxis(0);

	}

	public static double getLeftStickVertical (Joystick gamepad) {

		return gamepad.getRawAxis(1);

	}

	public static boolean getLeftTriggerButton(Joystick gamepad) {

		return gamepad.getRawAxis(2)>0.5;

	}

	public static double getLeftTriggerValue(Joystick gamepad) {

		return gamepad.getRawAxis(2);

	}

	public static boolean getRightTriggerButton(Joystick gamepad) {

		return gamepad.getRawAxis(3)>0.5;

	}

	public static double getRightTriggerValue(Joystick gamepad) {

		return gamepad.getRawAxis(3);

	}

	public static double getRightStickHorizontal (Joystick gamepad) {

		return gamepad.getRawAxis(4);

	}

	public static double getRightStickVertical (Joystick gamepad) {

		return gamepad.getRawAxis(5);

	}
}

