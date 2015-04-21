package ranger.tilegame.location;

public enum TiledTerrain {
    GRASS(0),
    UNDERBRUSH(2),
    ROCKS(3);

    public final int difficulty;

    TiledTerrain(int difficulty) {
        this.difficulty = difficulty;
    }
}
