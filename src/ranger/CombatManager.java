package ranger;

import ranger.entity.Entity;
import ranger.map.Battlefield;
import ranger.map.Location;

public class CombatManager {
	public static void playerAttacks(Game game, Entity player, Location location) {
		Battlefield battlefield = new Battlefield();
		location.addFeature(battlefield);

		Entity defender = location.getEntities().get(0);
		double stealthDice = Math.random() + location.getTerrainType().stealthMod;
		if (stealthDice > 0.8) {
			System.out.println("You take " + defender.getName().definite() + " unawares and kill it!");
			game.killEntity(defender);
			battlefield.addBody(defender);
		}
		
		while (!location.getEntities().isEmpty()) {
			// Player makes an attack
			defender = location.getEntities().get(0);
			
			System.out.println("You attack " + defender.getName().definite()
					+ " with your " + player.getEquip().getName().basic() + "!");
			defender.takeDamage(50);
			if (defender.getHealth() == 0) {
				System.out.println("You killed " + defender.getName().definite() + "!");
				game.killEntity(defender);
				battlefield.addBody(defender);
			}
			
			for (Entity e : location.getEntities()) {
				if (e.weaponDrawn()) {
					System.out.println(e.getName().indefinite() + " attacks you with its " + e.getEquip().getName().basic());
					player.takeDamage(50);
				} else {
					System.out.println(e.getName().indefinite() + " draws " + e.getEquip().getName().indefinite() + ".");
					e.setWeaponDrawn(true);
				}
			}
		}
	}
}
