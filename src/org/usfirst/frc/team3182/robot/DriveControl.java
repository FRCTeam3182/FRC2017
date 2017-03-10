package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Timer;


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
		timer=new Timer();
		armDuration= 2;
		state=ArmState.up;
	}
	
	/**
	 * The standard get left power method
	 */
	public double getL() {
		//Inverses the joystick output to make forward positive
		return -RobotConfig.joystickL.getY();
		
	}
	/**
	 * The standard get right power method
	 */
	public double getR() {
		//Inverses the joystick output to make forward positive
		return -RobotConfig.joystickR.getY();
	}
	
	//Collect getter
	public boolean collectCommand() {
		if(RobotConfig.joystickL.getRawButton(1) && !RobotConfig.joystickR.getRawButton(1))
			return true;
		else
			return false;
	}
	
	//Reverse collect getter
	public boolean collectCommandReverse() {
		if(RobotConfig.joystickR.getRawButton(1) && !RobotConfig.joystickL.getRawButton(1))
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
				timer.start();
				state=ArmState.movingDown;
			}
			break;
		case down:
			if(RobotConfig.joystickR.getRawButton(2)){
				timer.reset();
				timer.start();
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
    	if (getL() > 0) return Math.pow(getL(), 2);
        else return -Math.abs(Math.pow(getL(), 2));
    }
	/**
	 * This method returns the position of the right joystick with a square rule
	 * 
	 * @return square rule of getY() for right joystick
	 */
	public double getRExp() {
        if (getR() > 0) return Math.pow(getR(), 2);
        else return -Math.abs(Math.pow(getR(), 2));
    }
	

}
