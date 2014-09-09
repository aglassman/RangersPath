
package ranger.ui;

import jmotion.sprite.Sprite;
import jmotion.tilegame.TileScreenPanel;
import jmotion.tilegame.model.Physical;
import jmotion.tilegame.model.TileCoord;
import ranger.tilegame.PhysicalItem;
import ranger.tilegame.TiledGame;
import ranger.tilegame.entity.Arrow;
import ranger.tilegame.entity.PhysicalEntity;
import ranger.tilegame.entity.task.PlayerControlTask;
import ranger.tilegame.location.GameTile;
import ranger.tilegame.location.TiledLocation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;

public class RangerTileUI extends TileScreenPanel<GameTile> {

    public static boolean graphicalDebug = true;

    public RangerTileUI(TiledGame game, PlayerControlTask playerControl) {
        super(40);
        this.game = game;
        this.playerControl = playerControl;
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
                keySpace |= keyEvent.getKeyCode() == KeyEvent.VK_SPACE;
                keySpacePressed |= keyEvent.getKeyCode() == KeyEvent.VK_SPACE;
            }
            public void keyReleased(KeyEvent keyEvent) {
                keyRight &= keyEvent.getKeyCode() != KeyEvent.VK_RIGHT;
                keyLeft &= keyEvent.getKeyCode() != KeyEvent.VK_LEFT;
                keyUp &= keyEvent.getKeyCode() != KeyEvent.VK_UP;
                keyDown &= keyEvent.getKeyCode() != KeyEvent.VK_DOWN;
                keySpace &= keyEvent.getKeyCode() != KeyEvent.VK_SPACE;
            }
        });
    }

    public void setMap(TiledLocation location) {
        super.setMap(location);
        this.location = location;

        sprites = new HashMap<>();
        for (Physical p : location.getPhysicals())
            addSprite(p);
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
        // Check for new Physicals this frame
        for (Physical newPhysical : location.getNewPhysicals()) {
            addSprite(newPhysical);
            System.out.println("new Physical : " + newPhysical);
        }

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
        // Decide the players move this turn
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

        playerControl.setMovement(dx, dy);

        // Check for player attack
        // (Fire if the space bar is being held down, or if it has been
        // pressed during this frame)
        if (keySpace || keySpacePressed)
            playerControl.setWillAttack();

        // Input has been handled, model can act
        location.frameTick();

        // If the Location has changed, move there
        if (map != game.getCurrentLocation())
            setMap(game.getCurrentLocation());

        // Reset user input
        keySpacePressed = false;
    }

    private void addSprite(Physical p) {
        if (p instanceof Arrow)
            sprites.put(p, new ArrowSprite((Arrow)p));
        else if (p instanceof PhysicalItem)
            sprites.put(p, new ItemSprite((PhysicalItem)p, game));
        else if (p instanceof PhysicalEntity)
            sprites.put(p, new EntitySprite((PhysicalEntity)p, game));
        else
            sprites.put(p, new ObjectSprite(p, game));
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
    private boolean keySpace;
    private boolean keySpacePressed;

    private PlayerControlTask playerControl;
}