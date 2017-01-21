package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Joystick;

public class PowerGlove extends Joystick {
	
	
	public enum FingerPos {
		UP, MIDDLE, DOWN
	}
	
	/**
	 * We still have to figure out which buttons do what, and that is in the arduino code for the power glove. This
	 * can easily be changed later.
	 * @return
	 */
	
	public FingerPos getFinger() {
		if(this.getRawButton(1)&&this.getRawButton(2))
			return FingerPos.UP;
		else if (this.getRawButton(1))
			return FingerPos.MIDDLE;
		else
			return FingerPos.DOWN;
		
	}
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
