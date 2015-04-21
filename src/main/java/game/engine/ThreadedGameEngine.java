package game.engine;

public abstract class ThreadedGameEngine implements Runnable, GameEngine{
	
	private Thread gameThread;
	private boolean shutdown = false;
	private boolean pause = false;

	
	/* (non-Javadoc)
	 * @see game.engine.GameEngine#start()
	 */
	@Override
	public void start()
	{
		startup();
		bootstrap();
		gameThread.start();
	}
	
	boolean isRunning(){
		return pause;
	}
	
	/* (non-Javadoc)
	 * @see game.engine.GameEngine#pause()
	 */
	@Override
	public synchronized void pause()
	{
		pause = true;
	}
	
	/* (non-Javadoc)
	 * @see game.engine.GameEngine#resume()
	 */
	@Override
	public synchronized void resume()
	{
		gameThread.notify();
	}
	
	/* (non-Javadoc)
	 * @see game.engine.GameEngine#stop()
	 */
	@Override
	public void stop()
	{
		shutdown = true;
	}
	
	public void run()
	{
		while(!shutdown)
		{
			if(!pause)
			{
				gameLoop();
			}
			else
			{
				try {
					gameThread.wait();
					pause = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		shutdown();
	}
	
	private void bootstrap()
	{
		gameThread = new Thread(this);
	}
}
