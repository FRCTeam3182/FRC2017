package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;

/**
 * @author Team3182
 *
 */
public class CameraServo {
	
	//Pan servo of camera mount
	Servo panP = new Servo(RobotConfig.servoP);
	Servo panN = new Servo(RobotConfig.servoP);
	
	//Tilt servo of camera mount
	Servo tiltP = new Servo(RobotConfig.servoT);
	Servo tiltN = new Servo(RobotConfig.servoT);
	
	//This is for when the joystick is moved
	Joystick move = new Joystick(RobotConfig.cameraControl);
	
	public void move() {
		/*
		 * Dividing by 2 and adding 0.5 converts the joystick input range of -1.0 through 1.0 to 0.0 through 1.0.
		 * For example -1 from the joystick becomes 0 when it is divided by 2 and has 0.5 added to it.
		 */
		panP.set(move.getStickPOV() / 2.0 + 0.5);
		panN.set(move.getStickPOV() / 2.0 + 0.5);
		
		
		tiltP.set(move.getY() / 2.0 + 0.5);
		tiltN.set(move.getY() / 2.0 + 0.5);
		
	}
	
	
	/**
	 * 
	 */
	public CameraServo() {
		// TODO Auto-generated constructor stub
	}

}
