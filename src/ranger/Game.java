package ranger;

import ranger.item.Carcass;
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
		player.getInventory().addItem(new Carcass("Rabbit", 4, 5, true));
		player.getInventory().addItem(new Carcass("Pigeon", 1, 1, false));
	}
	
	private Time time;
	private Player player;
}
