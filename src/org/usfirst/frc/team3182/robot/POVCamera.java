package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;


/**
 * @author Alex
 */



public class POVCamera {
	
	//Pan servo of camera mount
		//Servo pan = new Servo(RobotConfig.servoP);
		
		//Tilt servo of camera mount
		//Servo tilt = new Servo(RobotConfig.servoT);
	
		
		
		/* 
		 * Using driveControlR for dpad
		 * driveControlR is the joystick with the dpad(knob on top)
		 */
Joystick stick = new Joystick(RobotConfig.joystickRChannel);


//Control
public void dpadMove() {
	
	if (stick.getPOV() == 0){
		RobotConfig.tilt.set(0.7);
		}
	//up
	
	if (stick.getPOV() == 45){
		RobotConfig.tilt.set(0.65);
		RobotConfig.pan.set(0.65);
		}
	//up right
	
	if (stick.getPOV() == 90){
		RobotConfig.pan.set(0.7);
		}
	//right
	
	if (stick.getPOV() == 135){
		RobotConfig.tilt.set(0.35);
		RobotConfig.pan.set(0.65);
		}
	//down right
	
	if (stick.getPOV() == 180){
		RobotConfig.tilt.set(0.3);
		}
	//down
	
	if (stick.getPOV() == 225){
		RobotConfig.tilt.set(0.35);
		RobotConfig.pan.set(0.35);
		}
	//down left
	
	if (stick.getPOV() == 270){
		RobotConfig.pan.set(0.3);
		}
	//left
	
	if (stick.getPOV() == 315){
		RobotConfig.tilt.set(0.65);
		RobotConfig.pan.set(0.35);
		}
	//up left
	
	}
	


	public POVCamera() {
		// TODO 
	}

}
