package ranger;

import ranger.entity.Entity;
import ranger.map.Battlefield;
import ranger.map.Location;
import ranger.name.Name.NameType;

public class CombatManager {
	public static void playerAttacks(Game game, Entity player, Location location) {
		Battlefield battlefield = new Battlefield();
		location.addFeature(battlefield);

		// Player attempts to sneak attack
		Entity defender = location.getEntities().get(0);
		double stealthDice = Math.random() + location.getTerrainType().stealthMod;
		if (stealthDice > defender.getPerception(game)) {
			Output.print("\tYou take %s unawares and kill it!", defender, NameType.DEFINITE);
			game.killEntity(defender);
			battlefield.addBody(defender);
		}
		
		// Continue the combat while there are enemies
		while (!location.getEntities().isEmpty()) {
			defender = location.getEntities().get(0);

			// Player makes an attack
			Output.print("\tYou attack %s with your %s.", defender, NameType.DEFINITE, player.getEquip(), NameType.BASIC);
			defender.takeDamage(player.getEquip().getDamage());
			if (defender.getHealth() == 0) {
				Output.print("\tYou killed %s!", defender, NameType.DEFINITE);
				game.killEntity(defender);
				battlefield.addBody(defender);
			}
			
			// Each enemy attacks player
			for (Entity e : location.getEntities()) {
				if (e.weaponDrawn()) {
					Output.print("\t%s attacks you with its %s.", e, NameType.INDEFINITE, e.getEquip(), NameType.BASIC);
					player.takeDamage(e.getEquip().getDamage());
					if (player.getHealth() == 0)
						game.killPlayer();
				} else {
					Output.print("\t%s draws %s.", e, NameType.INDEFINITE, e.getEquip(), NameType.INDEFINITE);
					e.setWeaponDrawn(true);
				}
			}
		}
	}
}
