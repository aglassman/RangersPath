package ranger;

import ranger.entity.Entity;
import ranger.name.Name;

public class Player extends Entity {

	public int getFood() {
		return food;
	}
	
	public void addFoodValue(int value) {
		food = Math.min(food + value, 100);
	}
	
	public int getHydration() {
		return hydration;
	}
	
	public Player() {
		super(new Name("Turambar"));
		
		food = 50;
		hydration = 100;
	}
	
	private int food;
	private int hydration;
}
