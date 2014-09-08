package ranger.tilegame;

import ranger.entity.Entity;

import java.awt.*;

public class PhysicalPlayer extends PhysicalEntity {

    @Override
    public void act(TiledLocation location) {
        location.tryEntityWalk(this, dx, dy);
        if (willAttack) {
            Point move = facing.project(10);
            location.addProjectile(new Arrow(x, y, move.x, move.y));
        }

        willAttack = false;
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
