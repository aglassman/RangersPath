package ranger.tilegame.entity.task;

import ranger.tilegame.location.TiledLocation;

public abstract class EntityTask {
    public abstract void act(TiledLocation location);
}
