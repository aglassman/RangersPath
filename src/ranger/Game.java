package ranger;

import ranger.item.Item;

public class Game {
	public Player getPlayer() {
		return player;
	}
	
	public Game() {
		player = new Player();
		
		player.getInventory().addItem(new Item("A Dagger"));
	}
	
	private Player player;
}
