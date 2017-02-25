package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveControl {
			
	static enum ArmState{
		invalid,
		down,
		up,
		movingDown,
		movingUp,
	}
	
	private Timer timer;
	private double armDuration;
	private ArmState state;
	
	public DriveControl() {
		
		System.out.println("Drive Control Initialized");
		//this tells Robot that the robot is using power glove
		//Robot.usesPowerGlove = true; //Implement this in Robot class
		System.out.println("PowerGlove used");
		timer=new Timer();
		armDuration= 2;
		state=ArmState.up;
	}
	
	/**
	 * The standard get left power method, linear with .15 deadzone
	 */
	public double getL() {
		if(Math.abs(RobotConfig.joystickL.getY())<.15) return 0;
		//double temp = driveControlL.getY();
		//temp = -temp;
		//Inverses the joystick output to make forward positive
		return -RobotConfig.joystickL.getY();
		
	}
	/**
	 * The standard get right power method, linear with .15 deadzone
	 */
	public double getR() {
		if(Math.abs(RobotConfig.joystickR.getY())<.15) return 0;
		//double temp = driveControlR.getY();
		//temp = -temp;
		//Inverses the joystick output to make forward positive
		return -RobotConfig.joystickR.getY();
	}
	
	//Collect getter
	public boolean collectCommand() {
		if((RobotConfig.joystickL.getRawButton(1)==true) && RobotConfig.joystickR.getRawButton(1)==false)
			return true;
		else
			return false;
	}
	
	//Reverse collect getter
	public boolean collectCommandReverse() {
		if((RobotConfig.joystickR.getRawButton(1)==true) && RobotConfig.joystickL.getRawButton(1)==false)
			return true;
		else
			return false;
	}
	
	public ArmState armCommand() {
		switch(state){
		case movingUp:
			if(timer.get()>armDuration){
				state=ArmState.up; 
			}
			break;
		case movingDown:
			if(timer.get()>armDuration){
				state=ArmState.down; 
			}
			break;
		case up:
			if(RobotConfig.joystickR.getRawButton(2)){
				timer.reset();
				state=ArmState.movingDown;
			}
			break;
		case down:
			if(RobotConfig.joystickR.getRawButton(2)){
				timer.reset();
				state=ArmState.movingUp;
			}
			break;
		case invalid:
			System.out.println("Oops! Something went wrong...");
			break;
		default:
			break;
		}
		return state;
	}
	
	/**
	 * This method returns the position of the left joystick with a square rule
	 * 
	 * @return square rule of getY() for left joystick
	 */
	public double getLExp() { //"ramps up"
    	if(Math.abs(getL())<.1)return 0; // Deadzone
    	if (getL() > 0) return Math.pow(getL(), 2);
        else return -Math.abs(Math.pow(getL(), 2));
    }
	/**
	 * This method returns the position of the right joystick with a square rule
	 * 
	 * @return square rule of getY() for right joystick
	 */
	public double getRExp() {
    	if(Math.abs(getR())<.1) return 0; // Deadzone
        if (getR() > 0) return Math.pow(getR(), 2);
        else return -Math.abs(Math.pow(getR(), 2));
    }
	
	/**
	 * The roll of the powerglove is the motion that you would make to steer a car
	 * @return the Y value of the power glove
	 */
	public double getPowerGloveRoll() {
		return RobotConfig.powerGlove.getRoll();
		
	}
	
	//Converts the throttle from 1 to -1 to 1 to 0. -1 is top, 1 is bottom.
	public double getClimbSpeed() {
		return (1.0+RobotConfig.joystickL.getThrottle())/2.0;
	}
	

}
