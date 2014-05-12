package ranger.hunting;

import ranger.Game;
import ranger.item.Carcass;

public class HuntManager {

	public static Carcass getRabbit() {
		return new Carcass("Rabbit", 8, 1, true);
	}

	public static Carcass getDeer() {
		return new Carcass("Rabbit", 15, 20, true);
	}

	public static Carcass getQuail() {
		return new Carcass("Quail", 5, 1, false);
	}
	
	public static void doHunt(Game game) {
		game.getTime().waitMinutes(60);
		
		double dice = Math.random();
		if (dice >= 0.5) {
			System.out.println("You caught nothing.");
		} else {
			Carcass carcass;
			if (dice >= 0.2)
				carcass = getRabbit();
			else
				carcass = getDeer();
			System.out.println("You caught a " + carcass.getAnimalName().toLowerCase() + ".");
			game.getPlayer().getInventory().addItem(carcass);
		}
	}
}
