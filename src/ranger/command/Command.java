package ranger.command;

import ranger.Game;

public interface Command {
	public String getKeyword();
	public void execute(Game game, String[] words, String directObject);
}
