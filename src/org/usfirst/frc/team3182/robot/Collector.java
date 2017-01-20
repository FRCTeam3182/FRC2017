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
		
		
	}
	public boolean detectGear() {
		
	}
	public boolean detectMotorJammed() {
		
	}
	
}
