package ranger.tilegame;

import jmotion.construction.SpriteLoader;
import jmotion.tilegame.model.Physical;
import ranger.Game;
import ranger.map.Direction;

/**
 * TiledGame wraps a regular Game adding a 2D tiled environment for each Location.
 *
 * This upgrades the text-based game into a graphical game with zelda-like 2d screens.
 */
public class TiledGame {

    public final SpriteLoader SPRITE_LOADER;

    public TiledLocation getCurrentLocation() {
        return currentLocation;
    }

    public void movePlayer(Direction direction) {
        game.movePlayer(direction);
        currentLocation = generator.generate(game.getPlayerLocation());

        // Move the player to the opposite side of the new screen
        if (direction == Direction.NORTH)
            player.move(0, currentLocation.REAL_HEIGHT);
        else if (direction == Direction.SOUTH)
            player.move(0, -currentLocation.REAL_HEIGHT);
        else if (direction == Direction.WEST)
            player.move(currentLocation.REAL_WIDTH, 0);
        else if (direction == Direction.EAST)
            player.move(-currentLocation.REAL_WIDTH, 0);

        // HACK - Add the physical player to the TiledLocation
        // - but the player entity should already be in the Location
        currentLocation.addPhysicalEntity(player);
    }

    public PhysicalEntity getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public TiledGame(Game game) {
        this.game = game;
        SPRITE_LOADER = new SpriteLoader("assets");

        generator = new TiledLocationGenerator(this);

        currentLocation = generator.generate(game.getPlayerLocation());

        player = new PhysicalEntity(game.getPlayer());
        player.setLocation(currentLocation.REAL_WIDTH / 2, currentLocation.REAL_HEIGHT / 2);

        // HACK - Add the physical player to the TiledLocation
        // - but the player entity should already be in the Location
        currentLocation.addPhysicalEntity(player);
    }

    private Game game;
    private TiledLocationGenerator generator;
    private TiledLocation currentLocation;
    private PhysicalEntity player;
}
