package ranger.command;

import ranger.Game;
import ranger.Output;
import ranger.item.Carcass;
import ranger.item.Inventory;
import ranger.item.Item;
import ranger.name.Name.NameType;

public class CleanCommand implements Command {

	public String getKeyword() {
		return "clean";
	}

	public String getHelpText() {
		return "Clean and dress an animal carcass for meat and hide.";
	}

	public void execute(Game game, String[] words, String directObject) {
		Inventory invent = game.getPlayer().getInventory();
		Item item = invent.getItem(directObject);
		if (item == null) {
			System.out.println("You don't have any of that.");
		} else if (item instanceof Carcass) {
			Carcass carcass = (Carcass)item;
			invent.expend(carcass);
			
			Output.println("You cleaned %s and produced:", carcass, NameType.DEFINITE);
			Output.printlnList("", carcass.clean(), "");
			for (Item product : carcass.clean()) {
				invent.addItem(product);
			}
		} else {
			System.out.println("You can't clean your " + directObject);
		}
	}

}
