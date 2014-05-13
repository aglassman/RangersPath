package ranger.command;

import ranger.Game;
import ranger.entity.Entity;
import ranger.map.Location;
import ranger.name.Name;

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
			System.out.println("You see " + Name.commaList(location.getFeatures()) + ".");
		}
		
		if (!location.getEntities().isEmpty()) {
			for (Entity e : location.getEntities()) {
				if (e.getEquip() != null) {
					if (e.weaponDrawn()) {
						System.out.println("You see " + e.getName().indefinite() + ", with its "
								+ e.getEquip().getName().basic()+" drawn!");
					} else {
						System.out.println("You see " + e.getName().indefinite() + " equipped with an undrawn "
								+ e.getEquip().getName().basic() + ".");
					}
				} else {
					System.out.println("You see " + e.getName().indefinite() + ", unarmed.");
				}
			}
		}
	}

}
