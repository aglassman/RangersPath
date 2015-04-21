package ranger.command;

import ranger.Game;

public class TimeCommand implements Command {

	public String getKeyword() {
		return "time";
	}

	public String getHelpText() {
		return "Check the approximate time of day.";
	}

	public void execute(Game game, String[] words, String directObject) {
		game.getTime().showDayTime();
	}

}
