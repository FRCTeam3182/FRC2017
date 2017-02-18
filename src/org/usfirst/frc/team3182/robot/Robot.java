package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.usfirst.frc.team3182.robot.DriveControl;
import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.RobotConfig;
import org.usfirst.frc.team3182.robot.Collector;


/** 
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	DriveTrain driveTrain;
	
	final String customGear = "Gear Auto";
	final String customLow = "High  Goal Auto";
	final String customHigh = "Low Goal Auto";
	String autoSelected;	
	DriveControl driveControl;
	Collector collector;
	/**trueKorea means the competition bot, falseKorea is the demobot
	 * This is for the sendable autoChooser we are making that allows you to choose between bots.
	 */
	String trueKorea = "trueKorea";
	String falseKorea = "falseKorea";
	String robotConfigSelected;
	
	//These are variables used in the distanceChooser sendable chooser
	String distanceA = "distanceA";
	String distanceB = "distanceB";
	String distanceC = "distanceC";
	String distanceD = "distanceD";

	SendableChooser<String> autoChooser = new SendableChooser<>();
	SendableChooser<String> configChooser = new SendableChooser<>();
	SendableChooser<String> distanceChooser = new SendableChooser<>();



	CameraServo cameraServo;
	
	public static boolean usesPowerGlove = true;
	
	Command autonomousCommand;
	
	public static CameraServer server;
	
	DriverStation ds;
	//private double warningTime=0;
  
	/**        
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */ 

	@Override
	public void robotInit() {

		//We started trying to read the contents of the botType.txt
		//file from the roboRIO.
		Path path = Paths.get("/home/lvuser/botType.txt");
		String botType; 
		try{
			botType = Files.readAllLines(path).get(0);
			if(botType.equals("testBot")){
				System.out.println("We set the variable botType to testBot!");
				RobotConfig.configureRobot(RobotConfig.Configs.TestBot);
			}
			else{
				System.out.println("WHAT?!");
			}
		}
		catch(IOException ex){
			System.out.println("The variable botType does not have the correct value");
			RobotConfig.configureRobot(RobotConfig.Configs.CompetitionBot);
		}
		
		
		driveTrain = new DriveTrain();
		driveControl = new DriveControl();
		cameraServo = new CameraServo();
		
		server = CameraServer.getInstance();
		server.startAutomaticCapture();
		
		//autoChooser=new SendableChoooser();
		//autoChooser.addDefault("Default", new driveDistance(96));
		/**autoChooser.addObject("Gear Auto", customGear);
		autoChooser.addObject("High Goal Auto", customHigh);
		autoChooser.addObject("Low Goal Auto", customLow);
		
		SmartDashboard.putData("Auto choices", autoChooser);
		
		configChooser.addObject("falseKorea", falseKorea);
		configChooser.addDefault("default", trueKorea);
		
		SmartDashboard.putData("RobotChoice", configChooser);
		
		distanceChooser.addDefault("Drive 0ft", distanceA);
		distanceChooser.addObject("Drive 2ft", distanceB);
		distanceChooser.addObject("Drive 50% Power", distanceC);
		distanceChooser.addObject("Joystick Drive", distanceD);
		
		SmartDashboard.putData("Distancechoice", distanceChooser);
			
		//runs the chooseDistancePerPulse method in RobotConfig with the correct parameter
		RobotConfig.chooseDistancePerPulse(configChooser.getSelected());
		*/
		
		//LiveWindow.addActuator("DriveTrain", "left motor", driveTrain.getLeftController());
		//LiveWindow.addActuator("DriveTrain", "right motor", RobotConfig.TalonR1);
		//LiveWindow.addActuator("Encoders", "left encoder", RobotConfig.leftEncoder);
		//LiveWindow.addActuator("Encoders", "right encoder", RobotConfig.rightEncoder);
		//LiveWindow.addActuator("PID", "Left Drivetrain", driveTrain.getLeftPIDController());
		//LiveWindow.addActuator("PID", "Right Drivetrain", driveTrain.getRightPIDController());

	}
	
	public void disabledInit() {
	}

	@Override
	public void autonomousInit() {

		//runs the chooseDistancePerPulse method in RobotConfig with the correct parameter
		autoSelected = autoChooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		if (autonomousCommand !=null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customGear:
			// Put custom auto code here
			break;
		case customHigh:
			// Put custom auto code here
			break;
		case customLow:
			// Put custom auto code here
			break;
		}
	}

	/**
	 * This function is called when test is chosen. 
	 */
	public void testInit() {
		//String driveType = distanceChooser.getSelected();
		//runs the chooseDistancePerPulse method in RobotConfig with the correct parameter
		//RobotConfig.chooseDistancePerPulse(configChooser.getSelected());

		
		/*SmartDashboard.putData("left motor", driveTrain.getLeftController());
		SmartDashboard.putData("right motor", driveTrain.getRightController());
		SmartDashboard.putData("left encoder", driveTrain.getLeftEncoder());
		SmartDashboard.putData("right encoder", driveTrain.getRightEncoder());
		

		if(driveType == "distanceB") {
			driveTrain.driveDistance(24);
		}
		else if(driveType == "distanceC")
			driveTrain.drive(.5, .5);
		else if(driveType == "distanceD")
			driveTrain.drive(driveControl.getL(), driveControl.getR());
		*/
		
		//cameraServo.move();
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	/**
	 * This function is called when teleop begins.
	 */
	public void teleopInit() {
	}
	
	/**
	 * This function is called during teleop mode
	 */
	public void teleopPeriodic() {
		driveTrain.drive(driveControl.getR(), driveControl.getL());
		SmartDashboard.putNumber("LeftStickVal", driveControl.getL());
		SmartDashboard.putNumber("RightStickVal", driveControl.getR());
		if(RobotConfig.joystickL.getRawButton(1)==true){
			collector.collect();
		}
		else{
		}
		if(RobotConfig.joystickR.getRawButton(1)==true){
			collector.collectReverse();
		}
		else{
		}
		if(RobotConfig.joystickL.getRawButton(2)==true){
			RobotConfig.armMotorTalon.set(.3);
		}
		else{
		}
		if(RobotConfig.joystickR.getRawButton(2)==true){
			RobotConfig.armMotorTalon.set(-.3);
		}
		else{
		}
		if(RobotConfig.joystickR.getRawButton(3)==true){
			RobotConfig.winchTalon.set(.1);
		}
		else{
		}
		if(RobotConfig.joystickR.getRawButton(3)==true){
			RobotConfig.winchTalon.set(-.1);
		}
		else{
		}
	}
}

