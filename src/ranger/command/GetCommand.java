package ranger.command;

import ranger.Game;
import ranger.item.Item;

public class GetCommand implements Command {

	public String getKeyword() {
		return "get";
	}

	public String getHelpText() {
		return "Pick up an item at your location.";
	}

	public void execute(Game game, String[] words, String directObject) {
		Item item = game.getPlayerLocation().getItem(directObject);
		if (item == null) {
			System.out.println("You can't find that here.");
		} else {
			game.getPlayer().getInventory().addItem(item);
		}
	}

}
