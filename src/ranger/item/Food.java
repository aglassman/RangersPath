package ranger.item;

import ranger.name.FoodName;

public class Food extends Item {
	
	public int getServings() {
		return servings;
	}
	
	public void addServings(int moreServings) {
		servings += moreServings;
	}
	
	public void consume() {
		--servings;
	}
	
	public int getFoodValue() {
		return foodValue;
	}
	
	public Food(String foodName, int foodValue, int servings) {
		super(new FoodName(foodName));
		((FoodName)name).setFood(this);
		this.foodValue = foodValue;
		this.servings = servings;
	}
	
	private int foodValue;
	private int servings;
}
