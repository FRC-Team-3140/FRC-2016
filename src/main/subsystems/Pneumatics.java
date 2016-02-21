package main.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;

/**
 *
 */
public class Pneumatics extends Subsystem implements Constants {

	/*****************
	 * INSTANCE DATA *
	 *****************/
	public static Pneumatics instance;
	private DoubleSolenoid shifter;
	private Compressor comp;

	/**
	 * Constructor
	 */
	private Pneumatics() {
		shifter = new DoubleSolenoid(1, SHIFTER_EXT, SHIFTER_RET);
		comp = new Compressor(1);
		comp.setClosedLoopControl(true);
		shifter.set(DoubleSolenoid.Value.kForward);
		shifter.set(DoubleSolenoid.Value.kOff);

	}

	/**
	 * Prevents the constructor from running more than once.
	 * 
	 * @return Instance - Pneumatic's instance.
	 */
	public static Pneumatics getInstance() {
		if (instance == null)
			instance = new Pneumatics();
		return instance;
	}
	
	/*******************
	 * COMMAND METHODS *
	 *******************/

	/**
	 * Shifts the gearbox from the different gears
	 * 
	 * @param v - Desired shifting value (Uses default shifting values)
	 */
	public void shift(DoubleSolenoid.Value v) {
		shifter.set(v);
	}

	/**
	 * Toggles the compressor (ON/OFF)
	 */
	public void toggleComp() {
		if (comp.enabled())
			comp.stop();
		else
			comp.start();
	}

	/*******************
	 * DEFAULT METHODS *
	 *******************/
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}
