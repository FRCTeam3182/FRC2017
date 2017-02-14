package org.usfirst.frc.team3182.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;

public class RobotConfig {

	//Define robot names
	//DemoBot is now called falseKorea. It has wheels of diameter 4"
	//CompetitionBot is now called trueKorea. It has wheels of diameter 6"
	
	//Joystick inputs USB
		public static int joystickLChannel = 0;
		public static Joystick joystickL = new Joystick(joystickLChannel);
		public static int joystickRChannel = 1;
		public static Joystick joystickR = new Joystick(joystickRChannel);
		public static int powerGloveChannel = 2;
		public static PowerGlove powerGlove = new PowerGlove(powerGloveChannel);
		
		public static int cameraJoystickChannel = 3;
		public static Joystick cameraJoystick = new Joystick(cameraJoystickChannel);
				
		//PWM 
		public static int talonR1Number = 0;
		public static CANTalon CANTalonR1 = new CANTalon(talonR1Number);
		public static int talonL1Number = 1;
		public static CANTalon CANTalonL1 = new CANTalon(talonL1Number);
		public static int talonR2Number = 2;
		public static CANTalon CANTalonR2 = new CANTalon(talonR2Number);
		public static int talonL2Number = 3;
		public static CANTalon CANTalonL2 = new CANTalon(talonL2Number);
		public static int winch = 0;
		public static Talon winchTalon = new Talon(winch);
		public static int upperMotor = 1;
		public static Talon upperMotorTalon = new Talon(upperMotor);
		public static int lowerMotor = 4;
		public static Talon lowerMotorTalon = new Talon(lowerMotor);
		public static int armMotor = 5;
		public static Talon armMotorTalon = new Talon(armMotor);
		public static int servoP = 6;
		public static Servo pan = new Servo(RobotConfig.servoP);
		public static int servoT = 7;
		public static Servo tilt = new Servo(RobotConfig.servoT);
		
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
		public static Encoder leftEncoder = new Encoder(encoderLA, encoderLB, true);
		public static Encoder rightEncoder = new Encoder(encoderRA, encoderRB);
		public static int encoderLowerA = 4;
		public static int encoderLowerB = 5;
		public static int encoderUpperA = 6;
		public static int encoderUpperB = 7;
		public static Encoder lowerEncoder = new Encoder(encoderLowerA, encoderLowerB);
		public static Encoder upperEncoder = new Encoder(encoderUpperA, encoderUpperB);
		
		//Default distancePerPulse value, can be changed with the chooseDistancePerPulse method
		public static double distancePerPulse = 0.013089969;
		
		public static void chooseDistancePerPulse(String string) {
			if(string == "trueKorea")	distancePerPulse = 0.013089969;
			else	distancePerPulse = 0.008726646;
		}
	
	


}
