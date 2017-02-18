package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * 
 * @author Alex
 *
 */
public class Collector {
	
	private Ultrasonic ultrasonic;
	private Encoder upperEncoder;
	private Encoder lowerEncoder;
	
	public Collector() {
		
		//ultrasonic = new Ultrasonic(RobotConfig.ultrasonicPing, RobotConfig.ultrasonicEcho);
		//upperEncoder = new Encoder(RobotConfig.encoderUpperA, RobotConfig.encoderUpperB);
		//lowerEncoder = new Encoder(RobotConfig.encoderLowerA, RobotConfig.encoderLowerB);

	}
	/**
	 * Activates conveyer belt.
	 * 
	 * @return Returns set speed.
	 */
	public void collect() {
		RobotConfig.upperMotorTalon.set(0.5);
		RobotConfig.lowerMotorTalon.set(0.9);
	}
	
	public void collectReverse() {
		RobotConfig.upperMotorTalon.set(-0.5);
		RobotConfig.lowerMotorTalon.set(-0.5);
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
