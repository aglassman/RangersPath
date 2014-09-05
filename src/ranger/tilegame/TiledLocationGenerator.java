package ranger.tilegame;

import jmotion.tilegame.model.Physical;
import mapgen.map.HeightMap;
import mapgen.map.MapGenerator;
import mapgen.map.voronoi.VoronoiContinent;
import ranger.entity.Entity;
import ranger.map.Feature;
import ranger.map.Location;
import ranger.tilegame.object.Campfire;

import java.util.Random;

public class TiledLocationGenerator {
    public TiledLocation generate(Location location) {
        TiledLocation tiled = new TiledLocation(location);
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
            // TODO this position should be based on some saved state or activity history, not randomly
            int x = (int)(Math.random() * tiled.REAL_WIDTH);
            int y = (int)(Math.random() * tiled.REAL_HEIGHT);
            p.setLocation(x, y);
            tiled.addPhysicalEntity(p);
        }

        // Flesh out any Features here
        for (Feature f : location.getFeatures()) {
            // TODO this campfire is a placeholder
            Campfire campfire = new Campfire();
            int x = (int)(Math.random() * tiled.REAL_WIDTH);
            int y = (int)(Math.random() * tiled.REAL_HEIGHT);
            campfire.setLocation(x, y);
            tiled.addPhysical(campfire);
        }

        return tiled;
    }

    public TiledLocationGenerator() {
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
}
