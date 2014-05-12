package ranger.item;


public class Carcass extends Item {
	
	public Item[] clean() {
		if (producesHide) {
			return new Item[]{
					new Food(animalName, foodValue, servings),
					new Item(animalName + " Skin")
			};
		}

		return new Item[]{
				new Food(animalName, foodValue, servings)
		};
	}
	
	public String getAnimalName() {
		return animalName;
	}

	public Carcass(String animalName, int foodValue, int servings, boolean producesHide) {
		super(animalName + " carcass");
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
