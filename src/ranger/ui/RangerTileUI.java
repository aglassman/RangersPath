
package ranger.ui;

import jmotion.tilegame.TileScreenPanel;
import ranger.entity.Bear;
import ranger.map.Direction;
import ranger.tilegame.GameTile;
import ranger.tilegame.PhysicalEntity;
import ranger.tilegame.TiledGame;
import ranger.tilegame.TiledLocation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class RangerTileUI extends TileScreenPanel<GameTile> {

    public RangerTileUI(TiledGame game) {
        super(30);
        this.game = game;
        setMap(game.getCurrentLocation());

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

        sprites = new HashMap<>();
        for (PhysicalEntity e : location.getEntities())
            sprites.put(e, new EntitySprite(e, game));
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
        for (PhysicalEntity e : location.getEntities())
            sprites.get(e).render(g);
    }

    protected void advanceFrame(int millis) {
        PhysicalEntity player = game.getPlayer();
        // Move the player
        int dx = 0;
        if (keyRight)
            dx += player.walkSpeed;
        if (keyLeft)
            dx -= player.walkSpeed;

        int dy = 0;
        if (keyDown)
            dy += player.walkSpeed;
        if (keyUp)
            dy -= player.walkSpeed;

        player.move(dx, dy);

        // Move the player to the next screen
        Direction horizontalDir = null;
        if (player.getX() < 0)
            horizontalDir = Direction.WEST;
        else if (player.getX() >= location.REAL_WIDTH)
            horizontalDir = Direction.EAST;
        if (horizontalDir != null)
            game.movePlayer(horizontalDir);

        Direction verticalDir = null;
        if (player.getY() < 0)
            verticalDir = Direction.NORTH;
        else if (player.getY() >= location.REAL_HEIGHT)
            verticalDir = Direction.SOUTH;
        if (verticalDir != null)
            game.movePlayer(verticalDir);

        if (horizontalDir != null || verticalDir != null)
            setMap(game.getCurrentLocation());
    }

    private TiledLocation location;
    private TiledGame game;
    private HashMap<PhysicalEntity, EntitySprite> sprites;

    private boolean keyRight;
    private boolean keyLeft;
    private boolean keyUp;
    private boolean keyDown;
}