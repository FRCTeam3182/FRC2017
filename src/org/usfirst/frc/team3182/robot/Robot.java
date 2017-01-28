package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team3182.robot.DriveControl;
import org.usfirst.frc.team3182.robot.DriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public SmartDashboard smartDashboard = new SmartDashboard();
	LiveWindow lw = new LiveWindow();
	DriveTrain drivetrain = new DriveTrain();
	//public static DriveControl driveControl;
	
	final String customGear = "Gear Auto";
	final String customLow = "High  Goal Auto";
	final String customHigh = "Low Goal Auto";
	DriveControl driveControl = new DriveControl();
	/**trueKorea means the competition bot, falseKorea is the demobot
	 * This is for the sendable chooser we are making that allows you to choose between bots.
	 */
	String trueKorea = "trueKorea";
	String falseKorea = "falseKorea";
	String robotConfigSelected;
	
	String autoSelected;	
	SendableChooser<String> chooser = new SendableChooser<>();
	SendableChooser<String> configChooser = new SendableChooser<>();


	
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
		/*drivetrain=new Drivetrain();
		 * driveControl=new DriveControl;
		 */
		
		/**
		 * FIXME: Uncomment when camera is installed
		server=CameraServer.getInstance();
		server.startAutomaticCapture();
		*/
		
		//chooser=new SendableChoooser();
		//chooser.addDefault("Default", new driveDistance(96));
		chooser.addObject("Gear Auto", customGear);
		chooser.addObject("High Goal Auto", customHigh);
		chooser.addObject("Low Goal Auto", customLow);
		
		SmartDashboard.putData("Auto choices", chooser);
		
		configChooser.addObject("falseKorea", falseKorea);
		configChooser.addDefault("default", trueKorea);
		
		SmartDashboard.putData("RobotChoice", configChooser);
		
		//runs the setBotConfig method in RobotConfig with the correct parameter
		RobotConfig.setBotConfig(configChooser.getSelected());
		
		
		
	}
	
	public void disabledInit() {
	}

	@Override
	public void autonomousInit() {
		//runs the setBotConfig method in RobotConfig with the correct parameter
		RobotConfig.setBotConfig(configChooser.getSelected());
		autoSelected = chooser.getSelected();
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
		//runs the setBotConfig method in RobotConfig with the correct parameter
		RobotConfig.setBotConfig(configChooser.getSelected());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		drivetrain.drive(.5, .5);
		smartDashboard.putNumber("LeftStickVal", driveControl.getL());
		smartDashboard.putNumber("RightStickVal", driveControl.getR());
	}
	
	/**
	 * This function is called when teleop begins.
	 */
	public void teleopInit() {
		//runs the setBotConfig method in RobotConfig with the correct parameter
		RobotConfig.setBotConfig(configChooser.getSelected());
	}
	
	/**
	 * This function is called during teleop mode
	 */
	public void teleopPeriodic() {
		drivetrain.drive(driveControl.getR(), driveControl.getL());
		smartDashboard.putNumber("LeftStickVal", driveControl.getL());
		smartDashboard.putNumber("RightStickVal", driveControl.getR());
	}
}

