package ranger;

import ranger.hunting.HuntManager;
import ranger.item.Food;
import ranger.item.Item;
import ranger.map.Feature;
import ranger.map.Location;
import ranger.map.Region;
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
	
	public Game() {
		time = new Time();
		player = new Player();

		player.getInventory().addItem(new Item("Dagger"));
		player.getInventory().addItem(new Food("Salted Pork", 3, 15));
		player.getInventory().addItem(HuntManager.getRabbit());
		player.getInventory().addItem(HuntManager.getQuail());
		
		region = new Region();
		region.addLocation(new Location("The forest stretches as far as your eye can see."));
		
		location = region.getRandomLocation();
		Feature orcCamp = new Feature("Orc camp", "It looks like the bastards cleared out of here long ago.");
		location.addFeature(orcCamp);
		orcCamp.getInventory().addItem(HuntManager.getRabbit());
	}
	
	private Time time;
	private Player player;
	private Region region;
	private Location location;
}
