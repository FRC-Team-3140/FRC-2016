package main.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.commands.shooter.Intake;
import main.commands.turret.PivotDown;

/**
 *
 */
public class IntakeBall extends CommandGroup {
    
    public  IntakeBall() {
    	addSequential(new PivotDown(0.25));
    	addSequential(new Intake());
    }
}
