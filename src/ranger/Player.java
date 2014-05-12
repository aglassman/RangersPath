package ranger;

import ranger.item.Inventory;

public class Player {
	public Inventory getInventory() {
		return inventory;
	}
	
	public int getFood() {
		return food;
	}
	
	public void addFoodValue(int value) {
		food = Math.min(food + value, 100);
	}
	
	public int getHydration() {
		return hydration;
	}
	
	public int getHealth() {
		return health;
	}
	
	public Player() {
		inventory = new Inventory();
		
		food = 50;
		hydration = 100;
		health = 100;
	}
	
	private Inventory inventory;
	private int food;
	private int hydration;
	private int health;
}
