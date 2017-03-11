package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Encoder;


import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * 
 * @author Alex
 *
 */
public class Collector {
	
	Talon lowerMotorTalon = RobotConfig.lowerMotorTalon;
	Talon upperMotorTalon = RobotConfig.upperMotorTalon;
	Talon armMotorTalon = RobotConfig.armMotorTalon;
	Encoder lowerEncoder = RobotConfig.lowerEncoder;
	Encoder upperEncoder = RobotConfig.upperEncoder;
	Counter laserCounter = RobotConfig.laserCounter;

	
	public Collector() {
		LiveWindow.addActuator("Collector", "lower motor", lowerMotorTalon);
		LiveWindow.addActuator("Collector", "upper motor",  upperMotorTalon);
		LiveWindow.addActuator("Collector", "arm",  armMotorTalon);
		LiveWindow.addSensor("Collector", "laser", laserCounter);
	}
	
	/**
	 * Activates conveyer belt.
	 * 
	 * @return Returns set speed.
	 */
	public void collectStop() {
		upperMotorTalon.set(0);
		lowerMotorTalon.set(0);
	}
	
	public void collect() {
		upperMotorTalon.set(-0.8);
		lowerMotorTalon.set(-1.0);
	}
	
	public void collectReverse() {
		upperMotorTalon.set(0.8);
		lowerMotorTalon.set(1.0);
	}

	public void armStop() {
		armMotorTalon.set(0);
	}
	
	public void arm() {
		armMotorTalon.set(.3);	
	}

	public void armReverse() {
		armMotorTalon.set(-.2);
	}
	
	/**
	 * Determines if the robot has a gear.
	 * 
	 * @return True if the robot has a motor.
	 */
	
	public boolean detectGear() {
		return false;
		// FIXME: Update this code to use the opto sensor
		// FIXME: Remove the return above when this function is implemented
		
		/* 
		double distanceToGear = 0;
		ultrasonic.ping();
		if (ultrasonic.getRangeInches() == distanceToGear) {
			return(true);
		} else {
			return(false);
		}*/
	}
	
	/**
	 * Determines if the motor is jammed.
	 * 
	 * @return True if the motor is jammed.
	 */
	public boolean detectMotorJammed() {
		return false;
		// FIXME: Connect the encoder from the upper motor to roboRio
		// FIXME: Update this code to use the encoder to check for a stuck gear
		// FIXME: Remove the return above when this function is implemented
		
		/*
		if (upperMotor.getSpeed() != 0 && upperEncoder.getStopped() == true) {
			return true;
		} else {
			return false;
		}
		*/
	}
}
