package main.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import main.Robot;

/**
 *
 */
public class StreamCamera extends Command {
	  private static final int FPS = 30;
	  private Thread captureThread;

	  public StreamCamera() {
	    requires(Robot.cc);
	  }

	  @Override
	  protected void initialize() {
	    captureThread = new Thread(() -> {
	      while (true) {
	        Robot.cc.stream();
	        System.out.println(1 / (double) FPS);
	        Timer.delay(1 / (double) FPS);
	      }
	    });
	    captureThread.setName("Camera Capture Thread");
	    captureThread.start();
	  }

	  @Override
	  protected void execute() {
		  Robot.cc.stream();

	  }

	  @Override
	  protected boolean isFinished() {
	    return false;
	  }

	  @SuppressWarnings("deprecation")
	  @Override
	  protected void end() {
	    captureThread.stop();
	  }

	  @Override
	  protected void interrupted() {
	    end();
	  }
	}
