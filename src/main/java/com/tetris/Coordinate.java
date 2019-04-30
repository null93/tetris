package com.tetris;

/**
 * This class is essentially implemented in order to bind data together into a tuple like structure.
 * It stores the row and column coordinates internally.
 * @version     1.0.0
 * @university  University of Illinois at Chicago
 * @course      CS342 - Software Design
 * @category    Project #05 - Tetris Game
 * @package     Graphical User Interface
 * @author      Rafael Grigorian
 * @author      Byambasuren Gansukh
 * @author      Paul Nguyen
 * @license     GNU Public License <http://www.gnu.org/licenses/gpl-3.0.txt>
 */
public class Coordinate
{
	/**
	 * This data member stores the location of the row and the purpose of this class is to
	 * bind data together.
	 * @var		int			row				The row position of an object
	 */
	protected int row;

	/**
	 * This data member stores the location of the column and the purpose of this class is to
	 * bind data together.
	 * @var		int			row				The column position of an object
	 */
	protected int col;

	/* This constructor takes two parameters, the first parameter is the row position of the
	 * object, and the second parameter is the column postion of the object.
	 */
	public Coordinate(int rowPos, int colPos)
	{
		row = rowPos;
		col = colPos;
	}

	/**
	 * This function will allow us to change the coordinates of an object, by giving the proper
	 * location of the object.
	 * @param 	int			rowPos			The new position of the row
	 * @param 	int			colPos			The new position of the column
	 * @return	void
	 */
	protected void setCoordinate(int rowPos, int colPos)
	{
		row = rowPos;
		col = colPos;
	}

	/**
	 * This function will allow us to change the coordinates of an object, by giving the proper
	 * location of the object.
	 * @param 	int			rowPos			The new position of the row
	 * @return	void
	 */
	protected void setRow(int rowPos)
	{
		row = rowPos;
	}

		/**
	 * This function will allow us to change the coordinates of an object, by giving the proper
	 * location of the object.
	 * @param 	int			colPos			The new position of the column
	 * @return	void
	 */
	protected void setCol(int colPos)
	{
		col = colPos;
	}

	/**
	 * This function will allow us to access the row coordinates of an object, by giving the proper
	 * location of the object.
	 * @return	int
	 */
	protected int getRow()
	{
		return row;
	}

	/**
	 * This function will allow us to access the column coordinates of an object, by giving the proper
	 * location of the object.
	 * @return	int
	 */
	protected int getCol()
	{
		return col;
	}
}
