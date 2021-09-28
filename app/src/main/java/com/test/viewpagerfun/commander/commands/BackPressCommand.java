package com.test.viewpagerfun.commander.commands;

import com.test.viewpagerfun.commander.Interface.ICommand;
import com.test.viewpagerfun.commander.receiver.BackPress;
import com.test.viewpagerfun.commander.receiver.ShuffleCards;

/* BackPressCommand.class
 * ----------------------
 * Represents a command.
 * Each command has a execute() method, as agreed upon by the interface.
 * This method gets called by the commander.
 * Within the execute() method the methods of the encapsulated receiver objects are called.
 * 
 * Here the receiver is, BackPress.
 * Furthermore a state is given to the receiver, so that more sophisticated logic can be implemented.
 */
public class BackPressCommand implements ICommand {

	BackPress backPress;

	public BackPressCommand() {
		this.backPress = new BackPress();
	}

	@Override
	public void execute() {
		backPress.click();
	}

	@Override
	public <E> void setPref(E pref) {
		backPress.setPref(pref);
	}

	@Override
	public <E> void setState(E state) {
		backPress.setState(state);
	}


}
