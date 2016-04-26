/**
 * This class will act as a mini-board, except it has the ability to operate function upon
 * the board such as rotation. This is our parent class which will be inherited out to other
 * shapes that we will create.
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
	 * This data member will hold the size of the matrix for the shape to be encased in.
	 * @var		int 			matrix_size				The length of the square matrix
	 */
	int matrix_size;

	/**
	 * This data member will act as a mini-board which will be allocated based upon the shape
	 * @var		Block[][]		matrix 					This will contain the shape within
	 */
	Block[][] matrix;

	/**
	 * This data member will keep track of the boundaries of the row
	 * @var		int 			rowBound 					The endpoint boundary of the row
	 */
	int rowBound;

	/**
	 * This data member will keep track of the boundaries of the row
	 * @var		int 			colBound 					The endpoint boundary of the col
	 */
	int colBound;

	/* This constructor will take the size of the matrix and allocate the matrix to be empty
	 */
	public Tetromino (int size)
	{
		matrix_size = size;
		matrix = new Block[matrix_size][matrix_size];
		for (int i = 0; i < matrix_size; i++)
		{
			for (int j = 0; j < matrix_size; j++)
			{
				matrix[i][j] = new Block(i, j, Shape.Empty);
			}
		}
	}

	/**
	 * This function returns the size of the matrix
	 * @return 		int
	 */
	protected int getSize()
	{
		return matrix_size;
	}

	/**
	 * This function will act as a helper function to see if the the block will go out of bounds,
	 * or if the current block is filled. This will validate our movement.
	 * @param 		int 		row 					Check the current row position
	 * @param 		int 		col 					Check the current column position 
	 * @param 		Block[][] 	board 					Pass the board through the function
	 * @return 		boolean
	 */
	protected boolean isValid(int row, int col, Block[][] board)
	{
		if (row < 0 || row >= rowBound)
			return false;
		if (col < 0 || col >= colBound)
			return false;
		if (board[row][col].isFilled)
			return false;
		else
			return true;
	}

	/**
	 * This function check if the current piece is able to move down
	 * @param		Block[][]	board 					Pass the board through the function
	 * @return 		boolean
	 */
	protected boolean canMoveDown(Block[][] board)
	{
		for (int i = 0; i < matrix_size; i++)
		{
			for (int j = 0; j < matrix_size; j++)
			{
				if (matrix[i][j].isFilled)
					if (!isValid(i - 1, j, board))
						return false;
			}
		}
		return true;
	}

	/**
	 * This function check if the current piece is able to move left
	 * @param		Block[][]	board 					Pass the board through the function
	 * @return 		boolean
	 */
	protected boolean canMoveLeft(Block[][] board)
	{
		for (int i = 0; i < matrix_size; i++)
		{
			for (int j = 0; j < matrix_size; j++)
			{
				if (matrix[i][j].isFilled)
					if (!isValid(i, j - 1, board))
						return false;
			}
		}
		return true;
	}

	/**
	 * This function check if the current piece is able to move right
	 * @param		Block[][]	board 					Pass the board through the function
	 * @return 		boolean
	 */
	protected boolean canMoveRight(Block[][] board)
	{
		for (int i = 0; i < matrix_size; i++)
		{
			for (int j = 0; j < matrix_size; j++)
			{
				if (matrix[i][j].isFilled)
					if (!isValid(i, j + 1, board))
						return false;
			}
		}
		return true;
	}



	protected void rotateRight()
	{
		for (int i = 0; i < matrix_size; i++)
		{
			for (int j = i + 1; j < matrix_size; j++)
			{
				Coordinate temp = matrix[i][j].getCoord();
				matrix[i][j].setCoordinate(matrix[j][i].getCoord());
				matrix[j][i].setCoordinate(temp);
			}
		}
		
		for (int i = 0; i < matrix_size / 2; i++)
		{
			for (int j = 0; j < matrix_size; j++)
			{
				Coordinate temp = matrix[j][i].getCoord();
				matrix[j][i].setCoordinate(matrix[j][matrix_size - i - 1].getCoord());
				matrix[j][i].setCoordinate(temp);
			}
		}
	}


}