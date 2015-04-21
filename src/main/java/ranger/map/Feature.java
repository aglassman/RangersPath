package ranger.map;

import ranger.item.Inventory;
import ranger.name.Name;
import ranger.name.Named;

public class Feature implements Named {

	public Inventory getInventory() {
		return inventory;
	}
	
	public Name getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Feature(Name name, String description) {
		this.name = name;
		this.description = description;
		inventory = new Inventory();
	}
	
	protected Name name;
	protected String description;
	protected Inventory inventory;
}
