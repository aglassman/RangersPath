package ranger.item.weapon;

import ranger.item.Item;
import ranger.name.Name;

public class Weapon extends Item {
	
	public int getDamage() {
		return damage;
	}

	public Weapon(Name name, int damage) {
		super(name);
		this.damage = damage;
	}
	
	private int damage;
}
