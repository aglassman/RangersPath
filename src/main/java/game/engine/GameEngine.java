package game.engine;

public interface GameEngine extends Controlable {
	
	public abstract void startup();
	
	public abstract void gameLoop();
	
	public abstract void shutdown();

}