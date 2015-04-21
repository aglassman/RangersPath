package ranger.command;

import ranger.Game;
import ranger.item.Item;
import ranger.item.Inventory;

public class InventoryCommand implements Command {

	public String getKeyword() {
		return "inventory";
	}

	public String getHelpText() {
		return "See all the items you are carrying.";
	}

	public void execute(Game game, String[] words, String directObject) {
		Inventory inventory = game.getPlayer().getInventory();
		if (inventory.isEmpty()) {
			game.ui.output.println("You carry nothing.");
		} else {
			game.ui.output.println("You carry:");
			for (Item i : inventory.getItems()) {
				game.ui.output.print(i.getName().indefinite());
				if (i == game.getPlayer().getEquip())
					game.ui.output.println(" (equipped)");
				else if (i == game.getPlayer().getAmmo())
					game.ui.output.println(" (quiver)");
				else
					game.ui.output.println("");
			}
		}
	}

}
