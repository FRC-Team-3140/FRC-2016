package main.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.Constants;
import main.OI;
import main.Robot;
import main.commands.drivetrain.Drive;

/**
 *
 */
public class Drivetrain extends Subsystem implements Constants {

	/*****************
	 * INSTANCE DATA *
	 *****************/
	private static CANTalon left, leftSlave, right, rightSlave;
	public static Drivetrain instance;
	//public static boolean isHighGear = false;

	/**
	 * Constructor
	 */
	private Drivetrain() {
		left = new CANTalon(LEFT);
		leftSlave = new CANTalon(LEFT_SLAVE);
		right = new CANTalon(RIGHT);
		rightSlave = new CANTalon(RIGHT_SLAVE);
		left.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		right.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		setTalonDefaults();
		//shifter.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * Prevents the constructor from running more than once.
	 * 
	 * @return Instance - Drivetrain's instance.
	 */
	public static Drivetrain getInstance() {
		if (instance == null) {
			instance = new Drivetrain();
		}
		return instance;
	}

	/*******************
	 * COMMAND METHODS *
	 *******************/

	/**
	 * Drives the robot using <i>arcade drive</i>
	 */
	public void drive() {
		double x, y, z, l, r;

		x = OI.getXbox().getMainX();
		y = OI.getXbox().getMainY();
		z = OI.getJoy().getMainX();
		if(Robot.mode && ((OI.getJoy().getMainX() > 0.1) || (OI.getJoy().getMainX() < -0.1))){
			l = OI.getJoy().getMainX();
			left.set(l * DRIVE_THROTTLE);
			right.set(l * DRIVE_THROTTLE);
		}else{
			l = ((x * 0.85) + (y * 0.7)) * -1;
			r = ((x * 0.85) - (y * 0.7)) * -1;
			left.set(l);
			right.set(r);
		}

		
		//SmartDashboard.putNumber("left", left.getEncVelocity());
		//SmartDashboard.putNumber("right", right.getEncVelocity());

	}

	/*******************
	 * SUPPORT METHODS *
	 *******************/
	
	/**
	 * Reverses the output of the Talon SRX's
	 * 
	 * @param output - Whether the output should be reversed.
	 */
	private void reverseTalons(boolean output) {
		left.reverseOutput(output);
		right.reverseOutput(output);
	}

	/**
	 * Sets the Talon SRX's brake mode
	 * 
	 * @param brake - Sets the brake mode (Uses default brake modes)
	 */
	private void setBrakeMode(Boolean brake) {
		left.enableBrakeMode(brake);
		leftSlave.enableBrakeMode(brake);
		right.enableBrakeMode(brake);
		rightSlave.enableBrakeMode(brake);
	}

	/**
	 * Sets the Talon SRX's control mode
	 * 
	 * @param mode - Sets the control mode (Uses default control modes)
	 */
	private void setCtrlMode(TalonControlMode mode) {
		left.changeControlMode(mode);
		leftSlave.changeControlMode(SLAVE_MODE);
		leftSlave.set(left.getDeviceID());
		// (arc sin(8020))/1 #killyourself
		right.changeControlMode(mode);
		rightSlave.changeControlMode(SLAVE_MODE);
		rightSlave.set(right.getDeviceID());
	}

	/**
	 * Sets the Talon SRX's defaults (reversing, brake and control modes)
	 */
	private void setTalonDefaults() {
		reverseTalons(true);
		setBrakeMode(true);
		setCtrlMode(DEFAULT_CTRL_MODE);
	}

	/********************
	 * DEFAULT COMMANDS *
	 ********************/

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
}
