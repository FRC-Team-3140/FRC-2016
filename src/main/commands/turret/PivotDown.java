package main.commands.turret;

import edu.wpi.first.wpilibj.command.Command;
import main.Robot;

/**
 *
 */
public class PivotDown extends Command {

	private double speed;
	
    public PivotDown(double s) {
    	requires(Robot.tr);
    	speed = s;
    }


    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.tr.pivotTo(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.tr.lower.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.tr.pivotTo(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
