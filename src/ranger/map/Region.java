package ranger.map;


public class Region {

	public Location getRandomLocation() {
		return locations[((int)Math.floor(Math.random() * WIDTH))][((int)Math.floor(Math.random() * HEIGHT))];
	}
	
	public Location getNeighbor(Location location, Direction direction) {
		if (direction == Direction.SOUTH)
			return locations[location.x][location.y+1];
		else if (direction == Direction.NORTH)
			return locations[location.x][location.y-1];
		else if (direction == Direction.WEST)
			return locations[location.x-1][location.y];
		else
			return locations[location.x+1][location.y];
	}
	
	public Direction[] getValidDirections(Location location) {
		return Direction.values();
	}

	public void setLocation(Location location, int x, int y) {
		locations[x][y] = location;
	}
	
	public Region(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		locations = new Location[WIDTH][HEIGHT];
	}
	
	private final int WIDTH;
	private final int HEIGHT;
	private Location[][] locations;
}
