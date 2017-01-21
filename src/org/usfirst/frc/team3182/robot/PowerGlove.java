package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Joystick;

public class PowerGlove extends Joystick {
	
	
	/**This enumerates the different possible finger positions of the power glove
	 * NOPOS is the same as a null value, in which the powerglove did not return any buttons pressed.
	 * @author RACE
	 *
	 */
	public enum FingerPos {
		UP, MIDDLE, DOWN, NOPOS
	}
	
	/**
	 * We still have to figure out which buttons do what, and that is in the arduino code for the power glove. This
	 * can easily be changed later.
	 * 
	 * This method gives the enum value for the finger position of the power glove. If no button is pressed, it returns null.
	
	 * @return
	 */
	public FingerPos getFinger() {
		if(this.getRawButton(1)&&this.getRawButton(2))
			return FingerPos.UP;
		else if (this.getRawButton(1))
			return FingerPos.MIDDLE;
		else if (this.getRawButton(2))
			return FingerPos.DOWN;
		else
			return FingerPos.NOPOS;
		
	}
	
	//This constructor doesn't need parameters because it only calls the Joystick constructor and that is given the USB #
	public PowerGlove() {
		super(IODef.powerGlove);
	}
	
	public double getRoll() {
		//I am not sure if y is the correct axis
		return this.getY();
	}

	public double getPitch() {
		//I also don't now if this is the correct axis
		return this.getX();
	}
	
	public double getYaw() {
		//Once again, not sure about the axis
		return this.getZ();
	}
	
	
}
