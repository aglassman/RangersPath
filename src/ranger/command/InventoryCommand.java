package ranger.command;

import ranger.Game;
import ranger.item.Item;
import ranger.item.Inventory;

public class InventoryCommand implements Command {

	public String getKeyword() {
		return "inventory";
	}

	public void execute(Game game, String[] words) {
		Inventory inventory = game.getPlayer().getInventory();
		if (inventory.isEmpty()) {
			System.out.println("You carry nothing.");
		} else {
			System.out.println("You carry:");
			for (Item i : inventory.getItems())
				System.out.println(i);
		}
	}

}
