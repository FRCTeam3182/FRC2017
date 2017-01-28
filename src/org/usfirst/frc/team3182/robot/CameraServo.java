/**
 * 
 */
package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;

/**
 * @author Team3182
 *
 */
public class CameraServo {
	
	
	Servo pan = new Servo(RobotConfig.servoP);
		
	Servo tilt = new Servo(RobotConfig.servoT);
	
	Joystick move = new Joystick(RobotConfig.cameraControl);
	
	public void move() {
		
		pan.set(move.getX() / 2.0 + 0.5);
		
		tilt.set(move.getY() / 2.0 + 0.5);
		
	}
	
	
	/**
	 * 
	 */
	public CameraServo() {
		// TODO Auto-generated constructor stub
	}

}
