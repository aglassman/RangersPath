package ranger.map;

import java.util.LinkedList;
import java.util.List;


public class Region {

    public final int WIDTH;
    public final int HEIGHT;

    public Location getLocation(int row, int col) {
        return locations[col][row];
    }

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
	
	public List<Direction> getValidDirections(Location location) {
		LinkedList<Direction> valid = new LinkedList<>();
		if (location.y > 0)
			valid.add(Direction.NORTH);
		if (location.y < HEIGHT-1)
			valid.add(Direction.SOUTH);
		if (location.x > 0)
			valid.add(Direction.WEST);
		if (location.x < WIDTH-1)
			valid.add(Direction.EAST);
		return valid;
	}

	public void setLocation(Location location, int x, int y) {
		locations[x][y] = location;
	}
	
	public Region(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		locations = new Location[WIDTH][HEIGHT];
	}

	private Location[][] locations;
}
