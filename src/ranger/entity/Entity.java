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
	
	public double getPerception(Game game) {
		double perception = 0.5;
		if (darkVision && game.getTime().isDark())
			perception += 0.2;
		
		return perception;
	}
	
	public Entity(Name name, boolean darkVision) {
		this.name = name;
		inventory = new Inventory();
		health = 100;
	}
	
	protected Inventory inventory;
	protected Weapon equip;
	protected Name name;
	protected Location location;
	protected int health;
	protected boolean drawn;
	
	protected boolean darkVision;
}
