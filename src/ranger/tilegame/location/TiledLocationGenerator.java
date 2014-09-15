package ranger.tilegame.location;

import mapgen.map.HeightMap;
import mapgen.map.MapGenerator;
import mapgen.map.voronoi.VoronoiContinent;
import ranger.entity.Entity;
import ranger.item.Item;
import ranger.map.Feature;
import ranger.map.Location;
import ranger.tilegame.PhysicalItem;
import ranger.tilegame.TiledGame;
import ranger.tilegame.entity.PhysicalEntity;
import ranger.tilegame.entity.task.RangedAttackTask;
import ranger.tilegame.object.Campfire;

import java.awt.*;
import java.util.Random;

public class TiledLocationGenerator {
    public TiledLocation generate(Location location) {
        TiledLocation tiled = new TiledLocation(game, location);
        MapGenerator generator = new VoronoiContinent(30);
        Random seededRandom = new Random(location.getSeed());
        HeightMap heightMap = generator.generate(seededRandom, tiled.WIDTH, tiled.HEIGHT);

        TiledTerrain[] terrains;
        float[] cutoffs;

        switch (location.getTerrainType()) {
            case PLAINS:
                terrains = new TiledTerrain[]{TiledTerrain.GRASS, TiledTerrain.UNDERBRUSH};
                cutoffs = new float[] {0.2f};
                break;
            case FOREST:
                terrains = new TiledTerrain[]{TiledTerrain.GRASS, TiledTerrain.UNDERBRUSH};
                cutoffs = new float[] {0.6f};
                break;
            case HILLSIDE:
                terrains = new TiledTerrain[]{TiledTerrain.GRASS, TiledTerrain.ROCKS};
                cutoffs = new float[] {0.5f};
                break;
            default:
                terrains = new TiledTerrain[0];
                cutoffs = new float[0];
        }

        fillLocation(tiled, heightMap, terrains, cutoffs);

        // Add PhysicalEntities wrapping the Entities
        for (Entity e : location.getEntities()) {
            PhysicalEntity p = new PhysicalEntity(e);

            p.setTask(new RangedAttackTask(p, game.getPlayer()));

            // TODO this position should be based on some saved state or activity history, not randomly
            int x = (int)(Math.random() * tiled.REAL_WIDTH);
            int y = (int)(Math.random() * tiled.REAL_HEIGHT);
            p.setLocation(x, y);
            tiled.addPhysicalEntity(p);
        }

        // Flesh out any Features here
        for (Feature f : location.getFeatures())
            generateFeature(f, tiled, seededRandom);

        return tiled;
    }

    public TiledLocationGenerator(TiledGame game) {
        this.game = game;
    }

    private void fillLocation(TiledLocation tiled, HeightMap map, TiledTerrain[] terrains, float[] cutoffs) {
        for (int row = 0; row<tiled.HEIGHT; ++row) {
            for (int col = 0; col<tiled.WIDTH; ++col) {
                float height = map.get(row, col);
                int i = 0;
                for (float f : cutoffs) {
                    if (height > f)
                        break;
                    ++i;
                }
                tiled.get(row, col).terrain = terrains[i];
            }
        }
    }

    private void generateFeature(Feature feature, TiledLocation location, Random random) {
        // TODO this campfire is a placeholder
        Campfire campfire = new Campfire();

        Point position = randomCoords(location, random);
        campfire.setLocation(position.x, position.y);
        location.addPhysical(campfire);

        for (Item item : feature.getInventory().getItems()) {
            Point itemLocation = randomCoords(position, 3*location.TILE_WIDTH, location, random);
            PhysicalItem physicalItem = new PhysicalItem(item);
            physicalItem.setLocation(itemLocation.x, itemLocation.y);
            location.addPhysical(physicalItem);
        }
    }

    private Point randomCoords(TiledLocation location, Random random) {
        int x = random.nextInt(location.REAL_WIDTH);
        int y = random.nextInt(location.REAL_HEIGHT);

        return new Point(x, y);
    }

    private Point randomCoords(Point origin, int maxRadius, TiledLocation location, Random random) {
        Point p;
        do {
            int xOffset = random.nextInt(2*maxRadius + 1) - maxRadius;
            int yOffset = random.nextInt(2*maxRadius + 1) - maxRadius;
            p = new Point(origin.x + xOffset, origin.y + yOffset);
        } while (!location.contains(p));

        return p;
    }

    private TiledGame game;
}
