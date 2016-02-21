package main.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;

/**
 *
 */
public class RightTomahawk extends Subsystem implements Constants {
	
	public static RightTomahawk instance;
	private static DoubleSolenoid actuator, actuator2;
	private static boolean left = true;

	private RightTomahawk() {
		actuator = new DoubleSolenoid(1, 4, 5);;
	}
	
	public static RightTomahawk getInstance() {
		if(instance == null)
			instance = new RightTomahawk();
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

