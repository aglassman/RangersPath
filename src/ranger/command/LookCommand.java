package ranger.command;

import java.util.LinkedList;
import java.util.List;

import ranger.Game;
import ranger.Output;
import ranger.entity.Entity;
import ranger.map.Direction;
import ranger.map.Location;
import ranger.name.Name.NameType;

public class LookCommand implements Command {

	public String getKeyword() {
		return "look";
	}

	public String getHelpText() {
		return "Investigate your surroundings.";
	}

	public void execute(Game game, String[] words, String directObject) {
		Output.clear();
		System.out.print(game.getTime().describeTimeOfDay() + " ");
		
		Location location = game.getPlayerLocation();
		System.out.print(location.describe());

		// List available directions
		List<Direction> similarNeighbors = new LinkedList<>();
		List<Direction> differentNeighbors = new LinkedList<>();
		for (Direction d : game.getRegion().getValidDirections(location)) {
			Location neighbor = game.getRegion().getNeighbor(location, d);
			if (neighbor.getTerrainType() == location.getTerrainType())
				similarNeighbors.add(d);
			else
				differentNeighbors.add(d);
		}
		if (!similarNeighbors.isEmpty())
			Output.printList(" The " + location.getName().basic() + " continues to the ", similarNeighbors, ".");
		if (!differentNeighbors.isEmpty()) {
			for (Direction d : differentNeighbors) {
				Location neighbor = game.getRegion().getNeighbor(location, d);
				Output.print(" To the %s is %s.", d, NameType.INDEFINITE, neighbor, NameType.INDEFINITE);
			}
		}
		System.out.println();
		
		// Display the features of this location
		if (!location.getFeatures().isEmpty())
			Output.printlnList("You see ", location.getFeatures(), ".");
		
		// Display the entities in this location
		if (!location.getEntities().isEmpty()) {
			for (Entity e : location.getEntities()) {
				if (e.getEquip() != null) {
					if (e.weaponDrawn()) {
						Output.println("You see %s, with its %s drawn!", e, NameType.INDEFINITE, e.getEquip(), NameType.BASIC);
					} else {
						Output.println("You see %s equipped with and undrawn %s.", e, NameType.INDEFINITE, e.getEquip(), NameType.BASIC);
					}
				} else {
					Output.println("You see %s, unarmed.", e, NameType.INDEFINITE);
				}
			}
		}
	}
}
