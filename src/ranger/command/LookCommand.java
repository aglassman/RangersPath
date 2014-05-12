package ranger.command;

import ranger.Game;
import ranger.map.Feature;
import ranger.map.Location;

public class LookCommand implements Command {

	public String getKeyword() {
		return "look";
	}

	public void execute(Game game, String[] words, String directObject) {
		System.out.println(game.getTime().describeTimeOfDay());
		
		Location location = game.getPlayerLocation();
		System.out.println(location.describe());
		
		if (!location.getFeatures().isEmpty()) {
			System.out.println("You see: ");
			for (Feature f : location.getFeatures())
				System.out.println(f.getName().indefinite());
		}
	}

}
