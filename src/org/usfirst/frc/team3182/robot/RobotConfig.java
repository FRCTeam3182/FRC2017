package org.usfirst.frc.team3182.robot;


public class RobotConfig {

	//Define robot names
	//DemoBot is now called falseKorea. It has wheels of diameter 4"
	//CompetitionBot is now called trueKorea. It has wheels of diameter 6"
	
	
	//Joystick inputs
	public static int driveControlL;
	public static int driveControlR;
	public static int powerGlove;
	public static int cameraControl;
			
	//Climber
	public static int winch;
			
	//Outputs
	public static int driveWheelL;
	public static int driveWheelR;
	public static int encoderL;
	public static int encoderR;
	public static int servoP;
	public static int servoT;
	
	//Encoder set distance per pulse
	public static double distancePerPulse;
	
	//setBotConfig is set to the string "trueKorea"(Competition bot) or "falseKorea"(Demo bot) based on which bot is selected
	public static void setBotConfig(String string) {
		if (string == "trueKorea") {
			//USB
			driveControlL = 0;
			driveControlR = 1;
			powerGlove = 2;
			cameraControl = 3;
			
			//PWM
			winch = 0;
			driveWheelL = 1;
			driveWheelR = 0;
			servoP = 2;
			servoT = 3;
			
			//DIO
			encoderL = 2;
			encoderR = 3;		
			distancePerPulse = 0.013089969;
		}
		else if (string == "falseKorea") {
			//USB
			driveControlL = 0;
			driveControlR = 1;
			powerGlove = 2;
			cameraControl = 3;
			
			//PWM
			winch = 0;
			driveWheelL = 1;
			driveWheelR = 0;
			servoP = 2;
			servoT = 3;
			
			//DIO
			encoderL = 2;
			encoderR = 3;
			distancePerPulse = 0.008726646;
		}
	}


}
