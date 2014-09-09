package ranger.tilegame.location;

import jmotion.tilegame.model.Map;
import jmotion.tilegame.model.Physical;
import ranger.map.Direction;
import ranger.map.Location;
import ranger.tilegame.PhysicalItem;
import ranger.tilegame.TiledGame;
import ranger.tilegame.entity.PhysicalEntity;
import ranger.tilegame.entity.PhysicalPlayer;

import java.awt.*;
import java.util.LinkedList;

public class TiledLocation extends Map<GameTile> {

    public final int REAL_WIDTH; // Total width of the space in pixels
    public final int REAL_HEIGHT; // Total height of the space in pixels

    public void addProjectile(Physical projectile) {
        addPhysicalLater(projectile);
    }

    public void removeItem(PhysicalItem item) {
        location.getItem(item.item);
        removeLater(item);
    }

    public void frameTick() {
        newPhysicals.clear();
        removeLater.clear();

        // Allow each entity to act
        for (PhysicalEntity entity : entities)
            entity.act(this);

        // Check if the player has moved to another Location
        PhysicalPlayer player = game.getPlayer();
        if (player.getX() < 0)
            game.movePlayer(Direction.WEST);
        else if (player.getX() >= REAL_WIDTH)
            game.movePlayer(Direction.EAST);
        if (player.getY() < 0)
            game.movePlayer(Direction.NORTH);
        else if (player.getY() >= REAL_HEIGHT)
            game.movePlayer(Direction.SOUTH);

        // Stop performing frame actions if the Location did change
        if (game.getCurrentLocation() != this)
            return;

        // Add any new objects created during this action (arrows, etc)
        for (Physical p : newPhysicals) {
            if (p instanceof PhysicalEntity)
                addPhysicalEntity((PhysicalEntity)p);
            else
                addPhysical(p);
        }

        // Remove any entities that died this frame
        for (PhysicalEntity entity : entities) {
            // Hack: arrows have no entity
            if (entity.entity != null && entity.entity.isDead())
                removeLater.add(entity);
        }

        // Remove any objects marked for removal this framed
        for (Physical toRemove : removeLater) {
            space.removePhysical(toRemove);
            entities.remove(toRemove); // no worries if it's not there
        }
    }

    public Iterable<Physical> getNewPhysicals() {
        return newPhysicals;
    }

    public Iterable<Physical> getPhysicals() {
        return space.getAllPhysicals();
    }

    public void addPhysicalLater(Physical p) {
        newPhysicals.add(p);
    }

    public void addPhysicalEntity(PhysicalEntity entity) {
        space.addPhysical(entity);
        entities.add(entity);
    }

    public void removeLater(Physical physical) {
        removeLater.add(physical);
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

    /**
     * Whether a point (in pixel-space, not a tile coordinate) is contained within the bounds
     */
    public boolean contains(int x, int y) {
        return x >= 0 && x < REAL_WIDTH
                && y >= 0 && y < REAL_HEIGHT;
    }

    public TiledLocation(TiledGame game, Location location) {
        super(20, 20, 40);
        this.location = location;
        this.game = game;

        visibility = new Visibility(this);
        entities = new LinkedList<>();
        removeLater = new LinkedList<>();
        newPhysicals = new LinkedList<>();

        REAL_WIDTH = WIDTH * TILE_WIDTH;
        REAL_HEIGHT = HEIGHT * TILE_WIDTH;

        for (int row = 0; row<HEIGHT; ++row) {
            for (int col = 0; col<WIDTH; ++col) {
                set(new GameTile(), row, col);
            }
        }
    }

    private void doCollision(Physical a, Physical b) {

    }

    private Visibility visibility;
    private LinkedList<PhysicalEntity> entities;
    private LinkedList<Physical> removeLater;
    private LinkedList<Physical> newPhysicals;
    private Location location;
    private TiledGame game;
}
