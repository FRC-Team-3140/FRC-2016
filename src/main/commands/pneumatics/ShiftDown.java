package main.commands.pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ShiftDown extends CommandGroup {
    
    public  ShiftDown() {
    	addSequential(new Shift(DoubleSolenoid.Value.kForward));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new Shift(DoubleSolenoid.Value.kOff));
    }
}
