package ranger.command;

import ranger.Game;

public class Quit implements Command {

	public String getKeyword() {
		return "quit";
	}

	public void execute(Game game, String[] words, String directObject) {
		System.exit(0);
	}

}
