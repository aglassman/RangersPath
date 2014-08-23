package ranger;

import java.util.Scanner;

import ranger.command.Parser;
import ranger.ui.TestUI;

public class Main {
	public static void main(String... args) {
		Game game = new Game();
		Parser parser = new Parser(game);
        TestUI testUI = new TestUI(game);

		Output.clear();
		
		try (Scanner in = new Scanner(System.in)) {
			while (true) {
				System.out.print(">");
				String input = in.nextLine();
				parser.parse(input);
				System.out.println();

                testUI.repaint();
			}
		}
	}
}
