package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveControl {

	//Initialize joystic map
	Joystick driveControlR = new Joystick(IODef.driveControlR);
	Joystick driveControlL = new Joystick(IODef.driveControlL);		

	

    // Init the power glove as a joystick and a few buttons from it as well
    Joystick powerGlove = new Joystick(IODef.powerGlove);
    JoystickButton pgButton1 = new JoystickButton(powerGlove, 1);
    JoystickButton pgButton2 = new JoystickButton(powerGlove, 2);
    JoystickButton pgButton3 = new JoystickButton(powerGlove, 3);
    JoystickButton pgButton4 = new JoystickButton(powerGlove, 4);

			
	public DriveControl() {
		
		System.out.println("Drive Control Initialized");
		
		Robot.usesPowerGlove = true; //Implement this in Robot class
		System.out.println("PowerGlove used");
		
		
	}
	
	public double getL() {
		if(Math.abs(driveControlL.getY())<.15) return 0;
		return driveControlL.getY();
		
	}
	public double getR() {
		if(Math.abs(driveControlR.getY())<.15) return 0;
		return driveControlR.getY();
	
		
	}
	
	public double getLExp() { //"ramps up"
    	if(Math.abs(getL())<.1)return 0; // Deadzone
    	if (getL() > 0) return Math.pow(getL(), 2);
        else return -Math.abs(Math.pow(getL(), 2));
    }
	
	public double getRExp() {
    	if(Math.abs(getR())<.1) return 0; // Deadzone
        if (getR() > 0) return Math.pow(getR(), 2);
        else return -Math.abs(Math.pow(getR(), 2));
    }
	
	
	public double getPowerGloveRoll() {
		return powerGlove.getY();
		
	}
	

}
