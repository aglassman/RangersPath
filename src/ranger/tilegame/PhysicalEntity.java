package ranger.tilegame;

import jmotion.tilegame.model.Physical;
import ranger.entity.Entity;
import ranger.map.Direction;

public class PhysicalEntity extends Physical {

    public final Entity entity;
    public int walkSpeed;

    public Direction facing;
    public int xMoved; // amount moved this frame in x
    public int yMoved; // amount moved this frame in y

    /**
     * Perform any action in a single frame time
     */
    public void act(TiledLocation location) {
    }

    public void tryWalk(int dx, int dy) {
        xMoved = dx;
        yMoved = dy;

        Direction dirMoved = Direction.getDirection(dx, dy);
        if (dirMoved != null)
            facing = dirMoved;
    }

    public PhysicalEntity(Entity entity) {
        this.entity = entity;

        walkSpeed = 6;
        facing = Direction.SOUTH; // TODO this should be randomized, by the generator
    }
}
