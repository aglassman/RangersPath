package ranger.map;

import ranger.entity.Entity;

public class Tracks {
    public Entity getEntity() {
        return entity;
    }

    public long getAge() {
        return age;
    }

    public void age(long time) {
        age += time;
    }

    public Tracks(Entity entity) {
        this.entity = entity;
    }

    private Entity entity;
    private long age;
}
