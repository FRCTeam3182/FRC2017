package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;

/** 
 * Class for basic driving functions including basic drive and drive to distance
 * 
 * @author Andrew Oh
 * 
 */
public class DriveTrain {
	private PIDController leftPIDController, rightPIDController;
	private RobotDrive drive;
	
	
	/**
	 * DriveTrain constructor, creates a DriveTrain
	 */
	public DriveTrain(){
		// Set this class's RobotDrive to the same from the RobotConfig
		drive = RobotConfig.robotDrive;
		
		// This takes the value for distancePerPulse from the RobotConfig class
		RobotConfig.leftEncoder.setDistancePerPulse(RobotConfig.distancePerPulse);
		RobotConfig.rightEncoder.setDistancePerPulse(RobotConfig.distancePerPulse);
		
		// Set the gains for the CompetitionBot
		double Kp, Ki, Kd, Kf;

		// Depending on the Robot's configuration...
		switch (RobotConfig.config) {
		
		// If we are the CompetitionBot, use the CAN-based Talons 
		case CompetitionBot:

			// Set the gains for the CompetitionBot
			Kp = 0;
			Ki = 0;
			Kd = 0;
			Kf = 1;
			
			// Initialize the PID controllers to use the CAN Talons
			leftPIDController  = new PIDController(Kp, Ki, Kd, Kf, RobotConfig.leftEncoder, RobotConfig.canTalonL);
			rightPIDController = new PIDController(Kp, Ki, Kd, Kf, RobotConfig.rightEncoder, RobotConfig.canTalonR);
			break;

		// If we are the CompetitionBot, use the PWM-based Talons
		case TestBot:
			
			// Set the gains for the TestBot
			Kp = 0;
			Ki = 0;
			Kd = 0;
			Kf = 1;
			
			// Initialize the PID controllers to use the PWM Talons
			leftPIDController  = new PIDController(Kp, Ki, Kd, Kf, RobotConfig.leftEncoder, RobotConfig.pwmTalonL);
			rightPIDController = new PIDController(Kp, Ki, Kd, Kf, RobotConfig.rightEncoder, RobotConfig.pwmTalonR);
			break;
			
		default:
			throw new IllegalArgumentException("Unknown RobotConfig provided to the DriveTrain");
		}
		
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
