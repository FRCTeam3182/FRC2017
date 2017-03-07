package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;

/**
 * @author Team3182 
 *
 */
public class CameraServo {
		
	public void move() {
		/*
		 * Dividing by 2 and adding 0.5 converts the joystick input range of -1.0 through 1.0 to 0.0 through 1.0.
		 * For example -1 from the joystick becomes 0 when it is divided by 2 and has 0.5 added to it.
		 */  
		RobotConfig.pan.set(RobotConfig.cameraJoystick.getX() / 2.0 + 0.5);
		
		RobotConfig.tilt.set(RobotConfig.cameraJoystick.getY() / 2.0 + 0.5);
		
	}
	
	
	/**
	 * 
	 */
	public CameraServo() {
		// TODO Auto-generated constructor stub
	}

}
