package ranger.entity;

import ranger.Game;
import ranger.item.Inventory;
import ranger.item.weapon.Weapon;
import ranger.map.Location;
import ranger.name.Name;
import ranger.name.Named;

public class Entity implements Named {
	
	public void act(Game game, int time) {
		location.removeEntity(this);
		Location newLocation = game.getRegion().getRandomLocation();
		location = newLocation;
		newLocation.addEntity(this);
	}
	
	public void setCurrentLocation(Location location) {
		this.location = location;
	}
	
	public Location getCurrentLocation() {
		return location;
	}

	public Name getName() {
		return name;
	}
	
	public void setEquip(Weapon weapon) {
		equip = weapon;
	}
	
	public Weapon getEquip() {
		return equip;
	}
	
	public boolean weaponDrawn() {
		return drawn;
	}
	
	public void setWeaponDrawn(boolean drawn) {
		this.drawn = drawn;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void takeDamage(int damage) {
		health = Math.max(0, health-damage);
	}

	public int getHealth() {
		return health;
	}
	
	public Entity(Name name) {
		this.name = name;
		inventory = new Inventory();
		health = 100;
	}
	
	private Inventory inventory;
	protected Weapon equip;
	protected Name name;
	protected Location location;
	protected int health;
	protected boolean drawn;
}
