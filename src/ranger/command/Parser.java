package ranger.command;

import java.util.HashMap;

import ranger.Game;

public class Parser {
	
	public void registerCommand(Command c) {
		String keyword = c.getKeyword();
		if (keywords.containsKey(keyword))
			throw new RuntimeException("Duplicate keyword: " + keyword);
		
		keywords.put(keyword, c);
	}
	
	public void parse(String input) {
		String[] words = input.split(" ");
		if (words.length > 0 && keywords.containsKey(words[0])) {
			keywords.get(words[0]).execute(game, words);
		} else {
			System.out.println("You can't do that.");
		}
	}
	
	public Parser(Game game) {
		this.game = game;
		keywords = new HashMap<>();

		registerCommand(new Quit());
		registerCommand(new InventoryCommand());
		registerCommand(new HealthCommand());
		registerCommand(new EatCommand());
		registerCommand(new TimeCommand());
		registerCommand(new WaitCommand());
	}
	
	private Game game;
	private HashMap<String, Command> keywords;
}
