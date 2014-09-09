package ranger.tilegame.entity.task;

import ranger.tilegame.entity.Arrow;
import ranger.tilegame.entity.PhysicalEntity;
import ranger.tilegame.location.TiledLocation;

public class AttackEntityTask extends EntityTask {
    public void act(TiledLocation location) {
        if (owner.canAttack()) {
            Arrow arrow = owner.fireRangedEquip();
            location.addProjectile(arrow);

            double theta = Math.atan2(target.getY() - owner.getY(), target.getX() - owner.getX());
            int speed = 10;
            int dx = (int)(speed * Math.cos(theta));
            int dy = (int)(speed * Math.sin(theta));
            arrow.setVelocity(dx, dy);
        }
    }

    public AttackEntityTask(PhysicalEntity owner, PhysicalEntity target) {
        this.owner = owner;
        this.target = target;
    }

    private PhysicalEntity owner;
    private PhysicalEntity target;
}
