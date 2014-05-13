package ranger.command;

import ranger.CombatManager;
import ranger.Game;
import ranger.map.Location;

public class AttackCommand implements Command {

	public String getKeyword() {
		return "attack";
	}

	public void execute(Game game, String[] words, String directObject) {
		Location location = game.getPlayerLocation();
		if (location.getEntities().isEmpty()) {
			System.out.println("Nobody to attack here.");
		} else {
			CombatManager.playerAttacks(game, game.getPlayer(), location);
		}		
	}

}
