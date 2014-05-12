package ranger;

import ranger.item.Food;
import ranger.item.Item;

public class Game {
	public Player getPlayer() {
		return player;
	}
	
	public Game() {
		player = new Player();

		player.getInventory().addItem(new Item("Dagger"));
		player.getInventory().addItem(new Food("Salted Pork", 3, 15));
	}
	
	private Player player;
}
