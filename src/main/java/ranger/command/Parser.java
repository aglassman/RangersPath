package ranger.command;

import java.util.SortedMap;
import java.util.TreeMap;

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
			game.ui.output.println("You can't do that.");
		}
	}
	
	public Parser(Game game) {
		this.game = game;
		keywords = new TreeMap<>();

		registerCommand(new AttackCommand());
		registerCommand(new CleanCommand());
		registerCommand(new EatCommand());
		registerCommand(new EquipCommand());
		registerCommand(new GetCommand());
		registerCommand(new GoCommand());
		registerCommand(new HealthCommand());
		registerCommand(new HuntCommand());
		registerCommand(new InventoryCommand());
		registerCommand(new LookCommand());
		registerCommand(new Quit());
		registerCommand(new SearchCommand());
        registerCommand(new TimeCommand());
        registerCommand(new TrackCommand());
		registerCommand(new WaitCommand());
		registerCommand(new WanderCommand());
		
		registerCommand(new Command() {

			public String getKeyword() {
				return "help";
			}

			public String getHelpText() {
				return "Recieve help for game commands.";
			}

			public void execute(Game game, String[] words, String directObject) {
				game.ui.output.println("Legal commands are:");
				for (String s : keywords.keySet()) {
					game.ui.output.println(s);
					game.ui.output.println("\t" + keywords.get(s).getHelpText());
				}
			}
		});
	}
	
	private final Game game;
	private SortedMap<String, Command> keywords;
}
