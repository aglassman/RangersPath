package ranger.tilegame;

import ranger.Game;

/**
 * TiledGame wraps a regular Game adding a 2D tiled environment for each Location.
 *
 * This upgrades the text-based game into a graphical game with zelda-like 2d screens.
 */
public class TiledGame {

    public TiledLocation getCurrentLocation() {
        return currentLocation;
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
