package ranger.command;

import ranger.Game;

public class WaitCommand implements Command {

	public String getKeyword() {
		return "wait";
	}

	public void execute(Game game, String[] words, String directObject) {
		try {
			int time;
			if (words.length > 1)
				time = Integer.parseInt(words[1]);
			else
				time = 15;
			game.waitMinutes(time);
		} catch (NumberFormatException e) {
		}
	}

}
