package game.engine.io.console;

import game.engine.io.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements Input{
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public String getInputBlocking() {
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}    
}
