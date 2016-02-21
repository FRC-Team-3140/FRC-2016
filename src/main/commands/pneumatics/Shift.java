
package main.commands.pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import main.Constants;
import main.Robot;

/**
 *
 */
public class Shift extends Command implements Constants{

	private DoubleSolenoid.Value v;
    public Shift(DoubleSolenoid.Value v) {
    	requires(Robot.pn);
    	this.v = v;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pn.shift(v);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
