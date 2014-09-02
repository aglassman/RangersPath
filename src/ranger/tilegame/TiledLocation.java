package ranger.tilegame;

import jmotion.tilegame.model.Map;
import jmotion.tilegame.model.PhysicalSpace;
import ranger.Game;
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

    public PhysicalEntity getPlayerEntity() {
        return player;
    }

    public void addPhysicalEntity(PhysicalEntity entity) {
        entities.add(entity);
        space.addPhysical(entity);

        if (entity.entity == game.getPlayer())
            player = entity;
    }

    public TiledLocation(Game game, Location location) {
        super(25, 25, 30);
        this.game = game;

        REAL_WIDTH = WIDTH * TILE_WIDTH;
        REAL_HEIGHT = HEIGHT * TILE_WIDTH;
        space = new PhysicalSpace(REAL_WIDTH, REAL_HEIGHT);

        for (int row = 0; row<HEIGHT; ++row) {
            for (int col = 0; col<WIDTH; ++col) {
                set(new GameTile(), row, col);
            }
        }

        entities = new LinkedList<>();
        for (Entity e : location.getEntities()) {
            addPhysicalEntity(new PhysicalEntity(e, this));
        }
    }

    private Game game;
    private PhysicalSpace space;
    private List<PhysicalEntity> entities;
    private PhysicalEntity player;
}
