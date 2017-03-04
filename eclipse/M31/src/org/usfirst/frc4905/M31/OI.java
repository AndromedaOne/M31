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
	public Joystick driveController;
	public JoystickButton saveImageButton;
	public JoystickButton placeGearAutomatically;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick subController;
    public JoystickButton closeHandler;
    public JoystickButton toggleHandler;
	public JoystickButton toggleButton;
	public JoystickButton climbButton;
	public JoystickButton shootFromBoilerButton;
	public JoystickButton reverseIntakeButton;
	
	public JoystickButton turnToLeftLift;
	public JoystickButton turnToMiddleLift;
	public JoystickButton turnToRightLift;
	
	
	public OI() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

		driveController = new Joystick(0);
        subController = new Joystick(1);

        //Drive Controllers
		

		placeGearAutomatically = new JoystickButton(driveController, ButtonsEnumerated.ABUTTON.getValue());
		placeGearAutomatically.whenPressed(new PlaceGearAutomatically(0));
		turnToLeftLift = new JoystickButton(driveController, ButtonsEnumerated.XBUTTON.getValue());
		turnToLeftLift.whenPressed(new TurnToCompassHeading(330));
		turnToMiddleLift = new JoystickButton(driveController, ButtonsEnumerated.YBUTTON.getValue());
		turnToMiddleLift.whenPressed(new TurnToCompassHeading(270));
		turnToRightLift = new JoystickButton(driveController, ButtonsEnumerated.BBUTTON.getValue());
		turnToRightLift.whenPressed(new TurnToCompassHeading(210));

        //closeHandler = new JoystickButton(subController, 1);
		//closeHandler.whenPressed(new CloseGearHandler());
		toggleHandler = new JoystickButton(subController, ButtonsEnumerated.BBUTTON.getValue());
       	toggleHandler.whileHeld(new OpenGearHandler());
        toggleHandler.whenReleased(new CloseGearHandler());

		// SmartDashboard Buttons
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
		SmartDashboard.putData("Group Close Hopper Fire No Vision", new GroupCloseHopperFireNoVision());
		SmartDashboard.putData("Group Close Hopper Fire Vision", new GroupCloseHopperFireVision());
		SmartDashboard.putData("Group Left Lift With Vision", new GroupLiftLeftVisionCorrect());
		SmartDashboard.putData("Group Lift Vision Right", new GroupLiftVisionRight());
		SmartDashboard.putData("Group Middle Lift", new GroupMiddleLift());
		SmartDashboard.putData("Group Middle Lift Fire", new GroupMiddleLiftFire());
		SmartDashboard.putData("Group Middle Lift Fire Vision", new GroupMiddleLiftFireVision());
		SmartDashboard.putData("Group Middle Lift No Move", new GroupMiddleLiftNoMove());
		SmartDashboard.putData("Group Middle Lift No Move Vision", new GroupMiddleLiftNoMoveVision());
		SmartDashboard.putData("Group Middle Lift Vision", new GroupMiddleLiftVision());
		SmartDashboard.putData("Group Move Past Baseline", new GroupMovePastBaseline());
		SmartDashboard.putData("Group Right Lift No Vision", new GroupLiftRightNoVision());
		SmartDashboard.putData("Group Shoot From Start Cross Baseline", new GroupShootFromStartCrossBaseLine());
		SmartDashboard.putData("MoveForward with ultrasonic to 6 inches", new TestMoveUltraFront());
		subController = new Joystick(1);		
		toggleButton = new JoystickButton(subController, ButtonsEnumerated.XBUTTON.getValue());
		reverseIntakeButton = new JoystickButton(subController, ButtonsEnumerated.LEFTBUMPERBUTTON.getValue());
		reverseIntakeButton.whileHeld(new ReverseIntake());
		climbButton = new JoystickButton(subController, ButtonsEnumerated.YBUTTON.getValue());
		climbButton.whileHeld(new climbRope());
		shootFromBoilerButton = new JoystickButton(subController, ButtonsEnumerated.RIGHTBUMPERBUTTON.getValue());
		shootFromBoilerButton.whileHeld(new ShootFromTheBoiler(790));
		shootFromBoilerButton.whenReleased(new ControlledFeederStop());
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

		return gamepad.getRawButton(ButtonsEnumerated.ABUTTON.getValue());

	}

	public static boolean getBButton(Joystick gamepad) {

		return gamepad.getRawButton(ButtonsEnumerated.BBUTTON.getValue());

	}

	public static boolean getXButton(Joystick gamepad) {

		return gamepad.getRawButton(ButtonsEnumerated.XBUTTON.getValue());

	}

	public static boolean getYButton(Joystick gamepad) {

		return gamepad.getRawButton(ButtonsEnumerated.YBUTTON.getValue());

	}

	public static boolean getLeftButton(Joystick gamepad) {

		return gamepad.getRawButton(ButtonsEnumerated.LEFTBUMPERBUTTON.getValue());

	}

	public static boolean getRightButton(Joystick gamepad) {

		return gamepad.getRawButton(ButtonsEnumerated.RIGHTBUMPERBUTTON.getValue());

	}

	public static boolean getBackButton(Joystick gamepad) {

		return gamepad.getRawButton(ButtonsEnumerated.BACKBUTTON.getValue());

	}

	public static boolean getStartButton(Joystick gamepad) {

		return gamepad.getRawButton(ButtonsEnumerated.STARTBUTTON.getValue());

	}

	public static boolean getLeftStickButton(Joystick gamepad) {

		return gamepad.getRawButton(ButtonsEnumerated.LEFTSTICKBUTTON.getValue());

	}

	public static boolean getRightStickButton(Joystick gamepad) {

		return gamepad.getRawButton(ButtonsEnumerated.RIGHTSTICKBUTTON.getValue());

	}

	public static double getLeftStickHorizontal (Joystick gamepad) {

		return gamepad.getRawAxis(EnumeratedRawAxis.LEFTSTICKHORIZONTAL.getValue());

	}

	public static double getLeftStickVertical (Joystick gamepad) {

		return gamepad.getRawAxis(EnumeratedRawAxis.LEFTSTICKVERTICAL.getValue());

	}

	public static boolean getLeftTriggerButton(Joystick gamepad) {

		return gamepad.getRawAxis(EnumeratedRawAxis.LEFTTRIGGER.getValue())>0.5;

	}

	public static double getLeftTriggerValue(Joystick gamepad) {

		return gamepad.getRawAxis(EnumeratedRawAxis.LEFTTRIGGER.getValue());

	}

	public static boolean getRightTriggerButton(Joystick gamepad) {

		return gamepad.getRawAxis(EnumeratedRawAxis.RIGHTTRIGGER.getValue())>0.5;

	}

	public static double getRightTriggerValue(Joystick gamepad) {

		return gamepad.getRawAxis(EnumeratedRawAxis.RIGHTTRIGGER.getValue());

	}

	public static double getRightStickHorizontal (Joystick gamepad) {

		return gamepad.getRawAxis(EnumeratedRawAxis.RIGHTSTICKHORIZONTAL.getValue());

	}

	public static double getRightStickVertical (Joystick gamepad) {

		return gamepad.getRawAxis(EnumeratedRawAxis.RIGHTSTICKVERTICAL.getValue());

	}
}

