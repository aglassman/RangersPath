package ranger.time;

import ranger.Game;

public interface GameEvent {
	public long getTime();
	public void execute(Game game);
}
