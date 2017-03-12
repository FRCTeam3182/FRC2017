package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.io.IOException;
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
	POVCamera povCamera;
	DriveTrain driveTrain;

	// Auto selector keys
	final String auto4s = "4sec";
	final String auto2s = "2sec";
	final String autoBackward = "autoBack";
	final String auto12ft = "12ft";
	final String autoGearDump = "autoGearDump";
	boolean isAutoBackwards = false;

	static enum AutoState{
		invalid,
		forward,
		deploy,
		reverse,
		stop,
	}

	// Test selector keys
	final String liveWindowKey = "lw";

	final String pidOnKey = "on";

	String autoSelected;
	String testSelected;
	String pidSelected;
	int currentTime;
	int targetTime;
	DriveControl driveControl;
	Collector collector;
	Winch winch;
	CameraServer server;
	int targetDistance;
	NetworkTableReader networkTableReader;

	// A couple state selectors for the robot
	SendableChooser<String> autoChooser = new SendableChooser<>();
	SendableChooser<String> testChooser = new SendableChooser<>();
	SendableChooser<String> pidChooser  = new SendableChooser<>();

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
		try {
			botType = Files.readAllLines(path).get(0);
			if (botType.equals("testBot")) {
				System.out.println("We set the variable botType to testBot!");
				RobotConfig.configureRobot(RobotConfig.Configs.TestBot);
			}
			else {
				System.out.println("WHAT?!");
				RobotConfig.configureRobot(RobotConfig.Configs.CompetitionBot);
			}
		}
		catch (IOException ex) {
			System.out.println("The variable botType does not have the correct value");
			RobotConfig.configureRobot(RobotConfig.Configs.CompetitionBot);
		}

		driveTrain = new DriveTrain();
		driveControl = new DriveControl();
		collector = new Collector();
		winch = new Winch();
		timer = new Timer();
		networkTableReader = new NetworkTableReader();

		// FIXME: It seems that when the camera is not connected, this cause
		// the robot to run really slowly
		//
		server = CameraServer.getInstance();
		server.startAutomaticCapture(0);
		server.startAutomaticCapture(1);

		autoChooser.addObject("Forward, 4 sec, .25 speed", auto4s);
		autoChooser.addObject("Forward, 2 sec, .25 speed", auto2s);
		autoChooser.addObject("Backward, 2 sec, .25 speed", autoBackward);
		autoChooser.addObject("Auto Gear Dump", autoGearDump);

		autoChooser.addObject("Forward 12 ft", auto12ft);
		autoChooser.addDefault("Do nothing", "");

		SmartDashboard.putData("Auto choices", autoChooser);

		testChooser.addObject("LiveWindow", liveWindowKey);
		testChooser.addDefault("Standard", "");

		SmartDashboard.putData("Test Modes", testChooser);

		pidChooser.addObject("Linear Drive", "");
		pidChooser.addDefault("PID Drive", pidOnKey);

		SmartDashboard.putData("Drive Modes", pidChooser);

		povCamera = new POVCamera();
	}

	@Override
	public void autonomousInit() {
		targetDistance = 0;
		targetTime = 0;
		autoSelected = autoChooser.getSelected();
		if (autoSelected.equals("4sec"))			targetTime = 5;
		else if (autoSelected.equals("2sec"))		targetTime = 2;
		else if (autoSelected.equals("auto12ft"))	targetDistance = 144;
		else if (autoSelected.equals("autoBack"))	{
			targetTime = 2;
			isAutoBackwards = true;
		}
		timer.reset();
		timer.start();
		RobotConfig.leftEncoder.reset();
		RobotConfig.rightEncoder.reset();
		pidSelected = pidChooser.getSelected();
		if (pidSelected.equals(pidOnKey)) {
			driveTrain.enablePID();
		}
		else {
			driveTrain.disablePID();
		}

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		if(autoSelected.equals(autoGearDump)) {
			if(timer.get()<2) driveTrain.drive(-.25, -.25);
			else if(timer.get()>2 && timer.get()<4)	{
				driveTrain.drive(0, 0);
				collector.armReverse();
			}
			
			else if(timer.get()>4 && timer.get()<8)	driveTrain.drive(.1, .1);
			else if(timer.get()>8)	driveTrain.drive(0, 0);
		}
		else {
			if(targetTime>0) {
				if(timer.get()<targetTime)
					if(isAutoBackwards)
						driveTrain.drive(-.25, -.25);
					else
						driveTrain.drive(.25, .25);
				else
					driveTrain.drive(0, 0);
			}
			else if(targetDistance>0){
				if(driveTrain.getLDistance()<targetDistance)	
					driveTrain.getLeftPIDController().setSetpoint(.5*driveTrain.maxSpeed_inPs);
				else
					driveTrain.getLeftPIDController().setSetpoint(0);
				if(driveTrain.getRDistance()<targetDistance)	
					driveTrain.getRightPIDController().setSetpoint(.5*driveTrain.maxSpeed_inPs);
				else
					driveTrain.getRightPIDController().setSetpoint(0);
			}
		}
	}

	/**
	 * This function is called when test is chosen. 
	 */
	public void testInit() {
		testSelected = testChooser.getSelected();
		if (testSelected.equals(liveWindowKey)) {
			LiveWindow.setEnabled(true);
		}
		else {
			LiveWindow.setEnabled(false);
			driveTrain.enablePID();
			SmartDashboard.putNumber("Left", 0);
			SmartDashboard.putNumber("Right", 0);
			SmartDashboard.putNumber("Upper Collector", 0);
			SmartDashboard.putNumber("Left Encoder Rate", RobotConfig.leftEncoder.getRate());
			SmartDashboard.putNumber("Right Encoder Rate", RobotConfig.rightEncoder.getRate());
			SmartDashboard.putNumber("Left Encoder Distance", RobotConfig.leftEncoder.getDistance());
			SmartDashboard.putNumber("Right Encoder  Distance", RobotConfig.rightEncoder.getDistance());
			driveTrain.drive(0, 0);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		if (testSelected.equals(liveWindowKey)) {
			LiveWindow.run();
		}
		else {
			SmartDashboard.putNumber("Left Encoder Rate", RobotConfig.leftEncoder.getRate());
			SmartDashboard.putNumber("Right Encoder Rate", RobotConfig.rightEncoder.getRate());
			SmartDashboard.putNumber("Left Encoder Distance", RobotConfig.leftEncoder.getDistance());
			SmartDashboard.putNumber("Right Encoder  Distance", RobotConfig.rightEncoder.getDistance());
			SmartDashboard.putNumber("Potentiometer", RobotConfig.analogPot.get());
			driveTrain.drive(SmartDashboard.getNumber("Left", 0), SmartDashboard.getNumber("Right", 0));
			
			collector.upperMotorTalon.set(SmartDashboard.getNumber("Upper Collector", 0));
			
			povCamera.dpadMove();

			// FIXME: I wasn't able to test this on day 0. I couldn't
			// start the server on the PI without an HDMI monitor!
			//networkTableReader.read();

			// The opto works! The issue was with the wiring on the sensor. [PB, 2017-03-10] 
			//System.out.println(collector.laserCounter.get());
		}
	}

	public void teleopInit() {
		pidSelected = pidChooser.getSelected();
		if (pidSelected.equals(pidOnKey)) {
			driveTrain.enablePID();
		}
		else {
			driveTrain.disablePID();
		}
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

		if (RobotConfig.joystickApp.getRawButton(4)) {
			winch.climb(false, 1);
		} else if (RobotConfig.joystickApp.getRawButton(5)) {
			winch.climb(true, 1);
		} else {
			winch.climbStop();
		}

		if (RobotConfig.joystickR.getRawButton(9)) {
			if(driveTrain.pidEnabled)
				driveTrain.disablePID();
			else
				driveTrain.enablePID();
		}
		RobotConfig.upperMotorTalon.set(RobotConfig.joystickApp.getThrottle());

		povCamera.dpadMove();
		if (RobotConfig.joystickR.getRawButton(5)) {
			povCamera.center();
		}
		if (RobotConfig.joystickApp.getRawButton(7))	RobotConfig.upperMotorTalon.set(-1);
		if(RobotConfig.joystickR.getRawButton(1))	driveTrain.toggleSpeed();
	}
}