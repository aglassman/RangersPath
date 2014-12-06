package ranger;

import ranger.entity.Entity;
import ranger.map.Battlefield;
import ranger.map.Location;
import ranger.name.Name.NameType;

public class TextCombatManager {
	
	public static void fight(Game game, Location location, Entity attacker, Entity defender) {
		Battlefield battlefield = new Battlefield();
		location.addFeature(battlefield);

		boolean attackWasMade = false;
		do {
			attackWasMade = attack(attacker, defender, game, location, battlefield);
			attackWasMade |= attack(defender, attacker, game, location, battlefield);
		} while (attackWasMade && attacker.isAlive() && defender.isAlive());
	}
	
	public static void playerAttacks(Game game, Entity player, Location location) {
		Battlefield battlefield = new Battlefield();
		location.addFeature(battlefield);

		// Player attempts to sneak attack
		stealthAttack(player, location.getEntities().get(0), game, location, battlefield);
		
		// Continue the combat while there are enemies and while an attack is made
		int attacksMade = 1;
		while (!location.getEntities().isEmpty() && attacksMade > 0) {
			attacksMade = 0;
			Entity defender = location.getEntities().get(0);
			if (attack(player, defender, game, location, battlefield))
				++attacksMade;
			
			// Each enemy attacks player
			for (Entity e : location.getEntities()) {
				if (e.weaponDrawn()) {
					if (attack(e, player, game, location, battlefield))
						++attacksMade;
				} else {
					Output.println("\t%s draws %s.", e, NameType.INDEFINITE, e.getEquip(), NameType.INDEFINITE);
					e.setWeaponDrawn(true);
				}
			}
		}
	}
	
	private static boolean attack(Entity attacker, Entity defender, Game game, Location location, Battlefield battlefield) {
		boolean attacked = false;
		if (attacker.getEquip().isRanged()) {
			// Check if the attacker has ammo
			if (attacker.getAmmo() != null && attacker.getAmmo().getQuantity() > 1) {
				attacker.getInventory().expend(attacker.getAmmo());
				attackMessage(attacker, defender, game);
				attacked = true;
			} else {
				noAmmoMessage(attacker, game);
			}
		} else {
			// Attacker hits Defender with a melee attack
			attackMessage(attacker, defender, game);
			defender.takeDamage(attacker.getEquip().getDamage());
			attacked = true;
		}
		if (defender.isDead()) {
			kill(attacker, defender, game, battlefield);
		}
		return attacked;
	}
	private static void stealthAttack(Entity attacker, Entity defender, Game game, Location location, Battlefield battlefield) {
		boolean canAttack = false;
		if (attacker.getEquip().isRanged()) {
			// Check if the attacker has ammo
			if (attacker.getAmmo() != null && attacker.getAmmo().getQuantity() > 1) {
				attacker.getInventory().expend(attacker.getAmmo());
				canAttack = true;
			} else {
				noAmmoMessage(attacker, game);
			}
		} else {
			// Attacker hits Defender with a melee attack
			canAttack = true;
		}
		double stealthDice = Math.random() + location.getTerrainType().stealthMod;
		if (canAttack && stealthDice > defender.getPerception(game)) {
			if (defender == game.getPlayer()) {
				game.killPlayer();
			} else {
				game.killEntity(defender);
				battlefield.addBody(defender);
				if (attacker == game.getPlayer())
					Output.println("\tYou take %s unawares and kill it!", defender, NameType.DEFINITE);
				else
					Output.println("%s takes %s unawares and kills it!", attacker, NameType.DEFINITE, defender, NameType.DEFINITE);
			}
		}
	}
	
	private static void attackMessage(Entity attacker, Entity defender, Game game) {
		if (attacker == game.getPlayer())
			Output.println("\tYou attack %s with your %s.", defender, NameType.DEFINITE, attacker.getEquip(), NameType.BASIC);
		else if (defender == game.getPlayer())
			Output.println("\t%s attacks you with its %s.", attacker, NameType.INDEFINITE, attacker.getEquip(), NameType.BASIC);
		else
			Output.println("\t%s attacks %s with its %s.", attacker, NameType.INDEFINITE, defender, NameType.INDEFINITE, attacker.getEquip(), NameType.BASIC);
	}
	
	private static void kill(Entity attacker, Entity defender, Game game, Battlefield battlefield) {
		if (defender == game.getPlayer()) {
			game.killPlayer();
		} else {
			game.killEntity(defender);
			battlefield.addBody(defender);
			
			if (attacker == game.getPlayer())
				Output.println("\tYou killed %s!", defender, NameType.DEFINITE);
			else
				Output.println("\t%s died!", defender, NameType.DEFINITE);
		}
	}
	
	private static void noAmmoMessage(Entity entity, Game game) {
		if (entity == game.getPlayer())
			System.out.println("\tYou are out of ammo.");
		else
			Output.println("\t%s is out of ammo.", entity, NameType.DEFINITE);
	}
}
