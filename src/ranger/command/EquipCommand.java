package ranger.command;

import ranger.Game;
import ranger.item.Item;
import ranger.item.weapon.Ammo;
import ranger.item.weapon.Weapon;

public class EquipCommand implements Command {

	public String getKeyword() {
		return "equip";
	}

	public String getHelpText() {
		return "Choose a weapon in your inventory to be your primary weapon.";
	}

	public void execute(Game game, String[] words, String directObject) {
		Item item = game.getPlayer().getInventory().getItem(directObject);
		
		if (item == null)
			System.out.println("You don't have one of those.");
		else if (item instanceof Weapon)
			game.getPlayer().setEquip((Weapon)item);
		else if (item instanceof Ammo)
			game.getPlayer().setAmmo((Ammo)item);
		else
			System.out.println("You can't equip " + item.getName().definite() + ".");
	}

}
