package game.engine.io.console;

import game.engine.io.UserInterface;

public class ConsoleInterface extends UserInterface{

	public ConsoleInterface() {
		super(new ConsoleInput(), new ConsoleOutput());
	}

}
