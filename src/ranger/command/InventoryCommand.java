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
			System.out.println("You carry nothing.");
		} else {
			System.out.println("You carry:");
			for (Item i : inventory.getItems()) {
				System.out.print(i.getName().indefinite());
				if (i == game.getPlayer().getEquip())
					System.out.println(" (equipped)");
				else
					System.out.println();
			}
		}
	}

}
