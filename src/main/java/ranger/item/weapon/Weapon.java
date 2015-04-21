package ranger.item.weapon;

import ranger.item.Item;
import ranger.name.Name;

public class Weapon extends Item {

    public int getDamage() {
        return damage;
    }

    /**
     * The time it takes to attack with the weapon after an attack
     * This doesn't tick down itself - the wielder is in charge of tracking its own
     * cooldown timer.
     */
    public int getCooldown() {
        return cooldown;
    }

    public boolean isRanged() {
		return isRanged;
	}

	public Weapon(Name name, int damage, int cooldown) {
		this(name, damage, false, cooldown);
	}

	public Weapon(Name name, int damage, boolean isRanged, int cooldown) {
		super(name);
		this.damage = damage;
		this.isRanged = isRanged;
        this.cooldown = cooldown;
	}
	
	private boolean isRanged;
	private int damage;
    private int cooldown;
}
