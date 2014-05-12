package ranger;

import ranger.hunting.HuntManager;
import ranger.item.Food;
import ranger.item.Item;
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
	
	public Game() {
		time = new Time();
		player = new Player();

		player.getInventory().addItem(new Item(new Name("dagger")));
		player.getInventory().addItem(new Food("Salted Pork", 3, 15));
		player.getInventory().addItem(HuntManager.getRabbit());
		player.getInventory().addItem(HuntManager.getQuail());
		
		region = Generator.newRegion();		
		location = region.getRandomLocation();
	}
	
	private Time time;
	private Player player;
	private Region region;
	private Location location;
}
