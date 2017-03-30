package org.usfirst.frc.team3182.robot;


public class DriveControl {

	static enum ArmState{
		invalid,
		up,
		down,
		idle
	}
	
	static enum CollectState{
		invalid,
		in,
		out,
		idle
	}

	public DriveControl() {
		System.out.println("Drive Control Initialized");
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


	// What should the collector on the arm do?
	public CollectState collectCommand() {
		if      (RobotConfig.joystickApp.getRawButton(1)) {return CollectState.in;}
		else if (RobotConfig.joystickApp.getRawButton(3)) {return CollectState.out;}
		else                                              {return CollectState.idle;}
	}

	public ArmState armCommand() {
		if      (RobotConfig.joystickApp.getRawButton(6)) {return ArmState.down;}
		else if (RobotConfig.joystickApp.getRawButton(7)) {return ArmState.up;}
		else                                              {return ArmState.idle;}
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
