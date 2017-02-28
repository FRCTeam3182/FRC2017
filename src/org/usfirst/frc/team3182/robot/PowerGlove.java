package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class PowerGlove extends Joystick {
	
	
	//These buttons are properties of any powerglove and will be getted and setted from within this class
	private JoystickButton pgButton1 = new JoystickButton(this, 1);
    private JoystickButton pgButton2 = new JoystickButton(this, 2);
    private JoystickButton pgButton3 = new JoystickButton(this, 3);
    private JoystickButton pgButton4 = new JoystickButton(this, 4);
    
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
	public PowerGlove(int channel) {
		super(channel);
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
	
	/**
	 * This method is used for getting the value of a button on the joystick
	 * @param joystickButton (the name of the button pgButton1...
	 * @return boolean value for the correct joystickButton
	 */
	public boolean getPowerButton(JoystickButton joystickButton) {
		return joystickButton.get();
	}
	
	
}
