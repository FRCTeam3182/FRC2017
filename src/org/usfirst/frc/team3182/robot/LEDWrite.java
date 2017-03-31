package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;



public class LEDWrite {
	private SPI spi;
	private DriverStation ds;
	public LEDWrite() {
		spi = new SPI(SPI.Port.kOnboardCS1);
		ds = DriverStation.getInstance();
	}

	public void climbLights() {
		byte[] message = {0, 0};
		spi.write(message, 2);
	}
	public void armLights() {
		byte[] message = {1, 0};
		spi.write(message, 2);
	}
	public void stationaryLights() {
		byte[] message = {2, 0};
		spi.write(message, 2);
	}
	public void fieldLights() {
		byte[] message = {3, 0};
		spi.write(message, 2);
	}
	public void drivingLights() {
		if (ds.getAlliance() == DriverStation.Alliance.Blue){
		byte[] message = {4, 0};
		spi.write(message, 2);	
		}
		if (ds.getAlliance() == DriverStation.Alliance.Red){
		byte[] message = {4, 0};
		spi.write(message, 2);	
		}
	}
}
