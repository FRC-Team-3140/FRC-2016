package main.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.commands.tomahawks.ActuateLeft;
import main.commands.tomahawks.lOff;

/**
 *
 */
public class DeployLeft extends CommandGroup implements Constants{
    
    public  DeployLeft() {
    	addSequential(new ActuateLeft());
    	addSequential(new WaitCommand(0.125));
    	addSequential(new lOff());
    }
}
