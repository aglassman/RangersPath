package ranger;

import ranger.hunting.HuntManager;
import ranger.item.Food;
import ranger.item.Item;
import ranger.map.Feature;
import ranger.map.Location;
import ranger.map.Region;
import ranger.name.DefiniteName;
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
	
	public Game() {
		time = new Time();
		player = new Player();

		player.getInventory().addItem(new Item(new Name("dagger")));
		player.getInventory().addItem(new Food("Salted Pork", 3, 15));
		player.getInventory().addItem(HuntManager.getRabbit());
		player.getInventory().addItem(HuntManager.getQuail());
		
		region = new Region();
		
		Location locationA = new Location("The forest stretches as far as your eye can see.");
		Feature orcCamp = new Feature(new Name("Orc camp"), "It looks like the bastards cleared out of here long ago.");
		orcCamp.getInventory().addItem(HuntManager.getRabbit());
		orcCamp.getInventory().addItem(HuntManager.getQuail());
		orcCamp.getInventory().addItem(new Item("arrowhead", 5));
		locationA.addFeature(orcCamp);
		region.addLocation(locationA);
		
		Location locationB = new Location("The forest stretches as far as your eye can see.");
		Feature seat = new Feature(new DefiniteName("Seat of the King"), "An ancient stone seat atop a hill, carved with the faces of long-forgotten kings.");
		locationB.addFeature(seat);
		Feature spring = new Feature(new Name("pond"), "You see your grizzled reflection in the still surface of the water.");
		locationB.addFeature(spring);
		region.addLocation(locationB);
		
		location = region.getRandomLocation();
	}
	
	private Time time;
	private Player player;
	private Region region;
	private Location location;
}
