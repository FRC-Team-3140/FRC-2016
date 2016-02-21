package main.commands;

import edu.wpi.first.wpilibj.command.Command;
import main.Robot;

/**
 *
 */
public class SwitchCamera extends Command {

    public SwitchCamera() {
    	requires(Robot.cc);
    }

    @Override
    protected void initialize() {
      switch (Robot.cc.getCurCamera()) {
        case FRONT:
          Robot.cc.startStream(main.subsystems.CameraController.Camera.BACK);
          break;
        case BACK:
          Robot.cc.startStream(main.subsystems.CameraController.Camera.FRONT);
          break;
        default:
          throw new IllegalStateException("Not implemented - what are you even doing");
      }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
