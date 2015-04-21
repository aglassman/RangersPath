package ranger.name;

import ranger.item.Item;

public class StackableItemName extends Name {

	@Override
	public String definite() {
		return "the " + baseName;
	}

	@Override
	public String indefinite() {
		return item.getQuantity() > 1 
				? item.getQuantity() + " " + pluralize(baseName)
				: indefiniteSingular(baseName);
	}

	public StackableItemName(String name, Item item) {
		super(name);
		this.item = item;
	}
	
	private Item item;
}
