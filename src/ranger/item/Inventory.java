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
		if (i.isStackable()) {
			Item existingItem = getItem(i.getName().basic());
			if (existingItem == null)
				items.add(i);
			else
				existingItem.adjustQuantity(i.getQuantity());
		} else {
			items.add(i);
		}
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}

	public void expend(Item item) {
		if (item.isStackable()) {
			item.adjustQuantity(-1);
			if (item.getQuantity() == 0)
				items.remove(item);
		} else {
			items.remove(item);
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
