package ranger.command;

import ranger.Game;
import ranger.Util;
import ranger.map.Direction;

public class WanderCommand implements Command {

	public String getKeyword() {
		return "wander";
	}

	public String getHelpText() {
		return "Explore the world.";
	}

	public void execute(Game game, String[] words, String directObject) {
		Direction move = Util.random(game.getRegion().getValidDirections(game.getPlayerLocation()));
		game.movePlayer(move);
	}

}
