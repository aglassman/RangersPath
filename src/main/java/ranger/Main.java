package ranger;

import game.engine.io.UserInterface;
import game.engine.io.console.ConsoleInterface;

public class Main {
	public static void main(String... args) {
		UserInterface ui = new ConsoleInterface();
		Game game = new Game(ui);
		game.start();
	}
}
