package ranger.command;

import game.engine.io.Output;
import ranger.Game;
import ranger.Player;

public class HealthCommand implements Command {

	public String getKeyword() {
		return "health";
	}

	public String getHelpText() {
		return "See your health status.";
	}

	public void execute(Game game, String[] words, String directObject) {
		Player player = game.getPlayer();
		displayStat(game.ui.output, player.getHealth(), "You are healthy.", "You are injured.", "You are gravely wounded.");
		displayStat(game.ui.output, player.getFood(), "You are not hungry.", "You are hungry.", "You are starving.");
		displayStat(game.ui.output, player.getHydration(), "You are not thirsty.", "You are parched.", "You are dehydrated.");
	}
	
	private void displayStat(Output output, int value, String high, String medium, String low) {
		if (value >= highThreshold)
			output.println(high);
		else if (value >= mediumThreshold)
			output.println(medium);
		else
			output.println(low);
	}

	private int highThreshold = 80;
	private int mediumThreshold = 30;
}
