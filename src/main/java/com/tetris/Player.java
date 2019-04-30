package com.tetris;

import java.util.Comparator;
/**
 * This class simply stores a player by that it stores the username and the users score.
 * Provides simple getters and no setters.
 *
 * @version     1.0.0
 * @university  University of Illinois at Chicago
 * @course      CS342 - Software Design
 * @category    Project #05 - Tetris Game
 * @author      Rafael Grigorian
 * @author      Byambasuren Gansukh
 * @authro      Paul Nguyen
 * @license     GNU Public License <http://www.gnu.org/licenses/gpl-3.0.txt>
 */
public class Player
{
	/**
	 * This internal data member saves the username of a User
	 * @var     String           username               Holds the username of this Player
	 */
	private String username;

	/**
	 * This internal data member saves the score of a User
	 * @var     int             score          Holds the score of this Player
	 */
	private int score;

	/**
	 * This constructor simply instantiates the data members
	 * @param   String          username                Value to init data member username
	 * @param   String          password_hash           Value to init data member password_hash
	 */
	public Player(String username, int score)
	{
		// Init data members
		this.username = username;
		this.score = score;
	}

	public static Comparator<Player> comparator = new Comparator<Player>()
	{
		public int compare(Player p1, Player p2)
		{
			String p1Name = p1.getUsername().toLowerCase();
			String p2Name = p2.getUsername().toLowerCase();

			int p1Score = p1.getScore();
			int p2Score = p2.getScore();

			if(p1Score == p2Score)
				return p1Name.compareTo(p2Name);

			return p2Score - p1Score;
		}
	};

	/**
	 * This function is a Getter for the username of this User
	 * @return  String          this.username           Data member username
	 */
	public String getUsername()
	{
		return this.username;
	}

	/**
	 * This function is a Getter for the hashed password of this User
	 * @return  String          this.password_hash      Data member password_hash
	 */
	public int getScore()
	{
		return this.score;
	}

	public String toString()
	{
		return this.username + "\t" + this.score + "\n";
	}
}
