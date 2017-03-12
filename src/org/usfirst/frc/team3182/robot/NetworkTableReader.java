package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTableReader {

	NetworkTable table;

	//public void

	public NetworkTableReader() {
		table = NetworkTable.getTable("CameraData");
		// TODO Auto-generated constructor stub
	}
	
	public void read() {
		double[] defaultValue = new double[0];
		defaultValue[0] = -1;
		
		double[] contours = table.getNumberArray("ContourPoints", defaultValue);
		double numOfContours = table.getNumber("NumOfContours", -1);
		double frameTime = table.getNumber("FrameTime", -1);
		System.out.print("Contours: ");
		for (double contour : contours) {
			System.out.print(contours + " ");
		}
		System.out.println();
		System.out.print("numOfContours: ");
		System.out.println(numOfContours);
		System.out.print("frameTime: ");
		System.out.println(frameTime);
		
	}

}
