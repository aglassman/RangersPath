package ranger.map;

import java.util.LinkedList;

public class Region {
	public Location getRandomLocation() {
		return locations.get((int)Math.floor(Math.random() * locations.size()));
	}
	
	public void addLocation(Location location) {
		locations.add(location);
	}
	
	public Region() {
		locations = new LinkedList<>();
	}
	
	private LinkedList<Location> locations;
}
