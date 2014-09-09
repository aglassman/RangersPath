package ranger.tilegame.entity;

import jmotion.tilegame.model.Physical;
import ranger.tilegame.location.TiledLocation;

public class Arrow extends PhysicalEntity {

    public static final int ARROW_SPEED = 20;

    public final int damage;
    public final int speed;

    @Override
    public void act(TiledLocation location) {
        location.movePhysical(this, dx, dy);

        if (!location.contains(x, y)) {
            location.removeLater(this);
        } else {
            for (Physical collided : location.getSpace().getCollisions(this)) {
                if (collided == owner || collided instanceof Arrow)
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

    public void setVelocity(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Arrow(PhysicalEntity owner, int x, int y, int damage, int speed) {
        super(null); // There is no Entity for an Arrow
        setLocation(x, y);
        this.owner = owner;
        this.damage = damage;
        this.speed = speed;
    }

    private PhysicalEntity owner;
    private int dx;
    private int dy;
}
