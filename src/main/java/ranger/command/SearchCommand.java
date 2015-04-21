package ranger.command;

import ranger.Game;
import ranger.map.Feature;
import ranger.map.Location;

public class SearchCommand implements Command {

	public String getKeyword() {
		return "search";
	}

	public String getHelpText() {
		return "Thoroughly search a feature of your location.";
	}

	public void execute(Game game, String[] words, String directObject) {
		Location location = game.getPlayerLocation();
		Feature f = location.getFeature(directObject);
		if (f == null) {
			game.ui.output.println("There is nothing like that to search here.");
		} else {
			// Describe the feature.
			game.ui.output.println(f.getDescription());
			
			// Describe any items found here.
			if (f.getInventory().isEmpty()) {
				game.ui.output.println("You do not find anything here.");
			} else {
				game.ui.output.printlnList("Searching " + f.getName().definite() + " you find ", f.getInventory().getItems(), ".");
			}
		}
	}

}
