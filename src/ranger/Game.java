package ranger;

import java.util.LinkedList;
import java.util.List;

import ranger.entity.Entity;
import ranger.hunting.HuntManager;
import ranger.item.Food;
import ranger.item.weapon.Ammo;
import ranger.item.weapon.Weapon;
import ranger.map.Direction;
import ranger.map.Generator;
import ranger.map.Location;
import ranger.map.Region;
import ranger.name.Name;
import ranger.name.Name.NameType;
import ranger.time.Time;

public class Game {
	
	public Time getTime() {
		return time;
	}
	
	public Player getPlayer() {
		return player;
	}

	public Location getPlayerLocation() {
		return player.getCurrentLocation();
	}
	
	public void moveEntity(Entity entity, Direction direction) {
		Location source = entity.getCurrentLocation();
		Location destination = region.getNeighbor(source, direction);
		
		source.removeEntity(entity);
		destination.addEntity(entity);
		entity.setCurrentLocation(destination);
		if (destination == player.getCurrentLocation())
			Output.println("%s approached from the %s.", entity, NameType.INDEFINITE, direction.getOpposite(), NameType.INDEFINITE);

		if (source == player.getCurrentLocation())
			Output.println("%s left, heading %s.", entity, NameType.INDEFINITE, direction, NameType.INDEFINITE);
	}
	
	public void movePlayer(Direction direction) {
		Location destination = region.getNeighbor(player.getCurrentLocation(), direction);
		player.setCurrentLocation(destination);
		Output.println("You travel %s and come to %s.", direction, NameType.INDEFINITE, destination, NameType.INDEFINITE);
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
	
	public void killPlayer() {
		System.out.println("You are dead.");
		System.exit(0);
	}
	
	public Game() {		
		time = new Time();
		player = new Player();

		Weapon dagger = new Weapon(new Name("dagger"), 10);
		player.setEquip(dagger);
		player.getInventory().addItem(dagger);
		
		Weapon bow = new Weapon(new Name("bow"), 10, true);
		player.getInventory().addItem(bow);
		Ammo arrows = new Ammo("arrow", 5, 3);
		player.getInventory().addItem(arrows);
		player.setAmmo(arrows);
		
		player.getInventory().addItem(new Food("Salted Pork", 3, 15));
		player.getInventory().addItem(HuntManager.getRabbit());
		player.getInventory().addItem(HuntManager.getQuail());
			
		entities = new LinkedList<>();
		region = Generator.newRegion(this);	
		player.setCurrentLocation(region.getRandomLocation());
	}
	
	private Time time;
	private Player player;
	private Region region;
	private List<Entity> entities;
}
