package ranger.name;

import ranger.item.Food;

public class FoodName extends Name {

	@Override
	public String definite() {
		return "the " + baseName;
	}

	@Override
	public String indefinite() {
		return food.getQuantity() > 1
				? food.getQuantity() + " servings of " + baseName
				: "a serving of " + baseName;
	}

	public FoodName(String name, Food food) {
		super(name);
		this.food = food;
	}
	
	private Food food;
}
