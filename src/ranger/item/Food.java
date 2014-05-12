package ranger.item;

import ranger.name.FoodName;

public class Food extends Item {

	public int getFoodValue() {
		return foodValue;
	}
	
	public Food(String foodName, int foodValue, int servings) {
		super();
		this.foodValue = foodValue;
		name = new FoodName(foodName, this);
		quantity = servings;
	}
	
	private int foodValue;
}
