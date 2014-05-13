package ranger;

import java.util.LinkedList;
import java.util.List;

import ranger.entity.Entity;
import ranger.hunting.HuntManager;
import ranger.item.Food;
import ranger.item.weapon.Weapon;
import ranger.map.Generator;
import ranger.map.Location;
import ranger.map.Region;
import ranger.name.Name;
import ranger.time.Time;

public class Game {
	
	public Time getTime() {
		return time;
	}
	
	public Player getPlayer() {
		return player;
	}

	public Location getPlayerLocation() {
		return location;
	}

	public void setPlayerLocation(Location location) {
		this.location = location;
	}
	
	public Region getRegion() {
		return region;
	}
	
	public void waitMinutes(int minutes) {
		time.waitMinutes(minutes);
		for (Entity e : entities)
			e.act(this, minutes);
	}

	public void addEntity(Entity entity, Location location) {
		entities.add(entity);
		location.addEntity(entity);
		entity.setCurrentLocation(location);
	}
	
	public void killEntity(Entity entity) {
		entities.remove(entity);
		
		
		entity.getCurrentLocation().removeEntity(entity);
	}
	
	public Game() {
		time = new Time();
		player = new Player();

		Weapon dagger = new Weapon(new Name("dagger"));
		player.setEquip(dagger);
		player.getInventory().addItem(dagger);
		player.getInventory().addItem(new Food("Salted Pork", 3, 15));
		player.getInventory().addItem(HuntManager.getRabbit());
		player.getInventory().addItem(HuntManager.getQuail());
			
		entities = new LinkedList<>();
		region = Generator.newRegion(this);	
		location = region.getRandomLocation();
	}
	
	private Time time;
	private Player player;
	private Region region;
	private Location location;
	private List<Entity> entities;
}
