package ranger.map;

import ranger.hunting.HuntManager;
import ranger.item.Item;
import ranger.name.Name;

public class Generator {
	public static Region newRegion() {
		Region region = new Region();
		
		for (int i = 0; i<100; ++i)
			region.addLocation(newLocation());
		
		return region;
	}
	
	private static Location newLocation() {
		Location location = new Location(new Name("forest"), "The forest stretches as far as your eye can see.");
		
		// Add some features
		if (Math.random() > 0.1)
			location.addFeature(new Feature(new Name("Orc camp"), "It looks like the bastards cleared out of here long ago."));
		
		// Add some items to the features
		for (Feature f : location.getFeatures())
			addItems(f);
		
		return location;
	}
	
	private static void addItems(Feature f) {
		if (Math.random() < 0.3)
			f.getInventory().addItem(HuntManager.getRabbit());
		if (Math.random() < 0.4)
			f.getInventory().addItem(HuntManager.getQuail());
		if (Math.random() < 0.3)
			f.getInventory().addItem(new Item("arrowhead", (int)Math.floor(Math.random()*20)));
	}
}
