package ranger.item;

import java.util.LinkedList;
import java.util.List;

import ranger.name.Name;

public class Inventory {
	public List<Item> getItems() {
		return items;
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	public void addItem(Item i) {
		if (i instanceof Food)
			addServings((Food)i);
		else
			items.add(i);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}

	public void reduceServing(Food food) {
		food.consume();
		if (food.getServings() == 0)
			items.remove(food);
	}

	public void addServings(Food food) {
		Item otherFood = getItem(food.name.basic().toLowerCase());
		if (otherFood == null || !(otherFood instanceof Food)) {
			items.add(food);
		} else {
			((Food)otherFood).addServings(food.getServings());
		}
	}
	
	public Item getItem(String name) {
		return Name.getByName(items, name);
	}
	
	public Inventory() {
		items = new LinkedList<>();
	}
	
	private LinkedList<Item> items;
}
