package ranger;

import ranger.entity.Entity;
import ranger.map.Battlefield;
import ranger.map.Location;

public class CombatManager {
	public static void playerAttacks(Game game, Entity player, Location location) {
		Battlefield battlefield = new Battlefield();
		location.addFeature(battlefield);

		// Player attempts to sneak attack
		Entity defender = location.getEntities().get(0);
		double stealthDice = Math.random() + location.getTerrainType().stealthMod;
		if (stealthDice > defender.getPerception(game)) {
			System.out.println("You take " + defender.getName().definite() + " unawares and kill it!");
			game.killEntity(defender);
			battlefield.addBody(defender);
		}
		
		// Continue the combat while there are enemies
		while (!location.getEntities().isEmpty()) {
			defender = location.getEntities().get(0);

			// Player makes an attack
			System.out.println("You attack " + defender.getName().definite()
					+ " with your " + player.getEquip().getName().basic() + "!");
			defender.takeDamage(player.getEquip().getDamage());
			if (defender.getHealth() == 0) {
				System.out.println("You killed " + defender.getName().definite() + "!");
				game.killEntity(defender);
				battlefield.addBody(defender);
			}
			
			// Each enemy attacks player
			for (Entity e : location.getEntities()) {
				if (e.weaponDrawn()) {
					System.out.println(e.getName().indefinite() + " attacks you with its " + e.getEquip().getName().basic());
					player.takeDamage(e.getEquip().getDamage());
					if (player.getHealth() == 0)
						game.killPlayer();
				} else {
					System.out.println(e.getName().indefinite() + " draws " + e.getEquip().getName().indefinite() + ".");
					e.setWeaponDrawn(true);
				}
			}
		}
	}
}
