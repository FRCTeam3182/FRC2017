package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTableReader {

	NetworkTable table;

	public void robotInit() {
		table = NetworkTable.getTable("dataTable");
	}

	//public void

	public NetworkTableReader() {
		// TODO Auto-generated constructor stub
	}

}
