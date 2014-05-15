package ranger.map;

import ranger.Game;
import ranger.entity.Entity;
import ranger.hunting.HuntManager;
import ranger.item.Item;
import ranger.item.weapon.Ammo;
import ranger.item.weapon.Weapon;
import ranger.name.Name;

public class Generator {
	public static Region newRegion(Game game) {
		int width = 30;
		int height = 30;
		Region region = new Region(width, height);
		
		for (int x = 0; x<width; ++x) {
			for (int y = 0; y<height; ++y) {
				Location l = addLocation(game, x, y);
				region.setLocation(l, x, y);
			}
		}
		
		return region;
	}
	
	private static Location addLocation(Game game, int x, int y) {
		Location location;
		double typeDice = Math.random();
		if (typeDice > 0.5)
			location = new Location(new Name("forest"), TerrainType.FOREST, "The forest stretches as far as your eye can see, and dense underbrush provides cover.", x, y);
		else if (typeDice > 0.2)
			location = new Location(new Name("stony hill"), TerrainType.HILLSIDE, "A stony outcropping rises above the surrounding countryside.", x, y);
		else
			location = new Location(new Name("grassland"), TerrainType.PLAINS, "Green grass rolls accross a grassy plain.", x, y);
		
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
		
		return location;
	}
	
	private static Entity getEntity() {
		Entity goblin = new Entity(new Name("Goblin"), true);
		
		if (Math.random() > 0.4) {
			Weapon shortSword = new Weapon(new Name("short sword"), 20);
			goblin.getInventory().addItem(shortSword);
			goblin.setEquip(shortSword);
		} else {
			Weapon bow = new Weapon(new Name("bow"), 10, true);
			goblin.getInventory().addItem(bow);
			goblin.setEquip(bow);
			Ammo arrows = new Ammo("arrow", 3, (int)(Math.random() * 6));
			goblin.getInventory().addItem(arrows);
			goblin.setAmmo(arrows);
		}		
		
		if (Math.random() > 0.5)
			goblin.setWeaponDrawn(true);
		
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
