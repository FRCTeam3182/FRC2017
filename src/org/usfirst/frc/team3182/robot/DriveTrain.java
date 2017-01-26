package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

/** 
 * Class for basic driving functions including basic drive and drive to distance
 * 
 * @author Andrew Oh
 * 
 */
public class DriveTrain {
	private RobotDrive drive;
	private Encoder encoder;
	private Talon leftController, rightController;
	
	/**
	 * DriveTrain constructor, creates a DriveTrain
	 */
	public DriveTrain(){
		leftController = new Talon(IODef.encoderL);
		rightController = new Talon(IODef.encoderR);
		drive = new RobotDrive(leftController, rightController);
		encoder = new Encoder(IODef.encoderL, IODef.encoderR);
		// FIXME: Find out the actual value for setdistanceperpulse! 
		encoder.setDistancePerPulse(12.00);
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
	 * Method for making robot drive to a distance, takes in distance and makes robot drive at half speed to that distancce
	 * @param inches
	 */
	public void driveDistance(double inches){
		encoder.reset();
		while(encoder.getDistance()<inches){
			drive.setLeftRightMotorOutputs(.5,.5);
		}
		drive.stopMotor();
	}
	
}
