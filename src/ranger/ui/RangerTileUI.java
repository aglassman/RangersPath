
package ranger.ui;

import jmotion.sprite.Sprite;
import jmotion.tilegame.TileScreenPanel;
import jmotion.tilegame.model.Physical;
import jmotion.tilegame.model.TileCoord;
import ranger.map.Direction;
import ranger.tilegame.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;

public class RangerTileUI extends TileScreenPanel<GameTile> {

    public static boolean graphicalDebug = true;

    public RangerTileUI(TiledGame game) {
        super(40);
        this.game = game;
        setMap(game.getCurrentLocation());

        GRASS = game.SPRITE_LOADER.readImage("grass.png");
        BRUSH = game.SPRITE_LOADER.readImage("trees.png");
        ROCKS = game.SPRITE_LOADER.readImage("rock.png");

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
        for (Physical p : location.getPhysicals()) {
            if (p instanceof PhysicalEntity)
                sprites.put(p, new EntitySprite((PhysicalEntity)p, game));
            else if (p instanceof PhysicalItem)
                sprites.put(p, new ItemSprite((PhysicalItem)p, game));
            else
                sprites.put(p, new ObjectSprite(p, game));
        }
    }

    protected void drawTile(Graphics2D g, int x, int y, int row, int col, GameTile tile) {
        Image image = null;
        switch (tile.terrain) {
            case UNDERBRUSH:
                image = BRUSH;
                break;
            case GRASS:
                image = GRASS;
                break;
            case ROCKS:
                image = ROCKS;
                break;
        }
        g.drawImage(image, x, y, null);
    }

    protected void renderForeground(Graphics2D g) {
        PhysicalEntity player = game.getPlayer();
        TileCoord c = location.getCoord(player.getX(), player.getY());

        // Draw fog
        HashSet<TileCoord> visible = location.getVisibility().getVisibleCoords(player, c.row, c.col);
        for (int row = 0; row<location.WIDTH; ++row) {
            for (int col = 0; col<location.HEIGHT; ++col) {
                if (!visible.contains(new TileCoord(row, col))) {
                    Color fog = new Color(55, 55, 55, 100);
                    g.setColor(fog);
                    g.fillRect(col*tileWidth, row*tileWidth, tileWidth, tileWidth);
                }
            }
        }
        // Draw sprites
        for (Physical p : location.getPhysicals()) {
            TileCoord physicalLocation = location.getCoord(p.getX(), p.getY());
            if (graphicalDebug || visible.contains(physicalLocation))
                sprites.get(p).render(g);
        }
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

        location.tryEntityWalk(player, dx, dy);

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

        location.frameTick();
    }

    private final Image GRASS;
    private final Image BRUSH;
    private final Image ROCKS;

    private TiledLocation location;
    private TiledGame game;
    private HashMap<Physical, Sprite> sprites;

    private boolean keyRight;
    private boolean keyLeft;
    private boolean keyUp;
    private boolean keyDown;
}