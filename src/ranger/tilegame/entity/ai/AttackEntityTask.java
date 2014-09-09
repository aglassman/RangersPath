package ranger.tilegame.entity.ai;

import ranger.tilegame.entity.Arrow;
import ranger.tilegame.entity.PhysicalEntity;
import ranger.tilegame.location.TiledLocation;

public class AttackEntityTask extends AITask {
    public void act(TiledLocation location) {
        if (--countdown == 0) {
            countdown = 10; // TODO the owning entity needs to be in charge of this
            double theta = Math.atan2(target.getY() - owner.getY(), target.getX() - owner.getX());
            int speed = 10;
            int x = (int)(speed * Math.cos(theta));
            int y = (int)(speed * Math.sin(theta));
            location.addProjectile(new Arrow(owner, owner.getX(), owner.getY()-30, x, y, 10));
        }
    }

    public AttackEntityTask(PhysicalEntity owner, PhysicalEntity target) {
        this.owner = owner;
        this.target = target;
        countdown = 10;
    }

    private PhysicalEntity owner;
    private PhysicalEntity target;
    private int countdown;
}
