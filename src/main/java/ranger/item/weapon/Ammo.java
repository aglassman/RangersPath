package ranger.item.weapon;

import ranger.item.Item;
import ranger.name.StackableItemName;

public class Ammo extends Item {
	public int damage() {
		return damage;
	}
	
	public Ammo(String name, int damage, int amount) {
		this.name = new StackableItemName(name, this);
		stackable = true;
		quantity = amount;
		this.damage = damage;
	}
	
	private int damage;
}
