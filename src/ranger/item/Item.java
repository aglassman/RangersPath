package ranger.item;

import ranger.name.Name;
import ranger.name.Named;

public class Item implements Named {
	
	public Name getName() {
		return name;
	}
	
	public Item(Name name) {
		this.name = name;
	}
	
	protected final Name name;
}
