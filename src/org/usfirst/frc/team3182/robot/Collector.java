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
	
	public void collect() {
		upperMotor.set(0.5);
		lowerMotor.set(0.5);
		upperMotor.getSpeed();
		lowerMotor.getSpeed();
	}
	public boolean detectGear() {
		//FIXME find ultrasonic value for gear
		ultrasonic.ping();
		ultrasonic.getRangeInches();
		return false;
	}
	public boolean detectMotorJammed() {
		//FIXME find encoder value for jammed motor
		encoder.getStopped();
		return false;
	}
	
}
