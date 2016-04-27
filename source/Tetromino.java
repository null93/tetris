import java.util.ArrayList;
/**
 *
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
public class Tetromino
{
	/**
	 *	This data member stores an array list of the of the active position
	 *	of this tetromino.
	 *	@var	ArrayList <Cell>		pieces		Placeholder for each piece in this tetromino
	 */
	ArrayList <Cell> pieces;

	//Constructor of each tetromino
	/* NEED IMPLEMENTATION OF FACTORY DESIGN PATTERN*/
	public Tetromino()
	{
		pieces = new ArrayList <Cell>();
	}


	//Get width of the piece
	double getWidth()
	{
		//Handle piece with no cell
		if (pieces.size() == 0)
			return 0;


		double colMin = pieces.get(0).column;
		double colMax = pieces.get(0).column;

		for (int i = 1; i < pieces.size(); i++)
		{
			//Get the min column of the piece
			if (colMin > pieces.get(i).column)
				colMin = pieces.get(i).column;
			
			//Get the min column of the piece
			if (colMax < pieces.get(i).column)
				colMax = pieces.get(i).column;
		}

		double totWidth = colMax - colMin;
		return totWidth;
	}


	//Get Height of the piece
	double getHeight()
	{
		//Handle piece with no cell
		if (pieces.size() == 0)
			return 0;


		double rowMin = pieces.get(0).row;
		double rowMax = pieces.get(0).row;

		for (int i = 1; i < pieces.size(); i++)
		{
			//Get the min row of the piece
			if (rowMin > pieces.get(i).row)
				rowMin = pieces.get(i).row;
			
			//Get the min row of the piece
			if (rowMax < pieces.get(i).row)
				rowMax = pieces.get(i).row;
		}

		double totHeight = rowMax - rowMin;
		return totHeight;
	}

	//Assuming the coordinate 0, 0 is at bottom-left
	void moveDown()
	{
		for (int i = 0; i < pieces.size(); i++)
		{
			Cell oldPiece = pieces.get(i);
			pieces.set(i, new Cell(oldPiece.type, (oldPiece.row - 1), oldPiece.column));
		}
	}

	//Assuming the coordinate 0, 0 is at bottom-left
	void moveLeft()
	{
		for (int i = 0; i < pieces.size(); i++)
		{
			Cell oldPiece = pieces.get(i);
			pieces.set(i, new Cell(oldPiece.type, oldPiece.row, (oldPiece.column - 1)));
		}
	}

	//Assuming the coordinate 0, 0 is at bottom-left
	void moveRight()
	{
		for (int i = 0; i < pieces.size(); i++)
		{
			Cell oldPiece = pieces.get(i);
			pieces.set(i, new Cell(oldPiece.type, oldPiece.row, (oldPiece.column + 1)));
		}
	}

	void rotateLeft()
	{
		/*TODO*/
	}

	void rotateRight()
	{
		/*TODO*/
	}

}