package main.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import main.Constants;
import main.Robot;

/**
 *
 */
public class Stop extends Command implements Constants{

    public Stop() {
        requires(Robot.sh);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.sh.spin(0);
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
