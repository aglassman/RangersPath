package game.engine.io;

import java.util.List;

import ranger.name.Named;

public interface Output {

	public abstract void clear();
	
	public abstract void print(String output);
	
	public abstract void println(String output);
	
	public abstract void println(String formatString, Object... items);

	public abstract void print(String formatString, Object... items);

	public abstract void printlnList(String before,	List<? extends Named> items, String after);

	public abstract void printList(String before, List<? extends Named> items, String after);

}