package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;


/**
 * @author Alex
 */


public class POVCamera {

	//Pan servo of camera mount
	Servo pan = RobotConfig.pan;

	//Tilt servo of camera mount
	Servo tilt = RobotConfig.tilt;





	/* 
	 * Using driveControlR for dpad
	 * driveControlR is the joystick with the dpad(knob on top)
	 */
	Joystick stick = new Joystick(RobotConfig.joystickRChannel);

	public POVCamera() {
		tilt.set(0.5);
		pan.set(0.5);
	}

	//Control
	public void dpadMove() {

		if (stick.getPOV() == 0){

			tilt.set(tilt.get() + 0.01);

		}
		//up

		if (stick.getPOV() == 45){

			tilt.set(tilt.get() + 0.01);
			pan.set(pan.get() + 0.01);

		}
		//up right

		if (stick.getPOV() == 90){

			pan.set(pan.get() + 0.01);

		}
		//right

		if (stick.getPOV() == 135){

			tilt.set(tilt.get() - 0.01);
			pan.set(pan.get() + 0.01);

		}
		//down right

		if (stick.getPOV() == 180){

			tilt.set(tilt.get() - 0.01);

		}
		//down

		if (stick.getPOV() == 225){

			tilt.set(tilt.get() - 0.01);
			pan.set(pan.get() - 0.01);

		}
		//down left

		if (stick.getPOV() == 270){

			pan.set(pan.get() - 0.01);

		}
		//left

		if (stick.getPOV() == 315){

			tilt.set(tilt.get() + 0.01);
			pan.set(pan.get() - 0.01);

		}
		//up left

		if (stick.getPOV() == -1) {
			tilt.set(0.5);
			pan.set(0.5);
		}
		
		//when let go recenter camera

		if (tilt.get() < 0) {
			tilt.set(0.5);
		}
		//limit tilt value lower than 0

		if (tilt.get() > 1) {
			tilt.set(0.5);
		}
		//limit tilt value above 1 

		if (pan.get() < 0) {
			pan.set(0.5);
		}
		//limit pan value lower than 0

		if (pan.get() > 1) {
			pan.set(0.5);
		}

	}



}
