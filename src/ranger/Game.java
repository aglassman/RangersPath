package ranger;

import ranger.hunting.HuntManager;
import ranger.item.Food;
import ranger.item.Item;
import ranger.time.Time;

public class Game {
	
	public Time getTime() {
		return time;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Game() {
		time = new Time();
		player = new Player();

		player.getInventory().addItem(new Item("Dagger"));
		player.getInventory().addItem(new Food("Salted Pork", 3, 15));
		player.getInventory().addItem(HuntManager.getRabbit());
		player.getInventory().addItem(HuntManager.getQuail());
	}
	
	private Time time;
	private Player player;
}
