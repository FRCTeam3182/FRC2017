package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;


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
	private Joystick joystickR;

	public DriveControl() {
		System.out.println("Drive Control Initialized");
		timer=new Timer();
		armDuration= 2;
		state=ArmState.up;
		joystickR = RobotConfig.joystickR; 
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
	

	public double getArcadeMove() {
		//Inverses the joystick output to make forward positive
		return -RobotConfig.joystickR.getY();
	}
	

	public double getArcadeRotate() {
		//Inverses the joystick output to make forward positive
		return -RobotConfig.joystickR.getX();
	}


	//Collect getter
	public boolean collectCommand() {
		if(RobotConfig.joystickApp.getRawButton(1))
			return true;
		else
			return false;
	}

	//Reverse collect getter
	public boolean collectCommandReverse() {
		if(RobotConfig.joystickApp.getRawButton(3))
			return true;
		else
			return false;
	}

	public ArmState armCommand() {
		switch(state){
		case movingUp:
			if(RobotConfig.analogPot.getAverageVoltage()>.010){
				state=ArmState.up; 

				//0.11255739966796875
				//0.056033128722656256
			}
			break;
		case movingDown:
			if(RobotConfig.analogPot.getAverageVoltage()<.058){
				state=ArmState.down; 
			}
			break;
		case up:

			if(RobotConfig.joystickR.getRawButton(2)){
				state=ArmState.movingDown;
			}
			break;
		case down:

			if(RobotConfig.joystickR.getRawButton(2)){
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

	public double pidArcadeL() {
		// Forward direction
		if (getArcadeMove() > 0) {
			// Right Rotate
			if (getArcadeRotate() > 0) {
				
				if (getArcadeMove() > getArcadeRotate()) {
					return getArcadeRotate();
				}
				else {
					return getArcadeMove();
				}}
			// Left Rotate
			else {
				if(getArcadeMove() > -getArcadeRotate()) {
					return getArcadeMove()+getArcadeRotate();
				}
				else {
					return getArcadeMove()+getArcadeRotate();
				}
			}
		}
		
		// Reverse direction
		else {
			if(getArcadeRotate() < 0) {
				if(getArcadeMove()>getArcadeRotate()) {
					return getArcadeRotate();
				}
				else {
					return getArcadeMove();
				}}
			else {
				if (getArcadeMove()<getArcadeRotate())
					return getArcadeMove()+getArcadeRotate();
				else {
					return getArcadeMove()+getArcadeRotate();
				}
			}
		}
	}

	public double pidArcadeR() {
		if (getArcadeMove()>0){
			if (getArcadeRotate()>0) {
				if (getArcadeMove()>getArcadeRotate()) {
					return getArcadeMove() - getArcadeRotate();
				}
				else {
					return getArcadeMove() - getArcadeRotate();
				}}
			else {
				if(getArcadeMove() > -getArcadeRotate()) {
					return getArcadeMove();
				}
				else {
					return -getArcadeRotate();
				}
			}
		}
		else {
			if(getArcadeRotate()<0) {
				if(getArcadeMove()>getArcadeRotate()) {
					return getArcadeMove() - getArcadeRotate();
				}
				else {
					return getArcadeMove() - getArcadeRotate();
				}}
			else {
				if (getArcadeMove()<getArcadeRotate())
					return getArcadeMove();
				else {
					return getArcadeRotate();
				}
			}
		}
	}

	


}
