package ranger.command;

import ranger.Game;
import ranger.map.Location;
import ranger.name.Name;

public class LookCommand implements Command {

	public String getKeyword() {
		return "look";
	}

	public void execute(Game game, String[] words, String directObject) {
		System.out.print(game.getTime().describeTimeOfDay() + " ");
		
		Location location = game.getPlayerLocation();
		System.out.println(location.describe());
		
		if (!location.getFeatures().isEmpty()) {
			System.out.println("You see " + Name.commaList(location.getFeatures()) + ".");
		}
	}

}
