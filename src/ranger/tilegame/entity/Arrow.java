package ranger.tilegame.entity;

import jmotion.tilegame.model.Physical;
import ranger.tilegame.location.TiledLocation;

public class Arrow extends PhysicalEntity {

    @Override
    public void act(TiledLocation location) {
        location.movePhysical(this, dx, dy);

        if (!location.contains(x, y)) {
            location.removeLater(this);
        } else {
            for (Physical collided : location.getSpace().getCollisions(this)) {
                if (collided == owner)
                    continue;
                if (collided instanceof PhysicalEntity) {
                    PhysicalEntity entity = (PhysicalEntity)collided;
                    entity.entity.takeDamage(damage);
                }
                location.removeLater(this);
                break; // Should only hit one object!
            }
        }
    }

    public Arrow(PhysicalEntity owner, int x, int y, int dx, int dy, int damage) {
        super(null); // There is no Entity for an Arrow
        setLocation(x, y);
        this.owner = owner;
        this.dx = dx;
        this.dy = dy;
        this.damage = damage;
    }

    private PhysicalEntity owner;
    private int dx;
    private int dy;
    private int damage;
}
