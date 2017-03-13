package org.usfirst.frc.team3182.robot;

import java.util.Set;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTableReader {

	NetworkTable table;

	//public void

	public NetworkTableReader() {
		table = NetworkTable.getTable("CameraData");
		Set<String> keys = table.getKeys();
	}
	
	public void read() {
		double[] defaultValue = new double[1];
		defaultValue[0] = -1;
		
		double[] contours = table.getNumberArray("ContourPoints", defaultValue);
		double numOfContours = table.getNumber("NumOfContours", -1);
		double FrameTime = table.getNumber("FrameTime", -1);
		System.out.print("Contours: ");
		for (double contour : contours) {
			System.out.print(" ");
			System.out.print(contour);
			System.out.println();
		}
		System.out.println();
		System.out.print("numOfContours: ");
		System.out.println(numOfContours);
		System.out.print("FrameTime: ");
		System.out.println(FrameTime);
		
	}

}
