package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;


/**
 * @author Alex
 */



public class POVCamera {
	
	//Pan servo of camera mount
		Servo pan = new Servo(RobotConfig.servoP);
		
		//Tilt servo of camera mount
		Servo tilt = new Servo(RobotConfig.servoT);
	
		
		
		/* 
		 * Using driveControlR for dpad
		 * driveControlR is the joystick with the dpad(knob on top)
		 */
Joystick stick = new Joystick(RobotConfig.joystickRChannel);


//Control
public void dpadMove() {
	
	if (stick.getPOV() == 0){
		tilt.set(tilt.get() + 0.05);
		}
	//up
	
	if (stick.getPOV() == 45){
		tilt.set(tilt.get() + 0.05);
		pan.set(pan.get() + 0.05);
		}
	//up right
	
	if (stick.getPOV() == 90){
			pan.set(pan.get() + 0.05);
		}
	//right
	
	if (stick.getPOV() == 135){
				tilt.set(tilt.get() - 0.05);
				pan.set(pan.get() + 0.05);
		}
	//down right
	
	if (stick.getPOV() == 180){
		tilt.set(tilt.get() - 0.05);
		}
	//down
	
	if (stick.getPOV() == 225){
		tilt.set(tilt.get() - 0.05);
		pan.set(pan.get() - 0.05);
		}
	//down left
	
	if (stick.getPOV() == 270){
		pan.set(pan.get() - 0.05);
		}
	//left
	
	if (stick.getPOV() == 315){
		tilt.set(tilt.get() + 0.05);
		pan.set(pan.get() - 0.05);
		}
	//up left
	
	}
	


	public POVCamera() {
		// TODO 
	}

}
