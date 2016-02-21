package main.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;

/**
 *
 */
public class Shooter extends Subsystem implements Constants {
	
	/*****************
	 * INSTANCE DATA *
	 *****************/
	private static Spark left, right;
	private static DoubleSolenoid launcher;
    public static Shooter instance;
    
    
    /**
     * Constructor
     */
	private Shooter() {
		left = new Spark(SHOOTER_LEFT);
		right = new Spark(SHOOTER_RIGHT);
		launcher = new DoubleSolenoid(1, LAUNCHER_EXT, LAUNCHER_RET);
	}
	
	/**
	 * Prevents the constructor from running more than once.
	 * 
	 * @return Instance - Shooter's instance.
	 */
	public static Shooter getInstance() {
		if(instance == null) {
			instance = new Shooter();
		}
			return instance;
	}
	
	/*******************
	 * COMMAND METHODS *
	 *******************/
	/**
	 * Spins the shooter's motor at a read speed
	 * 
	 * @param speed - desired speed (direction dictated by sign)
	 */
	public void spin(double speed) {
		left.set(speed);
		right.set(-speed);
	}
	
	public void launch(DoubleSolenoid.Value v) {
		launcher.set(v);
	}
	


    public void initDefaultCommand() {
    	setDefaultCommand(null);
    }
}

