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
	
	public Inventory() {
		items = new LinkedList<>();
	}
	
	private LinkedList<Item> items;
}
