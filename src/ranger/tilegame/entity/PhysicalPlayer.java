package ranger.tilegame.entity;

import jmotion.tilegame.model.Physical;
import ranger.entity.Entity;
import ranger.tilegame.PhysicalItem;
import ranger.tilegame.location.TiledLocation;

import java.awt.*;

public class PhysicalPlayer extends PhysicalEntity {

    @Override
    public void act(TiledLocation location) {
        --equipedWeaponCooldown;
        location.tryEntityWalk(this, dx, dy);
        if (willAttack && canAttack()) {
            Point velocity = facing.project(10);
            Arrow arrow = fireRangedEquip();
            location.addProjectile(arrow);
            arrow.setVelocity(velocity.x, velocity.y);
        }

        willAttack = false;

        for (Physical collided : location.getSpace().getCollisions(this)) {
            if (collided instanceof PhysicalItem) {
                // Remove this item from the 2D and base worlds
                PhysicalItem item = (PhysicalItem)collided;
                location.removeItem(item);

                // Give the item to the player
                entity.getInventory().addItem(item.item);
                System.out.println("Player collected " + item.item);
            }
        }
    }

    public void setWillAttack() {
        willAttack = true;
    }

    public void setMovement(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public PhysicalPlayer(Entity entity) {
        super(entity);
    }

    private int dx;
    private int dy;
    private boolean willAttack;
}
