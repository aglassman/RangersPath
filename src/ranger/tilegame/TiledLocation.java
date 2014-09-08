package ranger.tilegame;

import jmotion.tilegame.model.Map;
import jmotion.tilegame.model.Physical;
import ranger.map.Location;

import java.awt.*;
import java.util.LinkedList;

public class TiledLocation extends Map<GameTile> {

    public final int REAL_WIDTH; // Total width of the space in pixels
    public final int REAL_HEIGHT; // Total height of the space in pixels

    public void frameTick() {
        // TODO each Entity can act during the frame

        for (Physical collided : space.getCollisions(game.getPlayer())) {
            System.out.println(collided);
            if (collided instanceof PhysicalItem) {
                // Remove this item from the 2D and base worlds
                PhysicalItem item = (PhysicalItem)collided;
                space.removePhysical(item);
                location.getItem(item.item);

                // Give the item to the player
                game.getPlayer().entity.getInventory().addItem(item.item);
                System.out.println("Player collected " + item.item);
            }
        }
    }

    public Iterable<Physical> getPhysicals() {
        return space.getAllPhysicals();
    }

    public void addPhysicalEntity(PhysicalEntity entity) {
        space.addPhysical(entity);
        entities.add(entity);
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

    /**
     * Whether a point (in pixel-space, not a tile coordinate) is contained within the bounds
     */
    public boolean contains(Point p) {
        return p.x >= 0 && p.x < REAL_WIDTH
            && p.y >= 0 && p.y < REAL_HEIGHT;
    }

    public TiledLocation(TiledGame game, Location location) {
        super(20, 20, 40);
        this.location = location;
        this.game = game;

        visibility = new Visibility(this);
        entities = new LinkedList<>();

        REAL_WIDTH = WIDTH * TILE_WIDTH;
        REAL_HEIGHT = HEIGHT * TILE_WIDTH;

        for (int row = 0; row<HEIGHT; ++row) {
            for (int col = 0; col<WIDTH; ++col) {
                set(new GameTile(), row, col);
            }
        }
    }

    private Visibility visibility;
    private LinkedList<PhysicalEntity> entities;
    private Location location;
    private TiledGame game;
}
