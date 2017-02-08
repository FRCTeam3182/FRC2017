package org.usfirst.frc.team3182.robot;


public class RobotConfig {

	//Define robot names
	//DemoBot is now called falseKorea. It has wheels of diameter 4"
	//CompetitionBot is now called trueKorea. It has wheels of diameter 6"
	
	//Joystick inputs USB
		public static int driveControlL = 0;
		public static int driveControlR = 1;
		public static int powerGlove = 2;
		public static int cameraControl = 3;
				
		//Climber PWM 
		public static int driveWheelR = 2;
		public static int driveWheelL = 3;
		public static int winch = 0;
		public static int upperMotor = 1;
		public static int lowerMotor = 4;
		public static int armMotor = 5;
		public static int servoP = 6;
		public static int servoT = 7;
		
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
		// 1440 (p/r) * (1/2*pi*2) (r/in) = 114.591 (p/in) = 0.008726646 (in/p)
		public static double distancePerPulse = 0.008726646;
		
		public static void chooseDistancePerPulse(String string) {
			if(string == "trueKorea")	distancePerPulse = 0.013089969;
			else	distancePerPulse = 0.008726646;
		}
	
	


}
