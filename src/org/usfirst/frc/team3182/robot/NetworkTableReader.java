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
		double[] contours = table.getNumberArray("ContourPoints", defaultValue);
		System.out.print("Contours: ");
		for (double contour : contours) {
			System.out.print(contours + " ");
		}
		System.out.println();
	}

}
