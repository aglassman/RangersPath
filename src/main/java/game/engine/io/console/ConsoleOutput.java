package game.engine.io.console;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.CYAN;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import game.engine.io.Output;

import java.util.List;

import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;

import ranger.entity.Entity;
import ranger.item.weapon.Weapon;
import ranger.map.Direction;
import ranger.map.Feature;
import ranger.name.Name.NameType;
import ranger.name.Named;

public class ConsoleOutput implements Output{
	
	/* (non-Javadoc)
	 * @see ranger.io.console.Outputs#println(java.lang.String, java.lang.Object)
	 */
	@Override
	public void println(String formatString, Object... items) {
		print(formatString, items);
		System.out.println();
	}
	
	/* (non-Javadoc)
	 * @see ranger.io.console.Outputs#print(java.lang.String, java.lang.Object)
	 */
	@Override
	public void print(String formatString, Object... items) {
		AnsiConsole.systemInstall();
		
		Object[] newFormatObjects = new Object[items.length/2];
		for (int i = 0; i<items.length; i+=2) {
			Named named = (Named)items[i];
			NameType type = (NameType)items[i+1];
			newFormatObjects[i/2] = color(named, type);
		}
		
		System.out.print(String.format(formatString, newFormatObjects));
		
		AnsiConsole.systemUninstall();
	}
	
	/* (non-Javadoc)
	 * @see ranger.io.console.Outputs#printlnList(java.lang.String, java.util.List, java.lang.String)
	 */
	@Override
	public void printlnList(String before, List<? extends Named> items, String after) {
		printList(before, items, after);
		System.out.println();
	}
	
	/* (non-Javadoc)
	 * @see ranger.io.console.Outputs#printList(java.lang.String, java.util.List, java.lang.String)
	 */
	@Override
	public void printList(String before, List<? extends Named> items, String after) {
		AnsiConsole.systemInstall();
		
		String output = before + color(items.get(0), NameType.INDEFINITE);
		
		// only two items, no oxford comma
		if (items.size() == 2)
			output += " and " + color(items.get(1), NameType.INDEFINITE);
		else if (items.size() > 2) {
			for (int i = 1; i<items.size()-1; ++i)
				output += ", " + color(items.get(i), NameType.INDEFINITE);
			output += ", and " + color(items.get(items.size()-1), NameType.INDEFINITE);
		}
		System.out.print(output + after);
		
		AnsiConsole.systemUninstall();
	}
	
	public void clear() {
		AnsiConsole.systemInstall();
		System.out.println(ansi().eraseScreen());
		AnsiConsole.systemUninstall();
	}

	private String color(Named named, NameType type) {
		Color color;
		if (named instanceof Weapon)
			color = RED;
		else if (named instanceof Entity)
			color = RED;
		else if (named instanceof Feature)
			color = YELLOW;
		else if (named instanceof Direction)
			color = CYAN;
		else color = GREEN;
		
		String name;
		if (type == NameType.BASIC)
			name = named.getName().basic();
		else if (type == NameType.INDEFINITE)
			name = named.getName().indefinite();
		else
			name = named.getName().definite();
		
		return ansi().fg(color).a(name).reset().toString();
	}

	@Override
	public void print(String output) {
		System.out.print(output);
	}

	@Override
	public void println(String output) {
		System.out.println(output);
	}
	
}
