package ranger.item;

import java.util.LinkedList;

public class Inventory {
	public Iterable<Item> getItems() {
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
		Item otherFood = getItem(food.name);
		if (otherFood == null || !(otherFood instanceof Food)) {
			items.add(food);
		} else {
			((Food)otherFood).addServings(food.getServings());
		}
	}
	
	public Item getItem(String name) {
		for (Item i : items) {
			if (i.name.toLowerCase().equals(name.toLowerCase()))
				return i;
		}
		return null;
	}
	
	public Inventory() {
		items = new LinkedList<>();
	}
	
	private LinkedList<Item> items;
}
