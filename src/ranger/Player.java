package ranger;

import ranger.item.Inventory;

public class Player {
	public Inventory getInventory() {
		return inventory;
	}
	
	public Player() {
		inventory = new Inventory();
	}
	
	private Inventory inventory;
}
