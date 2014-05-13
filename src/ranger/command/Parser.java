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
			String directObject = "";
			if (words.length > 1)
				directObject = input.substring(words[0].length() + 1).toLowerCase();
			
			keywords.get(words[0]).execute(game, words, directObject);
		} else {
			System.out.println("You can't do that.");
		}
	}
	
	public Parser(Game game) {
		this.game = game;
		keywords = new HashMap<>();

		registerCommand(new AttackCommand());
		registerCommand(new CleanCommand());
		registerCommand(new EatCommand());
		registerCommand(new EquipCommand());
		registerCommand(new GetCommand());
		registerCommand(new HealthCommand());
		registerCommand(new HuntCommand());
		registerCommand(new InventoryCommand());
		registerCommand(new LookCommand());
		registerCommand(new Quit());
		registerCommand(new SearchCommand());
		registerCommand(new TimeCommand());
		registerCommand(new WaitCommand());
		registerCommand(new WanderCommand());
	}
	
	private Game game;
	private HashMap<String, Command> keywords;
}
