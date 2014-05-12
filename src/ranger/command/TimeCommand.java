package ranger.command;

import ranger.Game;

public class TimeCommand implements Command {

	public String getKeyword() {
		return "time";
	}

	public void execute(Game game, String[] words, String directObject) {
		game.getTime().showTime();
	}

}
