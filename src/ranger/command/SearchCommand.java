package ranger.command;

import ranger.Game;
import ranger.map.Feature;
import ranger.map.Location;
import ranger.name.Name;

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
			System.out.println("There is nothing like that to search here.");
		} else {
			// Describe the feature.
			System.out.println(f.getDescription());
			
			// Describe any items found here.
			if (f.getInventory().isEmpty()) {
				System.out.println("You do not find anything here.");
			} else {
				System.out.println("Searching " + f.getName().definite() + " you find " 
						+ Name.commaList(f.getInventory().getItems()) + ".");
			}
		}
	}

}
