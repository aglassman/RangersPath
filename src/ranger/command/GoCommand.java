package ranger.command;

import ranger.Game;
import ranger.Output;
import ranger.map.Direction;
import ranger.map.Location;
import ranger.name.Name.NameType;

public class GoCommand implements Command {

	public String getKeyword() {
		return "go";
	}

	public void execute(Game game, String[] words, String directObject) {
		try {
			Direction direction = Direction.valueOf(directObject.toUpperCase());
			Location current = game.getPlayerLocation();
			
			if (game.getRegion().getValidDirections(current).contains(direction)) {
				Location destination = game.getRegion().getNeighbor(current, direction);
				game.setPlayerLocation(destination);
				Output.println("You travel %s and come to %s.", direction, NameType.INDEFINITE, destination, NameType.INDEFINITE);
			} else {
				Output.println("You can't go %s.", direction, NameType.INDEFINITE);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("You can't go that way.");
		}
	}

	public String getHelpText() {
		return "Travel North, South, East, or West";
	}

}
