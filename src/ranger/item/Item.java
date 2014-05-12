package ranger.item;

public class Item {
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	public Item(String name) {
		this.name = name;
	}
	
	protected final String name;
}
