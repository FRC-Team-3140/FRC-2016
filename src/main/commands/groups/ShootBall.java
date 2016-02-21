package main.commands.groups;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.commands.shooter.Launch;
import main.commands.shooter.Shoot;
import main.commands.shooter.Stop;

/**
 *
 */
public class ShootBall extends CommandGroup implements Constants {
    
    public  ShootBall() {
    	addSequential(new Shoot());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new Launch(DoubleSolenoid.Value.kForward));
    	addSequential(new WaitCommand(0.125));
    	addSequential(new Launch(DoubleSolenoid.Value.kReverse));
    	addSequential(new Stop());
    	addSequential(new WaitCommand(0.125));
    	addSequential(new Launch(DoubleSolenoid.Value.kOff));
    }
}
