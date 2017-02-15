package org.usfirst.frc.team3182.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
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
	private PIDController leftPIDController, rightPIDController;
	
	
	/**
	 * DriveTrain constructor, creates a DriveTrain
	 */
	public DriveTrain(){
		RobotConfig.CANTalonRSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		RobotConfig.CANTalonRSlave.set(RobotConfig.CANTalonR.getDeviceID());
		RobotConfig.CANTalonLSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		RobotConfig.CANTalonLSlave.set(RobotConfig.CANTalonL.getDeviceID());
		//We are not sure whether the 1 talons are on the front or if the 2 talons are on the front.
		drive = new RobotDrive(RobotConfig.CANTalonL,RobotConfig.CANTalonR);
		
	
		//This takes the value for distancePerPulse from the RobotConfig class
		RobotConfig.leftEncoder.setDistancePerPulse(RobotConfig.distancePerPulse);
		RobotConfig.rightEncoder.setDistancePerPulse(RobotConfig.distancePerPulse);
		
		//FIXME: Fix the PID so that it can output to two different talons per encoder.
		leftPIDController = new PIDController(0, 0, 0, 1, RobotConfig.leftEncoder, RobotConfig.CANTalonL);
		rightPIDController = new PIDController(0, 0, 0, 1, RobotConfig.rightEncoder, RobotConfig.CANTalonR);
		leftPIDController.enable();
		rightPIDController.enable();
	}
	
	/**
	 * Very basic method for making the robot move, takes in two speed doubles and sets motor speed to them
	 * @param left value for left wheels, from -1 to 1
	 * @param right  value for right wheels, from -1 to 1
	 */
	public void drive(double left, double right){
		leftPIDController.setSetpoint(left);
		rightPIDController.setSetpoint(right);
	}
	
	/**
	 * Method for making robot drive to a distance, takes in distance and makes robot drive at half speed to that distance
	 * @param inches
	 * FIXME: use the setDistancePerPulse() method to be able to accurately calculate distance
	 */
	public void driveDistance(double inches){
		RobotConfig.leftEncoder.reset();
		RobotConfig.rightEncoder.reset();
		while(getLDistance()<inches && getRDistance()<inches){
			drive.setLeftRightMotorOutputs(.5,.5);
			
		}
		drive.stopMotor();
	}
	
	public double getLDistance() {
		return RobotConfig.leftEncoder.getDistance();
	}
	
	public double getRDistance() {
		return RobotConfig.rightEncoder.getDistance();
	}

	

/*
	/**
	 * @return the leftPIDController
	 *
	public PIDController getLeftPIDController() {
		return leftPIDController;
	}

	/**
	 * @return the rightPIDController
	 *
	public PIDController getRightPIDController() {
		return rightPIDController;
	}
	*/
}
