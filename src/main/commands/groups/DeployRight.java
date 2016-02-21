package main.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.commands.tomahawks.ActuateRight;
import main.commands.tomahawks.rOff;

/**
 *
 */
public class DeployRight extends CommandGroup implements Constants{
    
    public  DeployRight() {
    	addSequential(new ActuateRight());
    	addSequential(new WaitCommand(0.125));
    	addSequential(new rOff());
    }
}
