package lib.joystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController extends Joystick{
	
	/*****************
	 * INSTANCE DATA *
	 *****************/
	// Buttons
	public Button a;
	public Button b;
	public Button x;
	public Button y;
	public Button select;
	public Button start;
	// Thumb-stick buttons
	public Button leftJoystickButton;
	public Button rightJoystickButton;
	// Bumpers
	public Button leftBumper;
	public Button rightBumper;
	// Triggers
	public Button leftTrigger;
	public Button rightTrigger;

	
	/**
	 * @param port of the controller.
	 */
	public XboxController(int port) {
		super(port);
		a = new JoystickButton(this, 1);
		b = new JoystickButton(this, 2);
		x = new JoystickButton(this, 3);
		y = new JoystickButton(this, 4);
		leftBumper = new JoystickButton(this, 5);
		rightBumper = new JoystickButton(this, 6);
		select = new JoystickButton(this, 7);
		start = new JoystickButton(this, 8);
		leftJoystickButton = new JoystickButton(this, 9);
		rightJoystickButton = new JoystickButton(this, 10);
		leftTrigger = new AnalogButton(this, 2, 0.1);
		rightTrigger = new AnalogButton(this, 3, 0.1);
	}
	
	/**
	 * Gets the X-axis of the left-thumbstick, and smoothens it.
	 * @return Cubed value of the X-axis.
	 */
	public double getMainX() {
		return Math.pow(super.getRawAxis(0), 3) * -1;
	}
	
	/**
	 * Gets the Y-axis of the left-thumbstick, and smoothens it.
	 * @return Cubed value of the Y-axis.
	 */
	public double getMainY() {
		return Math.pow(super.getRawAxis(1), 5);
	}
	
	/**
	 * Gets the X-axis of the right thumbstick, and smoothens it.
	 * @return Cubed value of the alternate X-axis.
	 */
	public double getAltX() {
		return Math.pow(super.getRawAxis(4), 3) * -1;
	}
	
	/**
	 * Gets the Y-axis of the right thumbstick, and smoothens it.
	 * @return Cubed value of the alternate Y-axis.
	 */
	public double getAltY() {
		return Math.pow(super.getRawAxis(5), 3);
	}
	

}
