package org.usfirst.frc.team3182.robot;


public class RobotConfig {

	//Define robot names
	//DemoBot is now called falseKorea. It has wheels of diameter 4"
	//CompetitionBot is now called trueKorea. It has wheels of diameter 6"
	
	//Joystick inputs USB
		public static int driveControlL = 0;
		public static int driveControlR = 1;
		public static int powerGlove = 2;
				
		//Climber PWM
		public static int driveWheelR = 2;
		public static int driveWheelL = 3;
		public static int winch = 0;
		public static int upperMotor = 1;
		public static int lowerMotor = 4;
		public static int armMotor = 5;
		
		//Analog In
		public static int gyroscope = 0;
		public static int ultrasonicPing = 1;
		public static int ultrasonicEcho = 2;
		public static int potentiometer = 3;
		
		
		//DIO
		public static int encoderLA = 0;
		public static int encoderRA = 1;
		public static int encoderLB = 2;
		public static int encoderRB = 3;
		public static int encoderLowerA = 4;
		public static int encoderLowerB = 5;
		public static int encoderUpperA = 6;
		public static int encoderUpperB = 7;
		
		//Default distancePerPulse value, can be changed with the chooseDistancePerPulse method
		public static double distancePerPulse = 0.013089969;
		
		public static void chooseDistancePerPulse(String string) {
			if(string == "trueKorea")	distancePerPulse = 0.013089969;
			else	distancePerPulse = 0.008726646;
		}
	
	/**
	//Joystick inputs USB
	public static int driveControlL;
	public static int driveControlR;
	public static int powerGlove;
			
	//Climber PWM
	public static int winch;
	public static int driveWheelL;
	public static int driveWheelR;
	public static int upperMotor;
	public static int lowerMotor;
	
	//Analog In
	public static int gyroscope;
	public static int ultrasonicPing;
	public static int ultrasonicEcho;
	
	//DIO
	public static int encoderLA;
	public static int encoderRA;
	public static int encoderLB;
	public static int encoderRB;
	public static int encoderLowerA;
	public static int encoderLowerB;
	public static int encoderUpperA;
	public static int encoderUpperB;
	
	//Encoder set distance per pulse
	public static double distancePerPulse;
	
	//setBotConfig is set to the string "trueKorea" or "falseKorea" based on which bot is selected
	public static void setBotConfig(String string) {
		if (string == "trueKorea") {
			driveControlL = 0;
			driveControlR = 1;
			powerGlove = 2;
			
			driveWheelR = 0;
			driveWheelL = 1;
			winch = 2;
			upperMotor = 3;
			lowerMotor = 4;
			
			encoderLA = 0;
			encoderRA = 1;
			encoderLB = 2;
			encoderRB = 3;
			distancePerPulse = 0.013089969;
			encoderLowerA = 4;
			encoderLowerB = 5;
			encoderUpperA = 6;
			encoderUpperB = 7;
			
			gyroscope = 0;
			ultrasonicPing = 1;
			ultrasonicEcho = 2;
		}
		else if (string == "falseKorea") {
			driveControlL = 0;
			driveControlR = 1;
			powerGlove = 2;
			
			driveWheelR = 0;
			driveWheelL = 1;
			winch = 2;
			upperMotor = 3;
			lowerMotor = 4;
			
			encoderLA = 0;
			encoderRA = 1;
			encoderLB = 2;
			encoderRB = 3;
			distancePerPulse = 0.008726646;
			encoderLowerA = 4;
			encoderLowerB = 5;
			encoderUpperA = 6;
			encoderUpperB = 7;
			
			gyroscope = 0;
			ultrasonicPing = 1;
			ultrasonicEcho = 2;
			
		}
	}
	*/


}
