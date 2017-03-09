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
	
	//private Ultrasonic ultrasonic;
	
	Talon lowerMotorTalon = RobotConfig.lowerMotorTalon;
	Talon upperMotorTalon = RobotConfig.upperMotorTalon;
	Talon armMotorTalon = RobotConfig.armMotorTalon;
	Encoder lowerEncoder = RobotConfig.lowerEncoder;
	Encoder upperEncoder = RobotConfig.upperEncoder;
	Counter laserCounter = RobotConfig.laserCounter;

	
	public Collector() {
		
		//ultrasonic = new Ultrasonic(RobotConfig.ultrasonicPing, RobotConfig.ultrasonicEcho);

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
		upperMotorTalon.set(-0.5);
		lowerMotorTalon.set(-1.0);
	}
	
	public void collectReverse() {
		upperMotorTalon.set(0.3);
		lowerMotorTalon.set(1.0);
	}

	public void armStop() {
		armMotorTalon.set(0);
	}
	
	public void arm() {
		armMotorTalon.set(.2);
		
	}

	public void armReverse() {
		armMotorTalon.set(-.2);
	}
	
	/**
	 * Determines if the robot has a gear.
	 * 
	 * @return True id the robot has a motor.
	 */
	/*
	public boolean detectGear() {
		//FIXME find ultrasonic value for gear
		double distanceToGear = 0;
		ultrasonic.ping();
		if (ultrasonic.getRangeInches() == distanceToGear) {
			return(true);
		} else {
			return(false);
		}
	}
	*/
	
	/**
	 * Determines if the motor is jammed.
	 * 
	 * @return True if the motor is jammed.
	 */
	/*
	public boolean detectMotorJammed() {
		//FIXME find encoder value for jammed motor
		if (upperMotor.getSpeed() != 0 && upperEncoder.getStopped() == true) {
			return true;
		} else {
			return false;
		}
	}
	*/
	
}
