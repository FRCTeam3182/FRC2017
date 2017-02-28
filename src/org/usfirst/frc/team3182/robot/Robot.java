package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
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
	
	final String auto4S = "4sec";
	final String auto2S = "2sec";
	String autoSelected;	
	int currentTime;
	int targetTime;
	DriveControl driveControl;
	Collector collector;
	Winch winch;
	int targetDistance;
	/**trueKorea means the competition bot, falseKorea is the demobot
	 * This is for the sendable autoChooser we are making that allows you to choose between bots.
	 */
	
	
	

	SendableChooser<String> autoChooser = new SendableChooser<>();

	//CameraServo cameraServo;
	
	//public static boolean usesPowerGlove = true;
	
	//public static CameraServer server;
	
	Timer timer;
  
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
				RobotConfig.configureRobot(RobotConfig.Configs.CompetitionBot);
			}
		}
		catch(IOException ex){
			System.out.println("The variable botType does not have the correct value");
			RobotConfig.configureRobot(RobotConfig.Configs.CompetitionBot);
		}
		
		
		driveTrain = new DriveTrain();
		driveControl = new DriveControl();
		//cameraServo = new CameraServo();
		collector = new Collector();
		winch = new Winch();
		timer = new Timer();
		targetDistance = 72;
		
		//server = CameraServer.getInstance();
		//server.startAutomaticCapture();
		
		
		autoChooser.addDefault("Do nothing", null);
		autoChooser.addObject("Forward, 4 sec, .25 speed", auto4S);
		autoChooser.addObject("Forward, 2 sec, .25 speed", auto2S);
		
		SmartDashboard.putData("Auto choices", autoChooser);
		
	}
	
	public void disabledInit() {
	}

	@Override
	public void autonomousInit() {

		autoSelected = autoChooser.getSelected();
		if(autoSelected==null)	targetTime = 0;
		else if (autoSelected.equals("4sec"))	targetTime = 4;
		else if (autoSelected.equals("2sec"))	targetTime = 2;
		else targetTime = 0;
		timer.reset();
		timer.start();
		RobotConfig.leftEncoder.reset();
		RobotConfig.rightEncoder.reset();			
			

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

		if(driveTrain.getLDistance()<targetDistance)	
			driveTrain.getLeftPIDController().setSetpoint(.25*driveTrain.maxSpeed_inPs);
		else
			driveTrain.getLeftPIDController().setSetpoint(0);
		if(driveTrain.getRDistance()<targetDistance)	
			driveTrain.getRightPIDController().setSetpoint(.25*driveTrain.maxSpeed_inPs);
		else
			driveTrain.getRightPIDController().setSetpoint(0);
	}

	/**
	 * This function is called when test is chosen. 
	 */
	public void testInit() {
		LiveWindow.setEnabled(false);
		driveTrain.enablePID();
		SmartDashboard.putNumber("Left", 0);
		SmartDashboard.putNumber("Right", 0);
		SmartDashboard.putNumber("Left Encoder Rate", RobotConfig.leftEncoder.getRate());
		SmartDashboard.putNumber("Right Encoder Rate", RobotConfig.rightEncoder.getRate());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		//LiveWindow.run();
		SmartDashboard.putNumber("Left Encoder Rate", RobotConfig.leftEncoder.getRate());
		SmartDashboard.putNumber("Right Encoder Rate", RobotConfig.rightEncoder.getRate());
		driveTrain.drive(SmartDashboard.getNumber("Left", 0), SmartDashboard.getNumber("Right", 0));
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

		driveTrain.drive(driveControl.getLExp(), driveControl.getRExp());

		if (driveControl.collectCommand()) {
			collector.collect();
		} else if (driveControl.collectCommandReverse()) {
			collector.collectReverse();
		} else {
			collector.collectStop();
		}

		if (driveControl.armCommand()==DriveControl.ArmState.movingDown) {
			collector.arm();
		} else if (driveControl.armCommand()==DriveControl.ArmState.movingUp) {
			collector.armReverse();
		} else {
			collector.armStop();
		}
		
		if (RobotConfig.joystickR.getRawButton(3)) {
			//adding 1 and dividing by 2 makes the scale from 0 to 1 instead of -1 to 1
			winch.climb(false, driveControl.getClimbSpeed());
		} else if (RobotConfig.joystickR.getRawButton(4)) {
			winch.climb(true, driveControl.getClimbSpeed());
		} else {
			winch.climbStop();
		}
		
		if (RobotConfig.joystickR.getRawButton(7)) {
			if(driveTrain.pidEnabled)
				driveTrain.disablePID();
			else
				driveTrain.enablePID();
		}
	}
}

