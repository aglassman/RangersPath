package ranger.tilegame;

import map.HeightMap;
import map.MapGenerator;
import map.voronoi.VoronoiContinent;
import ranger.entity.Entity;
import ranger.map.Location;

import java.util.Random;

public class TiledLocationGenerator {
    public TiledLocation generate(Location location) {
        TiledLocation tiled = new TiledLocation(location);
        MapGenerator generator = new VoronoiContinent(30);
        HeightMap heightMap = generator.generate(new Random(), tiled.WIDTH, tiled.HEIGHT);

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

        for (Entity e : location.getEntities()) {
            PhysicalEntity p = new PhysicalEntity(e);
            // TODO this position should be based on some saved state or activity history, not randomly
            int x = (int)(Math.random() * tiled.REAL_WIDTH);
            int y = (int)(Math.random() * tiled.REAL_HEIGHT);
            p.setLocation(x, y);
            tiled.addPhysicalEntity(p);
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
