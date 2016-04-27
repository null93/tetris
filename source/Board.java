import java.util.ArrayList;

/**
 * This class acts as the back end of the game. This class will operate all the functions
 * of the gameplay.
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

public class Board
{
	//Board dimension
	//Left double on purpose...
	static double rows;
	static double columns;

	//The current level
	static int level;

	//Flag for rotation
	static boolean skipLoop;

	static int delay;

	


	//Set the proper prediction pieces for current and next
	Tetromino current;
	Tetromino next;

	//The board instance of tetris
	static ArrayList <Cell> board;

	//Constructor for the board ***
	public Board(double r, double c)
	{
		//Set the dimension
		rows = r;
		columns = c;

		//Set the initial level
		level = 1;

		skipLoop = true;	//when we skip loop, we prevent piece to move down
		
		//delay = (50 - (level x 2)) / 60 seconds
		delay = (50 - (level * 2) / 6000);

		current = null;		//randomize first 2 pieces then only modify next piece
		next = null;

		board = new ArrayList <Cell>();
	}

	//Move the current piece down
	void moveDown()
	{

	}

	//render the board to GUI
	void render()
	{
		
	}

	//Operate end game
	void gameOver()
	{

	}


	// /**
	//  * This data member will hold the row length of the board, and it will act as
	//  * a boundary checker.
	//  * @var 	int 			rows				The row dimension of the board
	//  */
	// protected int rows;

	// /**
	//  * This data member will hold the column length of the board, and it will act as
	//  * a boundary checker.
	//  * @var 	int 			columns				The column dimension of the board
	//  */
	// protected int columns;

	// /**
	//  * This data member will hold a grid of blocks as an infrastructure of the game
	//  * @var 	Block[][] 			board			2D Array of blocks to act as the board
	//  */
	// protected Block[][] board;

	// /* This constructor will initialize the board to empty with the proper dimension
	//  */
	// public Board()
	// {
	// 	rows = 20;
	// 	columns = 10;
	// 	board = new Block[rows][columns];

	// 	for (int i = 0; i < rows; i++)
	// 	{
	// 		for (int j = 0; j < columns; j++)
	// 		{
	// 			board[i][j] = new Block(i + 1, j + 1, Shape.Empty);
	// 		}
	// 	}
	// }

	// *
	//  * This function will store every single piece of the board into an arraylist
	//  * of Cell to transfer over to the GUI and update the GUI.
	//  * @return		ArrayList <Cell>
	 
	// protected ArrayList <Cell> updateBoard()
	// {
	// 	ArrayList <Cell> ret = new ArrayList <Cell> ();
	// 	for (int i = 0; i < rows; i++)
	// 	{
	// 		for (int j = 0; j < columns; j++)
	// 		{
	// 			if (board[i][j].isFilled)
	// 			ret.add(new Cell ( board[i][j].getType(), board[i][j].getRow(), board[i][j].getCol()));
	// 		}
	// 	}
	// 	return ret;
	// }


	// protected void spawnTetromino()
	// {
	// 	Tetromino temp = new IShape();
	// 	int size = temp.getSize();
	// 	for (int i = 0; i < size; i++)
	// 	{
	// 		for (int j = 0; j < size; j++)
	// 		{
	// 			int newRow = rows  - 1 - temp.matrix[i][j].getRow();
	// 			int newCol = (columns - 1) /2  - size/2 + temp.matrix[i][j].getCol();
	// 			board[newRow][newCol].setBlock(newRow + 1, newCol + 1, temp.matrix[i][j].getType());
	// 		}
	// 	}

	// }


}