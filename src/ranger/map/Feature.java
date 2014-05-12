package ranger.map;

import ranger.item.Inventory;

public class Feature {

	public Inventory getInventory() {
		return inventory;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Feature(String name, String description) {
		this.name = name;
		this.description = description;
		inventory = new Inventory();
	}
	
	private String name;
	private String description;
	private Inventory inventory;
}
