package ranger.map;

import ranger.Game;
import ranger.entity.Entity;
import ranger.hunting.HuntManager;
import ranger.item.Item;
import ranger.item.weapon.Weapon;
import ranger.name.Name;

public class Generator {
	public static Region newRegion(Game game) {
		Region region = new Region();
		
		for (int i = 0; i<100; ++i)
			addLocation(region, game);
		
		return region;
	}
	
	private static void addLocation(Region region, Game game) {
		Location location = new Location(new Name("forest"), "The forest stretches as far as your eye can see.");
		
		// Add some features
		if (Math.random() > 0.1)
			location.addFeature(new Feature(new Name("Orc camp"), "It looks like the bastards cleared out of here long ago."));
		
		// Add some entities
		if (Math.random() > 0.1) {
			game.addEntity(getEntity(), location);
		}
		
		// Add some items to the features
		for (Feature f : location.getFeatures())
			addItems(f);
		
		region.addLocation(location);
	}
	
	private static Entity getEntity() {
		Entity goblin = new Entity(new Name("Goblin"));
		Weapon shortSword = new Weapon(new Name("short sword"));
		goblin.setEquip(shortSword);
		if (Math.random() > 0.5)
			goblin.setWeaponDrawn(true);
		goblin.getInventory().addItem(shortSword);
		
		return goblin;
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
