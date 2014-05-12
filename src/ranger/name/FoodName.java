package ranger.name;

import ranger.item.Food;

public class FoodName extends Name {

	@Override
	public String definite() {
		return "the " + baseName;
	}

	@Override
	public String indefinite() {
		return food.getServings() > 1
				? food.getServings() + " servings of " + baseName
				: "a serving of " + baseName;
	}
	
	public void setFood(Food food) {
		this.food = food;
	}
	
	public FoodName(String name) {
		super(name);
	}
	
	private Food food;
}
