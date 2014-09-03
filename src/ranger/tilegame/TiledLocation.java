package ranger.tilegame;

import jmotion.tilegame.model.Map;
import jmotion.tilegame.model.PhysicalSpace;
import ranger.entity.Entity;
import ranger.map.Location;

import java.util.LinkedList;
import java.util.List;

public class TiledLocation extends Map<GameTile> {

    public final int REAL_WIDTH; // Total width of the space in pixels
    public final int REAL_HEIGHT; // Total height of the space in pixels

    public Iterable<PhysicalEntity> getEntities() {
        return entities;
    }

    public void addPhysicalEntity(PhysicalEntity entity) {
        entities.add(entity);
        space.addPhysical(entity);
    }

    public TiledLocation(Location location) {
        super(25, 25, 30);

        REAL_WIDTH = WIDTH * TILE_WIDTH;
        REAL_HEIGHT = HEIGHT * TILE_WIDTH;
        space = new PhysicalSpace(REAL_WIDTH, REAL_HEIGHT);

        for (int row = 0; row<HEIGHT; ++row) {
            for (int col = 0; col<WIDTH; ++col) {
                set(new GameTile(), row, col);
            }
        }

        entities = new LinkedList<>();
    }

    private PhysicalSpace space;
    private List<PhysicalEntity> entities;
}
