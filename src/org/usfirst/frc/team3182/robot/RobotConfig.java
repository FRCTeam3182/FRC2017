package org.usfirst.frc.team3182.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class RobotConfig {
	
	/** All the possible configuration of the robot. */
	public enum Configs {
		/** The TestBot is the old roboRio. It's running on a drivetrain with diameter 4" wheels. */
		TestBot,
		
		/** The CompetitionBot is the old roboRio. It's running on a drivetrain with diameter 6" wheels. */
		CompetitionBot
	}
	
	public static Configs config;
	
	//Joystick inputs USB
	public static int joystickLChannel = 0;
	public static Joystick joystickL = new Joystick(joystickLChannel);
	public static int joystickRChannel = 1;
	public static Joystick joystickR = new Joystick(joystickRChannel);
	public static int powerGloveChannel = 2;
	//public static PowerGlove powerGlove = new PowerGlove(powerGloveChannel);
	
	public static int cameraJoystickChannel = 3;
	public static Joystick cameraJoystick = new Joystick(cameraJoystickChannel);
			
	// CAN Identifiers
	public static int canTalonRNumber = 0;
	public static int canTalonLNumber = 1;
	public static int canTalonRSlaveNumber = 2;
	public static int canTalonLSlaveNumber = 3;
	
	/** The right primary drivetrain CAN Talon. This will be NULL for the TestBot. */ 
	public static CANTalon canTalonR;
	
	/** The left primary drivetrain CAN Talon. This will be NULL for the TestBot. */
	public static CANTalon canTalonL;
	
	/** The right slave drivetrain CAN Talon. This will be NULL for the TestBot. */
	public static CANTalon canTalonRSlave;
	
	/** The left slave drivetrain CAN Talon. This will be NULL for the TestBot. */
	public static CANTalon canTalonLSlave;
	
	//PWM
	public static int winch = 0;
	public static Talon winchTalon = new Talon(winch);
	public static int upperMotor = 1;
	public static Talon upperMotorTalon = new Talon(upperMotor);
	public static int pwmTalonRNumber = 2;
	public static int pwmTalonLNumber = 3;
	public static int lowerMotor = 4;
	public static Talon lowerMotorTalon = new Talon(lowerMotor);
	public static int armMotor = 5;
	public static Talon armMotorTalon = new Talon(armMotor);
	public static int servoP = 6;
	public static Servo pan = new Servo(RobotConfig.servoP);
	public static int servoT = 7;
	public static Servo tilt = new Servo(RobotConfig.servoT);

	/** The right primary drivetrain PWM Talon. This will be NULL for the CompetitionBot. */ 
	public static Talon pwmTalonR;
	
	/** The left primary drivetrain PWM Talon. This will be NULL for the CompetitionBot. */
	public static Talon pwmTalonL;
	
	/*
	//Analog In
	public static int gyroscope = 0;
	public static int ultrasonicPing = 1;
	public static int ultrasonicEcho = 2;
	public static int potentiometer = 3;
	*/
	
	
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
	public static int laserChannel = 9; 
	public static Counter laserCounter = new Counter(laserChannel);
	
	/** The linear distance in inches traveled by the robot per pulse of the encoders */ 
	public static double distancePerPulse;
	
	public static RobotDrive robotDrive;
	
	public static void configureRobot(Configs robot) {
		config = robot;
		
		switch (robot) {
		case CompetitionBot:
			
			canTalonR = new CANTalon(canTalonRNumber);
			canTalonL = new CANTalon(canTalonLNumber);
			
			canTalonRSlave = new CANTalon(canTalonRSlaveNumber);
			canTalonLSlave = new CANTalon(canTalonLSlaveNumber);
			
			canTalonRSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
			canTalonLSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
			
			canTalonRSlave.set(canTalonR.getDeviceID());
			canTalonLSlave.set(canTalonL.getDeviceID());

			LiveWindow.addActuator("DriveTrain", "Right Pri. Talon", canTalonR);
			LiveWindow.addActuator("DriveTrain", "Right Sl. Talon",  canTalonRSlave);
			LiveWindow.addActuator("DriveTrain", "Left Pri. Talon",  canTalonL);
			LiveWindow.addActuator("DriveTrain", "Left Sl. Talon",   canTalonLSlave);
			
			//We may not be able to use this constructor when we have PID and follower can talons
			robotDrive = new RobotDrive(canTalonL, canTalonLSlave, canTalonR, canTalonRSlave);
			
			distancePerPulse = 0.013089969;
			
			break;
			
		case TestBot:
			pwmTalonR = new Talon(pwmTalonRNumber);
			pwmTalonL = new Talon(pwmTalonLNumber);
			
			robotDrive = new RobotDrive(pwmTalonL, pwmTalonR);
			
			distancePerPulse = 0.008726646;
			
			break;
			
		default:
			throw new IllegalArgumentException("Unknown robot configuration provided to the configureRobot() method.");
		}
	}


}
