package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/*
import org.usfirst.frc.team3182.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3182.robot.commands.TimedDrive;
import org.usfirst.frc.team3182.robot.commands.TimedTurn;
import org.usfirst.frc.team3182.robot.commands.TimedVariableDrive;
 */

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */



public class Robot extends IterativeRobot {
	
	//public static Drivetrain drivetrain;
	//public static DriveControl driveControl;
	
	final String customGear = "Gear Auto";
	final String customLow = "High  Goal Auto";
	final String customHigh = "Low Goal Auto";
	
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	public static boolean usesPowerGlove = true;
	
	Command autonomousCommand;
	
	
	public static CameraServer server;
	
	DriverStation ds;
	//private double warningTime=0;
	
	@Override
	public void robotInit() {
		/*drivetrain=new Drivetrain();
		 * driveControl=new DriveControl;
		 */
		
		
		server=CameraServer.getInstance();
		server.startAutomaticCapture();
		
		//chooser=new SendableChoooser();
		//chooser.addDefault("Default", new driveDistance(96));
		chooser.addObject("Gear Auto", customGear);
		chooser.addObject("High Goal Auto", customHigh);
		chooser.addObject("Low Goal Auto", customLow);
		
		SmartDashboard.putData("Auto choices", chooser);
		
		
	}
	
	public void disabledInit() {
		//driveControl=new DriveControl();
	}

	
	@Override
	public void autonomousInit() {
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
		}
		switch (autoSelected) {
		case customHigh:
			// Put custom auto code here
			break;
		}
		switch (autoSelected) {
		case customLow:
			// Put custom auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

