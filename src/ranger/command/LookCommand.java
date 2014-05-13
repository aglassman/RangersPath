package ranger.command;

import ranger.Game;
import ranger.Output;
import ranger.entity.Entity;
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
		System.out.print(game.getTime().describeTimeOfDay() + " ");
		
		Location location = game.getPlayerLocation();
		System.out.println(location.describe());
		
		if (!location.getFeatures().isEmpty()) {
			Output.printList("You see ", location.getFeatures(), ".");
		}
		
		if (!location.getEntities().isEmpty()) {
			for (Entity e : location.getEntities()) {
				if (e.getEquip() != null) {
					if (e.weaponDrawn()) {
						Output.print("You see %s, with its %s drawn!", e, NameType.INDEFINITE, e.getEquip(), NameType.BASIC);
					} else {
						Output.print("You see %s equipped with and undrawn %s.", e, NameType.INDEFINITE, e.getEquip(), NameType.BASIC);
					}
				} else {
					Output.print("You see %s, unarmed.", e, NameType.INDEFINITE);
				}
			}
		}
	}
}
