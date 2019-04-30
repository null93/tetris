package com.tetris;

class GameTimer implements Runnable {

	protected GUI gui;
	protected Thread thread;
	protected long initTime;
	protected int seconds;
	public GameTimer ( GUI gui)
	{
		this.gui = gui;
		thread = new Thread();
		initTime = System.currentTimeMillis();
		seconds = 0;
	}

	public synchronized void run ()
	{
		while(!Board.isGameOver)
		{
			if(!gui.pause)
				gui.updateTime(seconds++);
			//System.out.println(seconds);
			try
			{
				thread.sleep(1000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		this.seconds = 0;
	}
}
