package ranger.command;

import ranger.Game;
import ranger.item.Item;
import ranger.map.Feature;
import ranger.map.Location;

public class SearchCommand implements Command {

	public String getKeyword() {
		return "search";
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
				System.out.println("You search the " + f.getName().definite() + " and you find:");
				for (Item i : f.getInventory().getItems())
					System.out.println(i.getName().indefinite());
			}
		}
	}

}
