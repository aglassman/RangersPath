package ranger.command;

import ranger.Game;
import ranger.hunting.HuntManager;

public class HuntCommand implements Command {

	public String getKeyword() {
		return "hunt";
	}

	public void execute(Game game, String[] words, String directObject) {
		HuntManager.doHunt(game);
	}

}
