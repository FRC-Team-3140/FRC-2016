package main;

import lib.joystick.AdvJoystick;
import lib.joystick.XboxController;
//import main.commands.SwitchCamera;
import main.commands.groups.DeployLeft;
import main.commands.groups.DeployRight;
import main.commands.groups.IntakeBall;
import main.commands.groups.ShootBall;
import main.commands.pneumatics.ShiftDown;
import main.commands.pneumatics.ShiftUp;
import main.commands.pneumatics.ToggleComp;
import main.commands.shooter.Stop;
import main.commands.turret.PivotDown;
import main.commands.turret.PivotUp;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements Constants {
	private static OI instance;

	private static XboxController xbox;
	private static AdvJoystick joy;

	public OI() {
		xbox = new XboxController(JOYSTICK);
		joy = new AdvJoystick(1);
		check();

	}

	public static OI getInstance() {
		if (instance == null) {
			instance = new OI();
		}
		return instance;
	}

	public static XboxController getXbox() {
		return xbox;
	}

	public static AdvJoystick getJoy() {
		return joy;
	}

	private void check() {
		xbox.start.whenPressed(new ToggleComp());
		//xbox.select.whenPressed(new SwitchCamera());
		// Bumpers
		xbox.leftBumper.whenPressed(new IntakeBall());
		xbox.leftBumper.whenReleased(new Stop());
		xbox.rightBumper.whenPressed(new ShootBall());
		xbox.a.whenPressed(new PivotDown(0.3));
		xbox.y.whenPressed(new PivotUp(0.3));
		joy.trigger.whenPressed(new ShootBall());
		joy.thumb.whenPressed(new IntakeBall());
		joy.thumb.whenReleased(new Stop());
		joy.three.whenPressed(new PivotDown(0.25));
		// joy.five.whenPressed(new PivotUp(0.2));
		// JoystickButton
		xbox.leftJoystickButton.whenPressed(new ShiftUp());
		xbox.leftJoystickButton.whenReleased(new ShiftDown());
		xbox.rightTrigger.whenPressed(new DeployRight());
		xbox.leftTrigger.whenPressed(new DeployLeft());
		// Analog thumbstick(s)

	}
}
