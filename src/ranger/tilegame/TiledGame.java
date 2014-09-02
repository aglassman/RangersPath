package ranger.tilegame;

import ranger.Game;
import ranger.map.Direction;
import ranger.map.Location;

/**
 * TiledGame wraps a regular Game adding a 2D tiled environment for each Location.
 *
 * This upgrades the text-based game into a graphical game with zelda-like 2d screens.
 */
public class TiledGame {

    public TiledLocation getCurrentLocation() {
        return currentLocation;
    }

    public void movePlayer(Direction direction) {
        game.movePlayer(direction);
        currentLocation = generator.generate(game.getPlayerLocation());
    }

    public Game getGame() {
        return game;
    }

    public TiledGame(Game game) {
        this.game = game;

        generator = new TiledLocationGenerator(game);

        currentLocation = generator.generate(game.getPlayerLocation());
    }

    private Game game;
    private TiledLocationGenerator generator;
    private TiledLocation currentLocation;
}
