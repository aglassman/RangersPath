package ranger.map;

import mapgen.map.HeightMap;
import mapgen.map.MapGenerator;
import mapgen.map.voronoi.VoronoiContinent;
import ranger.Game;
import ranger.entity.Bear;
import ranger.entity.Entity;
import ranger.hunting.HuntManager;
import ranger.item.Item;
import ranger.item.weapon.Ammo;
import ranger.item.weapon.Weapon;
import ranger.name.Name;

import java.util.Random;

public class Generator {
	public static Region newRegion(Game game) {
		int width = 30;
		int height = 30;

        Random dice = new Random();

        // Generate a height map
        MapGenerator generator = new VoronoiContinent(50);
        HeightMap map = generator.generate(dice, width, height);

		Region region = new Region(width, height);
		for (int col = 0; col<width; ++col) {
            for (int row = 0; row<height; ++row) {
                Location location;

                float h = map.get(row, col);
                if (h > 0.7)
                    location = new Location(new Name("stony hill"), TerrainType.HILLSIDE, "A stony outcropping rises above the surrounding countryside.", col, row);
                else if (h > 0.2)
                    location = new Location(new Name("forest"), TerrainType.FOREST, "The trees stretch high overhead, and dense underbrush provides cover.", col, row);
                else
                    location = new Location(new Name("grassland"), TerrainType.PLAINS, "Green grass waves accross the open plain.", col, row);

                region.setLocation(location, col, row);
                addFeatures(game, location, dice);
            }
        }
		
		return region;
	}
	
	private static Location addFeatures(Game game, Location location, Random dice) {
		// Add some features
		if (dice.nextDouble() > 0.1)
			location.addFeature(new Feature(new Name("Orc camp"), "It looks like the bastards cleared out of here long ago."));

        // Add some enemies
        if (dice.nextDouble() < 0.1) {
            game.addEntity(getEntity(), location);
        }

        // Add some bears
        if (dice.nextDouble() < 0.1) {
            game.addEntity(new Bear(), location);
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
