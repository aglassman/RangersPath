package ranger.map;

import ranger.entity.Entity;
import ranger.item.Item;
import ranger.name.Name;

public class Battlefield extends Feature {
	
	public void addBody(Entity dead) {
		++bodies;
		for (Item i : dead.getInventory().getItems())
			inventory.addItem(i);
		
		if (bodies > 4)
			description = "A battle was fought here.";
	}

	public Battlefield() {
		super(new Name("skirmish site"), "A skirmish was fought here.");
	}

	private int bodies;
}
