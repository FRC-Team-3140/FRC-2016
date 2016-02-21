package main.commands.shooter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import main.Robot;

/**
 *
 */
public class Launch extends Command {

	DoubleSolenoid.Value v;
	
    public Launch(DoubleSolenoid.Value b) {
    	requires(Robot.sh);
    	v = b;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.sh.launch(v);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.sh.launch(DoubleSolenoid.Value.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
