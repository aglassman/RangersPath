package ranger.tilegame.entity;

import jmotion.tilegame.model.Physical;
import ranger.entity.Entity;
import ranger.map.Direction;
import ranger.tilegame.entity.task.EntityTask;
import ranger.tilegame.location.TiledLocation;

public class PhysicalEntity extends Physical {

    public final Entity entity;
    public int walkSpeed;

    public Direction facing;
    public int xMoved; // amount moved this frame in x
    public int yMoved; // amount moved this frame in y

    public void setTask(EntityTask task) {
        this.task = task;
    }

    /**
     * Perform any action in a single frame time
     */
    public void act(TiledLocation location) {
        --equipedWeaponCooldown;
        if (task != null)
            task.act(location);
    }

    public Arrow fireRangedEquip() {
        equipedWeaponCooldown = entity.getEquip().getCooldown();
        entity.getAmmo().adjustQuantity(-1);
        return new Arrow(this, x, y-30, entity.getEquip().getDamage(), Arrow.ARROW_SPEED);
    }

    public boolean canAttack() {
        return entity.getEquip() != null
            && (!entity.getEquip().isRanged() || entity.getAmmo() != null && entity.getAmmo().getQuantity() > 0)
            && equipedWeaponCooldown <= 0;
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

    protected EntityTask task;
    protected int equipedWeaponCooldown;
}
