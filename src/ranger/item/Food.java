package ranger.item;

public class Food extends Item {
	
	public String toString() {
		return servings > 1 
				? servings + " servings of " + name
				: "A serving of " + name;
	}
	
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
	
	public Food(String name, int foodValue, int servings) {
		super(name);
		this.foodValue = foodValue;
		this.servings = servings;
	}
	
	private int foodValue;
	private int servings;
}
