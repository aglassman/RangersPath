package ranger.tilegame.entity.ai;

import ranger.tilegame.location.TiledLocation;

public abstract class AITask {
    public abstract void act(TiledLocation location);
}
