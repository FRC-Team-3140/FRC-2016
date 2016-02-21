package main.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.Constants;
import main.OI;
import main.Robot;
import main.commands.turret.Pivot;

/**
 *
 */
public class Turret extends Subsystem implements Constants {

	private static CANTalon winch;
	public DigitalInput upper;
	public DigitalInput lower;
	private static DoubleSolenoid launcher;
	public static Turret instance;

	// String str = new String("Hello");
	// String str = "Hello";
	/**
	 * Constructor
	 */
	private Turret() {
		winch = new CANTalon(TURRET);
		winch.changeControlMode(PERCENT_VBUS_MODE);
		winch.enableBrakeMode(BRAKE_MODE);
		lower = new DigitalInput(0);
		upper = new DigitalInput(1);
		// winch.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	}

	/**
	 * Prevents the constructor from running more than once.
	 * 
	 * @return Instance - Drivetrain's instance.
	 */
	public static Turret getInstance() {
		if (instance == null) {
			instance = new Turret();
		}
		return instance;
	}

	/**
	 * Pivots the turret to change elevation
	 * 
	 * @param speed
	 *            - the desired speed (direction dictated by sign)
	 */
	public void pivot() {
		double speed;
		double throttle;
		
		if (Robot.mode && (((OI.getJoy().getMainY() > 0.1) || (OI.getJoy().getMainY() < -0.1))))
			speed = OI.getJoy().getMainY() * -1;
		else
			speed = OI.getXbox().getAltY();


		if (speed >= 0)
			throttle = TURRET_THROTTLE_UP;
		else
			throttle = TURRET_THROTTLE_DOWN;

		if (speed > 0.0 && !lower.get())
			winch.set(0);
		else if (speed < 0.0 && !upper.get())
			winch.set(0);
		else
			winch.set(speed * throttle);

		// SmartDashboard.putNumber("Throttle", throttle);
		//SmartDashboard.putBoolean("Upper", lower.get());
		// SmartDashboard.putBoolean("Lower", upper.get());
		// SmartDashboard.putNumber("Turret", winch.getEncPosition());
	}

	public void pivotTo(double d) {
		winch.set(d);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new Pivot());
	}


}
