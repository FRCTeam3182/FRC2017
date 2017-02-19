package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Winch {

	Talon winchTalon = RobotConfig.winchTalon;
	
	public Winch() {
		
		LiveWindow.addActuator("Collector", "winch", winchTalon);
		
	}
	
	public void climb(boolean clockwise, double speed) {
		
		if(clockwise){
			winchTalon.set(speed);
		}
		else {
			winchTalon.set(-speed);
		}
	}
	
	public void climbStop() {
		winchTalon.set(0);
	}
}
