package com.tetris;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

class GameLoop implements Runnable {

	protected GUI gui;
	private Thread thread;
	private Thread timerThread;
	private GameTimer timer;
	public GameLoop ( ) {
		gui = new GUI();
		thread = new Thread();
		timer = new GameTimer(gui);
		timerThread = new Thread(timer);
		timerThread.start();
		// Render the gui
		gui.render();
		gui.renderNext(gui.board.next.pieces.get(1).type);
	}

	public synchronized void run ()
	{
		while(true)
		{
			while(!gui.board.isGameOver)
			{
				// DEBUG - printing isActive
			   // gui.board.printBoard();
				while ( gui.pause )
				{
					System.out.println ("Paused");
				}

				// Render the board
				gui.renderBoard();

				// Update Display Panel
				gui.updateScore(gui.board.score);
				gui.updateLines(gui.board.lines);
				gui.updateLevel(gui.board.level);
				if(!gui.board.moveDown())
				{
					gui.board.update(gui.getGravity());
					// Render the next piece
					//if(gui.board.next.pieces.size() != 0)
					gui.renderNext(gui.board.next.pieces.get(0).type);
				}

				// Gravity - delay
				try
				{
					thread.sleep((long)gui.board.delay);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}


			System.out.println("Game Over!");
			String name = JOptionPane.showInputDialog(null, "Enter your name: ");

			if(name != null)
			{
				name = name.replaceAll("\\s+", "");
				gui.highscoresManager.addPlayer(name, gui.board.score);
			}

			int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?",
				"Restart the game?",
				JOptionPane.YES_NO_OPTION);

			if(reply == JOptionPane.YES_OPTION)
			{
				JFrame message = new JFrame();
				JOptionPane pane = new JOptionPane("Great! Starting a new game in 3 seconds!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
				message.setContentPane(pane);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				message.setLocation(dim.width / 2 - message.getSize().width / 2,
					dim.height / 2 - message.getSize().height / 2);
				message.setVisible(true);
				message.pack();
				message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

				try
				{
					thread.sleep(3000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

				message.setVisible(false);
				message.dispose();

				gui.restart();
				Board.isGameOver = false;


				timerThread = new Thread(timer);
				timerThread.start();
			}
			else
			{
				gui.dispose();
				System.exit(0);
			}
		}
	}

}
