
package ranger.ui;

import jmotion.tilegame.TileScreenPanel;
import jmotion.tilegame.model.Map;
import ranger.Game;
import ranger.entity.Bear;
import ranger.tilegame.GameTile;
import ranger.tilegame.PhysicalEntity;
import ranger.tilegame.TiledLocation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RangerTileUI extends TileScreenPanel<GameTile> {

    public RangerTileUI(Game game) {
        super(30);
        this.game = game;

        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent keyEvent) {
            }
            public void keyPressed(KeyEvent keyEvent) {
                keyRight |= keyEvent.getKeyCode() == KeyEvent.VK_RIGHT;
                keyLeft |= keyEvent.getKeyCode() == KeyEvent.VK_LEFT;
                keyUp |= keyEvent.getKeyCode() == KeyEvent.VK_UP;
                keyDown |= keyEvent.getKeyCode() == KeyEvent.VK_DOWN;
            }
            public void keyReleased(KeyEvent keyEvent) {
                keyRight &= keyEvent.getKeyCode() != KeyEvent.VK_RIGHT;
                keyLeft &= keyEvent.getKeyCode() != KeyEvent.VK_LEFT;
                keyUp &= keyEvent.getKeyCode() != KeyEvent.VK_UP;
                keyDown &= keyEvent.getKeyCode() != KeyEvent.VK_DOWN;
            }
        });
    }

    public void setMap(TiledLocation location) {
        super.setMap(location);
        this.location = location;

        // TODO create sprites for each of the entities
    }

    protected void drawTile(Graphics2D g, int x, int y, int row, int col, GameTile tile) {
        switch (tile.terrain) {
            case UNDERBRUSH:
                g.setColor(new Color(10, 42, 10));
                break;
            case GRASS:
                g.setColor(Color.GREEN);
                break;
            case ROCKS:
                g.setColor(Color.GRAY);
                break;
        }
        g.fillRect(x, y, tileWidth, tileWidth);
    }

    protected void renderForeground(Graphics2D g) {
        // Draw sprites
        for (PhysicalEntity e : location.getEntities()) {
            g.setColor(getColor(e));
            g.fillOval(e.getX() - 5, e.getY() - 5, 10, 10);
        }
    }

    protected void advanceFrame(int millis) {
        PhysicalEntity player = location.getPlayerEntity();
        // Move the player
        int dx = 0;
        if (keyRight)
            dx += player.walkSpeed;
        if (keyLeft)
            dx -= player.walkSpeed;
        location.getPlayerEntity().move(dx, 0);

        int dy = 0;
        if (keyDown)
            dy += player.walkSpeed;
        if (keyUp)
            dy -= player.walkSpeed;
        location.getPlayerEntity().move(0, dy);
    }

    private Color getColor(PhysicalEntity entity) {
        if (entity.entity instanceof Bear)
            return new Color(106, 66, 15);
        if (entity.entity == game.getPlayer())
            return Color.RED;
        return Color.BLACK; // Goblins, other enemies
    }

    private TiledLocation location;
    private Game game;

    private boolean keyRight;
    private boolean keyLeft;
    private boolean keyUp;
    private boolean keyDown;
}