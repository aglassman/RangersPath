package ranger.item.weapon;

import ranger.item.Item;
import ranger.name.Name;

public class Weapon extends Item {
	
	public int getDamage() {
		return damage;
	}
	
	public boolean isRanged() {
		return isRanged;
	}

	public Weapon(Name name, int damage) {
		super(name);
		this.damage = damage;
	}

	public Weapon(Name name, int damage, boolean isRanged) {
		super(name);
		this.damage = damage;
		this.isRanged = isRanged;
	}
	
	private boolean isRanged;
	private int damage;
}
