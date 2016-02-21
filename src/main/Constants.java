package main;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.CANTalon.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public interface Constants {
	
	/*************
	 * VARIABLES *
	 *************/
	// THROTTLE MULTIPLIERS
	public final double DRIVE_THROTTLE = 0.4;
	public final double SHOOT_THROTTLE = 1.0;
	public final double INTAKE_THROTTLE = 0.7;
	public final double TURRET_THROTTLE_UP = 0.8;
	public final double TURRET_THROTTLE_DOWN = 0.5;
	public final double LIFTER_THROTTLE = 0.0;
	
	
	/*************
	 * CONSTANTS *
	 *************/
	// FOO
	public final String foo = "foo";
	// DEFAULT TALON MODES
	public final TalonControlMode DEFAULT_CTRL_MODE = TalonControlMode.PercentVbus;
	public final boolean DEFAULT_BRAKE_MODE = true;
	// TALON CONTROL MODES
	public final TalonControlMode PERCENT_VBUS_MODE = TalonControlMode.PercentVbus;
	public final TalonControlMode VOLTAGE_MODE = TalonControlMode.Voltage;
	public final TalonControlMode SLAVE_MODE = TalonControlMode.Follower;
	public final TalonControlMode DISABLED = TalonControlMode.Disabled;
	// TALON BRAKE MODES
	public final boolean BRAKE_MODE = true;
	public final boolean COAST_MODE = false;
	// PNEUMATIC STATES
	public final DoubleSolenoid.Value EXT = Value.kForward;
	public final DoubleSolenoid.Value RET = Value.kReverse;
	public final DoubleSolenoid.Value OFF = Value.kOff;
	
	/****************
	 * DEVICE PORTS *
	 ****************/
	// JOYSTICKS (USB)
	public final int JOYSTICK = 0;
	// TALON SRX'S (CAN BUS)
	public final int LEFT = 2;
	public final int LEFT_SLAVE = 3;
	public final int RIGHT = 4;
	public final int RIGHT_SLAVE = 5;
	// OTHER MOTOR CONTROLLERS (PWM)
	public final int SHOOTER_LEFT = 0;
	public final int SHOOTER_RIGHT = 1;
	public final int TURRET = 6;
			;
	public final int LIFTER = 3;
	// PNEUMATICS (PCM)
	public final int SHIFTER_EXT = 1;
	public final int SHIFTER_RET = 0;
	public final int LAUNCHER_EXT = 3;
	public final int LAUNCHER_RET = 2;
	public final int TOMAHAWKS_EXT = 4;
	public final int TOMAHAWKS_RET = 5;
	
	
}
