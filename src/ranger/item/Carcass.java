package ranger.item;

import java.util.LinkedList;
import java.util.List;

import ranger.name.Name;


public class Carcass extends Item {
	
	public List<Item> clean() {
		List<Item> produce = new LinkedList<>();
		produce.add(new Food(animalName, foodValue, servings));
		if (producesHide) 
			produce.add(new Item(new Name(animalName + " skin")));

		return produce;
	}
	
	public String getAnimalName() {
		return animalName;
	}

	public Carcass(String animalName, int foodValue, int servings, boolean producesHide) {
		super(animalName + " carcass", 1);
		this.animalName = animalName;
		this.foodValue = foodValue;
		this.servings = servings;
		this.producesHide = producesHide;
	}

	private String animalName;
	private int servings;
	private int foodValue;
	private boolean producesHide;
}
