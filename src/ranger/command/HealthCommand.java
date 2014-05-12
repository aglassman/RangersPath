package ranger.command;

import ranger.Game;
import ranger.Player;

public class HealthCommand implements Command {

	public String getKeyword() {
		return "health";
	}

	public void execute(Game game, String[] words, String directObject) {
		Player player = game.getPlayer();
		displayStat(player.getHealth(), "You are healthy.", "You are injured.", "You are gravely wounded.");
		displayStat(player.getFood(), "You are not hungry.", "You are hungry.", "You are starving.");
		displayStat(player.getHydration(), "You are not thirsty.", "You are parched.", "You are dehydrated.");
	}
	
	private void displayStat(int value, String high, String medium, String low) {
		if (value >= highThreshold)
			System.out.println(high);
		else if (value >= mediumThreshold)
			System.out.println(medium);
		else
			System.out.println(low);
	}

	private int highThreshold = 80;
	private int mediumThreshold = 30;
}
