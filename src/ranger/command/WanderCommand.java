package ranger.command;

import ranger.Game;

public class WanderCommand implements Command {

	public String getKeyword() {
		return "wander";
	}

	public void execute(Game game, String[] words, String directObject) {
		game.setPlayerLocation(game.getRegion().getRandomLocation());
		
		System.out.println("You wander to " + game.getPlayerLocation().getName().indefinite() + ".");
	}

}
