package ranger.command;

import ranger.Game;

public class TimeCommand implements Command {

	public String getKeyword() {
		return "time";
	}

	public void execute(Game game, String[] words) {
		game.getTime().showTime();
	}

}
