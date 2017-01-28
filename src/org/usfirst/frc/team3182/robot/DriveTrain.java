package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

/** 
 * Class for basic driving functions including basic drive and drive to distance
 * 
 * @author Andrew Oh
 * 
 */
public class DriveTrain {
	private RobotDrive drive;
	private Encoder leftEncoder, rightEncoder;
	private Talon leftController, rightController;
	
	/**
	 * DriveTrain constructor, creates a DriveTrain
	 */
	public DriveTrain(){
		leftController = new Talon(RobotConfig.driveWheelL);
		rightController = new Talon(RobotConfig.driveWheelR);
		drive = new RobotDrive(leftController, rightController);
		leftController.setInverted(true);
		rightController.setInverted(true);
		leftEncoder = new Encoder(RobotConfig.encoderLA, RobotConfig.encoderLB);
		rightEncoder = new Encoder(RobotConfig.encoderRA, RobotConfig.encoderRB);
		//This takes the value for distancePerPulse from the RobotConfig class
		leftEncoder.setDistancePerPulse(RobotConfig.distancePerPulse);
		rightEncoder.setDistancePerPulse(RobotConfig.distancePerPulse);
		
	}
	
	/**
	 * Very basic method for making the robot move, takes in two speed doubles and sets motor speed to them
	 * @param left
	 * @param right
	 */
	public void drive(double left, double right){
		drive.setLeftRightMotorOutputs(left, right);
	}
	
	/**
	 * Method for making robot drive to a distance, takes in distance and makes robot drive at half speed to that distance
	 * @param inches
	 * FIXME: use the setDistancePerPulse() method to be able to accurately calculate distance
	 */
	public void driveDistance(double inches){
		leftEncoder.reset();
		rightEncoder.reset();
		while(leftEncoder.getDistance()<inches && rightEncoder.getDistance()<inches){
			drive.setLeftRightMotorOutputs(.5,.5);
			
		}
		drive.stopMotor();
	}

	public Talon getLeftController() {
		return leftController;
	}
	
	public Talon getRightController() {
		return rightController;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}
	
	public Encoder getLeftEncoder() {
		return leftEncoder;
	}
	
	public double getLDistance() {
		return leftEncoder.getDistance();
	}
	
	public double getRDistance() {
		return rightEncoder.getDistance();
	}
}
