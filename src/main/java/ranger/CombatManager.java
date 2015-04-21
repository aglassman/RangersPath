package ranger;

import ranger.entity.Entity;
import ranger.item.weapon.Ammo;

public class CombatManager {
    public void makeRangedAttack(Game game, Entity attacker, Ammo ammo, Entity defender, boolean attackStealthed) {
        int damage = attacker.getEquip().getDamage() + ammo.damage();

        // Stealth bonus
        if (attackStealthed)
            damage *= 2;

        // TODO other effects should include armor sand racial adjustments to the damage
        defender.takeDamage(damage);

        // Cleanup
        if (defender.isDead())
            game.killEntity(defender);
    }
}
