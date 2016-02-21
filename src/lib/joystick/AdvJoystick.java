package lib.joystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class AdvJoystick extends Joystick{
	
	/*****************
	 * INSTANCE DATA *
	 *****************/
	// Buttons
	public Button trigger;
	public Button thumb;
	public Button three;
	public Button four;
	public Button five;
	public Button six;

	
	/**
	 * @param port of the controller.
	 */
	public AdvJoystick(int port) {
		super(port);
		trigger = new JoystickButton(this, 1);
		thumb = new JoystickButton(this, 2);
		three = new JoystickButton(this, 3);
		four = new JoystickButton(this, 4);
		five = new JoystickButton(this, 5);
		six = new JoystickButton(this, 6);
	}
	
	/**
	 * Gets the X-axis of the left-thumbstick, and smoothens it.
	 * @return Cubed value of the X-axis.
	 */
	public double getMainX() {
		return Math.pow(super.getRawAxis(0), 3);
	}
	
	/**
	 * Gets the Y-axis of the left-thumbstick, and smoothens it.
	 * @return Cubed value of the Y-axis.
	 */
	public double getMainY() {
		return Math.pow(super.getRawAxis(1), 3);
	}
	

}
