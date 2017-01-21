package src.org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * 
 * @author Alex
 *
 */
public class Collector {
	
	private Talon upperMotor;
	private Talon lowerMotor;
	private Ultrasonic ultrasonic;
	private Encoder encoder;
	
	public Collector() {
		upperMotor = new Talon(2);
		lowerMotor = new Talon(3);
		ultrasonic = new Ultrasonic(0, 0);
		encoder = new Encoder(0, 0);
	}
	/**
	 * Activates conveyer belt.
	 * 
	 * @return Returns set speed.
	 */
	public void collect() {
		upperMotor.set(0.5);
		lowerMotor.set(0.5);
		upperMotor.getSpeed();
		lowerMotor.getSpeed();
	}
	/**
	 * Determines if the robot has a gear.
	 * 
	 * @return True id the robot has a motor.
	 */
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
	/**
	 * Determines if the motor is jammed.
	 * 
	 * @return True if the motor is jammed.
	 */
	public boolean detectMotorJammed() {
		//FIXME find encoder value for jammed motor
		if (upperMotor.getSpeed() != 0 && encoder.getStopped() == true) {
			return true;
		} else {
			return false;
		}
	}
	
}
