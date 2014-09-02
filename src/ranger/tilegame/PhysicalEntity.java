package ranger.tilegame;

import jmotion.tilegame.model.Physical;
import ranger.entity.Entity;

public class PhysicalEntity extends Physical {

    public final Entity entity;
    public int walkSpeed;

    public PhysicalEntity(Entity entity, TiledLocation location) {
        this.entity = entity;

        walkSpeed = 4;

        x = (int)(Math.random() * location.REAL_WIDTH);
        y = (int)(Math.random() * location.REAL_HEIGHT);
    }
}
