import java.util.ArrayList;
import java.util.Random;

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

	boolean[][] isActive;

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

		Random rand = new Random();

		int i = rand.nextInt(7);

		current = null;		//randomize first 2 pieces then only modify next piece
		next = null;

		board = new ArrayList <Cell>();

		//Used for deletion, set all settle pieces of cell if they are active or not
		isActive = new boolean[(int) r][(int) c];
	}

	//Move the current piece down
	boolean moveDown()
	{
		//If the current can move down
		for (int i = 0; i < current.pieces.size(); i++)
		{
			//Transverse through the board to check
			for (int j = 0; j < board.size(); j++)
			{
				//Out of boundaries
				if ((current.pieces.get(i).row - 1) < 0)
					return false;

				//A current cell exist there																							//May be needed for deleted cell
				if ((board.get(i).row == current.pieces.get(i).row - 1) && (board.get(i).column == current.pieces.get(i).column))		// && (board.get(i).type == Shape.E))
					return false;
			}
		}

		//If we arrived here we pass all test and move piece down
		current.moveDown();
		return true;
	}

	//Move the current piece left
	boolean moveLeft()
	{
		//If the current can move down
		for (int i = 0; i < current.pieces.size(); i++)
		{
			//Transverse through the board to check
			for (int j = 0; j < board.size(); j++)
			{
				//Out of boundaries
				if ((current.pieces.get(i).column - 1) < 0)
					return false;

				//A current cell exist there																							//May be needed for deleted cell
				if ((board.get(i).row == current.pieces.get(i).row) && (board.get(i).column == current.pieces.get(i).column - 1))		// && (board.get(i).type == Shape.E))
					return false;
			}
		}

		//If we arrived here we pass all test and move piece down
		current.moveLeft();
		return true;
	}

	//Move the current piece right
	boolean moveRight()
	{
		//If the current can move down
		for (int i = 0; i < current.pieces.size(); i++)
		{
			//Transverse through the board to check
			for (int j = 0; j < board.size(); j++)
			{
				//Out of boundaries
				if ((current.pieces.get(i).column + 1) >= columns)
					return false;

				//A current cell exist there																							//May be needed for deleted cell
				if ((board.get(i).row == current.pieces.get(i).row) && (board.get(i).column == current.pieces.get(i).column + 1))		// && (board.get(i).type == Shape.E))
					return false;
			}
		}

		//If we arrived here we pass all test and move piece down
		current.moveRight();
		return true;
	}

	//Set what is settled on the board
	void setActive()
	{
		//Reset the board 
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				isActive[i][j] = false;
			}
		}

		//Turn on all active pieces
		for (int i = 0; i < board.size(); i++)
		{
			int r = (int) board.get(i).row;
			int c = (int) board.get(i).column;

			isActive[r][c] = true;
		}
	}

	//Check the board for any rows that are filled, delete the row
	//Operate this function after current piece settles down
	//Returns total number of lines cleared for score keeping
	int checkAndDelete()
	{
		int linesCleared = 0;
		for (int i = 0; i < rows; i++)
		{
			boolean rowIsFilled = true;
			for (int j = 0; j < columns; j++)
			{
				//If row is not filled move onto next line
				if (!isActive[i][j])
					rowIsFilled = false;
			}

			if (rowIsFilled)
			{
				//Update lines cleared for score system
				linesCleared++;
				//Delete the rows
				deleteRow(i);
				//Now bring everything above it down one, above it
				pack(i);
				//Recheck that line
				i--;
			}

		}
		return linesCleared;
	}

	//Delete the row frome the board
	void deleteRow(int row)
	{
		for (int i = 0; i < columns; i++)
		{
			isActive[row][i] = false;
		}

		for (int i = 0; i < board.size(); i++)
		{
			if (board.get(i).row == row)
				board.remove(i);
		}
	}

	//Helper function to help bring everything down one, parameter will take the row that was just cleared
	void pack(int row)
	{
		for (int i = 0; i < board.size(); i++)
		{
			//The last row will need to get place in a temp
			//Replace everything above it with row going down
			if (board.get(i).row > row)
			{
				Cell temp = new Cell(board.get(i).type, (board.get(i).row - 1), board.get(i).column);
				board.set(i, temp);
			}
		}

		for (int i = row; i < rows - 1; i++)
		{
			//Update the boolean board
			for (int j = 0; j < columns; j++)
			{
				isActive[i][j] = isActive[i + 1][j];
			}
		}
		//Make the last row as all false
		for (int i = 0; i < columns; i++)
		{
			isActive[row - 1][i] = false;
		}
	}

	//render the board to GUI
	ArrayList <Cell> render()
	{
		ArrayList <Cell> newBoard = new ArrayList <Cell>();
		//Render the current piece and the board to the GUI

		//Get all active pieces of the board
		for (int i = 0; i < board.size(); i++)
		{
			int r = (int) board.get(i).row;
			int c = (int) board.get(i).column;

			if (isActive[r][c])
			{
				newBoard.add(board.get(i));
			}
		}

		//Add the current piece
		for (int i = 0; i < current.pieces.size(); i++)
		{
			newBoard.add(current.pieces.get(i));
		}

		return newBoard;
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