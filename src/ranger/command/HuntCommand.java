package ranger.command;

import ranger.Game;
import ranger.hunting.HuntManager;

public class HuntCommand implements Command {

	public String getKeyword() {
		return "hunt";
	}

	public String getHelpText() {
		return "Hunt for game at your location.";
	}

	public void execute(Game game, String[] words, String directObject) {
		HuntManager.doHunt(game);
	}

}
