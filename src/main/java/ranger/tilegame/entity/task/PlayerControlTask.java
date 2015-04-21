package ranger.tilegame.entity.task;

import game.engine.io.UserInterface;
import jmotion.tilegame.model.Physical;
import ranger.Game;
import ranger.tilegame.PhysicalItem;
import ranger.tilegame.entity.Arrow;
import ranger.tilegame.entity.PhysicalEntity;
import ranger.tilegame.location.TiledLocation;

import java.awt.*;

public class PlayerControlTask extends EntityTask {

    public void setMovement(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setWillAttack() {
        willAttack = true;
    }

    public void act(TiledLocation location) {
        location.tryEntityWalk(player, dx, dy);

        if (willAttack && player.canAttack()) {
            Point velocity = player.facing.project(10);
            Arrow arrow = player.fireRangedEquip();
            location.addProjectile(arrow);
            arrow.setVelocity(velocity.x, velocity.y);
        }

        willAttack = false;

        // Look for collectibles the player collects
        for (Physical collided : location.getSpace().getCollisions(player)) {
            if (collided instanceof PhysicalItem) {
                // Remove this item from the 2D and base worlds
                PhysicalItem item = (PhysicalItem)collided;
                location.removeItem(item);

                // Give the item to the player
                player.entity.getInventory().addItem(item.item);
                ui.output.println("Player collected " + item.item);
            }
        }
    }

    public PlayerControlTask(UserInterface ui, PhysicalEntity player) {
    	this.ui = ui;
        this.player = player;
        player.setTask(this);
    }

    private UserInterface ui;
    private PhysicalEntity player;
    private boolean willAttack;
    private int dx;
    private int dy;
}
