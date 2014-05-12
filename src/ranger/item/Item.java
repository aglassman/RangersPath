package ranger.item;

import ranger.name.Name;
import ranger.name.Named;
import ranger.name.StackableItemName;

public class Item implements Named {
	
	public Name getName() {
		return name;
	}
	
	public boolean isStackable() {
		return stackable;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void adjustQuantity(int adjustment) {
		quantity += adjustment;
	}
	
	public Item(String name, int quantity) {
		this.name = new StackableItemName(name, this);
		this.quantity = quantity;
		stackable = true;
	}
	
	public Item(Name name) {
		this.name = name;
		stackable = false;
	}
	
	protected Item() {
		
	}
	
	protected Name name;
	protected boolean stackable;
	protected int quantity;
}
