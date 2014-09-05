package ranger.tilegame;

import jmotion.tilegame.model.Map;
import jmotion.tilegame.model.PhysicalSpace;
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

    public void tryEntityWalk(PhysicalEntity entity, int dx, int dy) {
        GameTile tile = get(getCoord(entity.getX(), entity.getY()));
        int difficulty = tile.terrain.difficulty;

        // Pass the desired move to the entity before we modify it
        // Their animation should express the intent of their movement, not the result
        entity.tryWalk(dx, dy);

        if (dx > 0)
            dx = Math.max(0, dx - difficulty);
        else
            dx = Math.min(0, dx + difficulty);
        if (dy > 0)
            dy = Math.max(0, dy - difficulty);
        else
            dy = Math.min(0, dy + difficulty);

        space.moveObject(entity, dx, dy);
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public TiledLocation(Location location) {
        super(20, 20, 40);

        visibility = new Visibility(this);

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
    private Visibility visibility;
}
