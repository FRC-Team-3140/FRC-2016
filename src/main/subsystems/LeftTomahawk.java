package main.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;

/**
 *
 */
public class LeftTomahawk extends Subsystem implements Constants {
	
	public static LeftTomahawk instance;
	private static DoubleSolenoid actuator, actuator2;
	private static boolean left = true;

	private LeftTomahawk() {
		actuator = new DoubleSolenoid(1, 6, 7);;
	}
	
	public static LeftTomahawk getInstance() {
		if(instance == null)
			instance = new LeftTomahawk();
		return instance;
		
	}
	
	public void actuate() {
		if(left)
			actuator.set(EXT);
		else
			actuator.set(RET);
		left = !left;
	}

	
	public void off() {
		actuator.set(OFF);
	}

	
    public void initDefaultCommand() {
    	setDefaultCommand(null);
    }
}

