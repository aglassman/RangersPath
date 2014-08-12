package ranger.time;

import ranger.Game;
import ranger.entity.Entity;
import ranger.map.Direction;

public class EntityMove implements GameEvent {

	public long getTime() {
		return time;
	}

	public void execute(Game game) {
		if (entity.isAlive()) {
			game.moveEntity(entity, direction);
			game.getTime().scheduleEvent(entity.getNextAction(game, game.getTime().getCurrentTime()));
		}
	}
	
	public EntityMove(Entity entity, Direction direction, long time) {
		this.entity = entity;
		this.direction = direction;
		this.time = time;
	}
	
	private Entity entity;
	private Direction direction;
	private long time;
}
