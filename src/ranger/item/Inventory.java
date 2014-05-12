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
		items.add(i);
	}
	
	public void reduceServing(Food food) {
		food.consume();
		if (food.getServings() == 0)
			items.remove(food);
	}
	
	public Item getItem(String name) {
		for (Item i : items) {
			if (i.name.equals(name))
				return i;
		}
		return null;
	}
	
	public Inventory() {
		items = new LinkedList<>();
	}
	
	private LinkedList<Item> items;
}
