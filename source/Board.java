import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

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
	/**
	 * This data member will hold the the width of the grid.
	 * @var		double			rows 		Row dimension of the board
	 * @static
	 */
	static double rows;

	/**
	 * This data member will hold the the height of the grid.
	 * @var		double			columns 	Columns dimension of the board
	 * @static
	 */
	static double columns;

	/**
	 * This data member will bookkeep the current level.
	 * @var 	int 			level		The current level
	 * @static
	 */
	int level;

	/**
	 * This data member will act as a flag to determine if we skip loop in the thread.
	 * @var 	boolean 		skipLoop	Set if we skip loop
	 * @static
	 */
	boolean skipLoop;

	/**
	 * This data member bookkeeps the delay, on which we sleep the thread until
	 * we update the board and gameplay.
	 * @var 	int 			delay 		The current delay until we update
	 * @static
	 */
	double delay;

	/**
	 * This data member bookkeeps the current score, that is scored by the player,
	 * when we clear a line.
	 * @var 	int 			score 		Store the current score
	 * @static
	 */
	int score;

	/**
	 * This data member bookkeeps the total amount of lines that the user have cleared
	 * from the board.
	 * @var 	int 			lines 		Store the current score
	 * @static
	 */
	int lines;
	

	// 
	boolean collisionFlag;

	/**
	 * This data member holds the current tetromino being operated on.
	 *	@var		Tetromino 			Get the current active tetromino
	 */
	Tetromino current;

	/**
	 * This data member holds the next tetromino to be operated on.
	 * This tetromino will become current after the current piece has
	 * settle down.
	 * @var			Tetromino 			Get the next tetromino
	 */
	Tetromino next;

	/**
	 * This holds a pointer to all the active cells in the board. A cell
	 * will transfer from current to the board.
	 * @var 		ArrayList <Cell>	Contains all the active pieces
	 * @static		
	 */
	ArrayList <Cell> board;

	/**
	 * This data member will act as a second instance of board, but this
	 * will extract data from the board and determine if there is an
	 * active piece in the grid.
	 * @var			boolean[][]			Contains all the flags for each piece on the grid
	 */
	boolean[][] isActive;

	/**
	 * This data member will act as a flag to determine whether the game is over
	 * game is over on true, false otherwise
	 * @var			boolean			Flag to determine whether the game is over
	 */
	static boolean isGameOver;

	/* This is a constructor for the board, it will need the dimension of row by column. */
	public Board(double r, double c)
	{
		//Set the dimension
		rows = r;
		columns = c;

		//Set the initial level
		level = 1;

		//when we skip loop, we prevent piece to move down
		skipLoop = true;	
		
		//delay = (50 - (level x 2)) / 60 seconds
		delay = ((50.0 - ((double) level * 2.0)) / 60.0) * 1000.0;

		System.out.println("IN BOARD delay: " + delay);

		//Initialize the total number of lines cleared.
		lines = 0;

		//randomize first 2 pieces then only modify next piece
		Random rand = new Random();
		int i = rand.nextInt(7);
		switch(i)
		{
			case 0:
				current = new TetrominoI();
				break;
			case 1:
				current = new TetrominoT();
				break;
			case 2:
				current = new TetrominoO();
				break;
			case 3:
				current = new TetrominoL();
				break;
			case 4:
				current = new TetrominoJ();
				break;
			case 5: 
				current = new TetrominoS();
				break;
			case 6:
				current = new TetrominoZ();
				break;
			default:
				System.out.println("ERROR: randomization has failed with value: " + i);
		}

		int j = rand.nextInt(7);
		switch(j)
		{
			case 0:
				next = new TetrominoI();
				break;
			case 1:
				next = new TetrominoT();
				break;
			case 2:
				next = new TetrominoO();
				break;
			case 3:
				next = new TetrominoL();
				break;
			case 4:
				next = new TetrominoJ();
				break;
			case 5: 
				next = new TetrominoS();
				break;
			case 6:
				next = new TetrominoZ();
				break;
			default:
				System.out.println("ERROR: randomization has failed with value: " + i);
		}

		board = new ArrayList <Cell>();

		//Used for deletion, set all settle pieces of cell if they are active or not
		isActive = new boolean[(int) r + 10][(int) c];

		isGameOver = false;

		collisionFlag = false;
	}

	/**
	 * This function will attempt to move the current tetromino down.
	 * If failure to move down, then return false.
	 * @return 		boolean			If we were able to move the current tetromino down
	 */
	boolean moveDown()
	{
		//If the current can move down
		for (Cell piece : current.pieces)
		{
			// //Transverse through the board to check
			// for (Cell cell : board)
			// {
				//Out of boundaries
				if ((piece.row - 1) < 0)
					return false;

				// //A current cell exist there																							//May be needed for deleted cell
				// if ((board.get(i).row == current.pieces.get(i).row - 1) && (board.get(i).column == current.pieces.get(i).column))		// && (board.get(i).type == Shape.E))
				// 	return false;

				double r = piece.row - 1;
				double c = piece.column;
				if (isActive[(int)r][(int)c])
					return false;
			// }
		}

		//If we arrived here we pass all test and move piece down
		current.moveDown();
		return true;
	}

	/**
	 * This function will attempt to move the current tetromino left.
	 * If failure to move down, then return false.
	 * @return 		boolean			If we were able to move the current tetromino left
	 */
	boolean moveLeft()
	{
		//If the current can move down
		for (Cell piece : current.pieces)
		{
			// //Transverse through the board to check
			// for (Cell cell : board)
			// {
				//Out of boundaries
				if ((piece.column - 1) < 0)
					return false;

				// //A current cell exist there																							//May be needed for deleted cell
				// if ((board.get(i).row == current.pieces.get(i).row - 1) && (board.get(i).column == current.pieces.get(i).column))		// && (board.get(i).type == Shape.E))
				// 	return false;

				double r = piece.row;
				double c = piece.column - 1;
				if (isActive[(int)r][(int)c])
					return false;
			// }
		}

		//If we arrived here we pass all test and move piece down
		current.moveLeft();
		return true;
	}

	/**
	 * This function will attempt to move the current tetromino right.
	 * If failure to move down, then return false.
	 * @return 		boolean			If we were able to move the current tetromino right
	 */
	boolean moveRight()
	{
		//If the current can move down
		for (Cell piece : current.pieces)
		{
			// //Transverse through the board to check
			// for (Cell cell : board)
			// {
				//Out of boundaries
				if ((piece.column + 1) >= columns)
					return false;

				// //A current cell exist there																							//May be needed for deleted cell
				// if ((board.get(i).row == current.pieces.get(i).row - 1) && (board.get(i).column == current.pieces.get(i).column))		// && (board.get(i).type == Shape.E))
				// 	return false;

				double r = piece.row;
				double c = piece.column + 1;
				if (isActive[(int)r][(int)c])
					return false;
			// }
		}

		//If we arrived here we pass all test and move piece down
		current.moveRight();
		return true;
	}

	boolean rotateRight()
	{
		//If the current can move down
		for (Cell piece : current.pieces)
		{
			// //Transverse through the board to check
			// for (Cell cell : board)
			// {
				//Out of boundaries
				// if ((piece.column + 1) >= columns)
				// 	return false;

				// //A current cell exist there																							//May be needed for deleted cell
				// if ((board.get(i).row == current.pieces.get(i).row - 1) && (board.get(i).column == current.pieces.get(i).column))		// && (board.get(i).type == Shape.E))
				// 	return false;
				if(current.pivotCell == null)
				{
				System.out.println("NULL")	;
				return false;

				}
				double vrpCol = piece.column - current.pivotCell.column;
				double vrpRow = piece.row - current.pivotCell.row;

				// Rotate the relative vector 
				// [ a b ] [ vrpCol ] = [ vrpCol * a + vrpRow * b ] -> a and d are 0: [ vrpRow * b]
				// [ c d ] [ vrpRow ]   [ vrpCol * c + vrpRow * d ]                   [ vrpCol * c]

				double vRotatedCol = vrpRow * 1;
				double vRotatedRow = vrpCol * -1;

				// Change back the vector to be relative of origin
				// VrelativetoO = [ vRotatedCol + pivotCell.column ]
				//                [ vRotatedRow + pivotCell.row    ]
				double c = vRotatedCol + current.pivotCell.column;
				double r = vRotatedRow + current.pivotCell.row;

				if (r < 0 || r >= rows || c < 0 || c >= columns)
				{
					//Do nothing and let it push off the wall
				}
				 //System.out.println("Row:" + r + "Column" + c );
				 //System.out.println("PIVOT- r: "+ current.pivotCell.row + "c: " + current.pivotCell.column);
				else if (isActive[(int)r][(int)c])
				{
					System.out.println("FAIL")	;
					return false;
				}
			// }

		}
		//If we arrived here we pass all test and move piece down
		current.rotate(Tetromino.Rotation.RIGHT);
		return true;
	}

	boolean rotateLeft()
	{
		//If the current can move down
		for (Cell piece : current.pieces)
		{
			// //Transverse through the board to check
			// for (Cell cell : board)
			// {
				//Out of boundaries
				// if ((piece.column + 1) >= columns)
				// 	return false;

				// //A current cell exist there																							//May be needed for deleted cell
				// if ((board.get(i).row == current.pieces.get(i).row - 1) && (board.get(i).column == current.pieces.get(i).column))		// && (board.get(i).type == Shape.E))
				// 	return false;
				if(current.pivotCell == null)
				{
				System.out.println("NULL")	;
				return false;

				}
				double vrpCol = piece.column - current.pivotCell.column;
				double vrpRow = piece.row - current.pivotCell.row;

				// Rotate the relative vector 
				// [ a b ] [ vrpCol ] = [ vrpCol * a + vrpRow * b ] -> a and d are 0: [ vrpRow * b]
				// [ c d ] [ vrpRow ]   [ vrpCol * c + vrpRow * d ]                   [ vrpCol * c]

				double vRotatedCol = vrpRow * -1;
				double vRotatedRow = vrpCol * 1;

				// Change back the vector to be relative of origin
				// VrelativetoO = [ vRotatedCol + pivotCell.column ]
				//                [ vRotatedRow + pivotCell.row    ]
				double c = vRotatedCol + current.pivotCell.column;
				double r = vRotatedRow + current.pivotCell.row;

				if (r < 0 || r >= rows || c < 0 || c >= columns)
				{
					//Do nothing and let it push off the wall
				}
				 //System.out.println("Row:" + r + "Column" + c );
				 //System.out.println("PIVOT- r: "+ current.pivotCell.row + "c: " + current.pivotCell.column);
				else if (isActive[(int)r][(int)c])
				{
					System.out.println("FAIL")	;
					return false;
				}
			// }

		}
		//If we arrived here we pass all test and move piece down
		current.rotate(Tetromino.Rotation.LEFT);
		return true;
	}

	/**
	 * This function will get information from the board, and set them to
	 * its proper statement depending on if there is an active piece on the grid.
	 * @return 		void
	 */
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
		for (Cell cell : board)
		{
			int r = (int) cell.row;
			int c = (int) cell.column;

			isActive[r][c] = true;
			//System.out.println("Set active at: " + r + " " + c);
		}
	}

	/**
	 * This function goes through the grid to see if any rows are full. If so,
	 * then we delete the row and keep track of how many rows we cleared. After
	 * we delete the row, we will bring everything above it down by one. Repeat
	 * until we check the whole grid.
	 * @return 		int 		The total number of lines cleared
	 */
	int checkAndDelete()
	{
		int linesCleared = 0;
		// for (int i = 0; i < rows; i++)
		// {
		// 	boolean rowIsFilled = true;
		// 	for (int j = 0; j < columns; j++)
		// 	{
		// 		//If row is not filled move onto next line
		// 		if (!isActive[i][j])
		// 			rowIsFilled = false;
		// 	}

		// 	if (rowIsFilled)
		// 	{
		// 		//Update lines cleared for score system
		// 		linesCleared++;
		// 		//Delete the rows
		// 		deleteRow(i);
		// 		//Now bring everything above it down one, above it
		// 		pack(i);
		// 		//Recheck that line
		// 		i--;
		// 	}
		// 	setActive();

		// }

		ArrayList<Integer> rowsToClear = new ArrayList<Integer>();

		for(int i = 0; i < rows; i++)
		{
			boolean rowIsFilled = true;
			for(int j = 0; j < columns; j++)
			{
				if(!isActive[i][j])
					rowIsFilled = false;
			}

			if(rowIsFilled)
			{
				linesCleared++;
				deleteRow(i);
				pack(i);
				//setActive();
				i--;
			}
		}

		// if(linesCleared > 0)
		// 	pack(rowsToClear.get(0), linesCleared);
		return linesCleared;
	}

	

	void printBoard()
	{
		for (int i = (int) (rows - 1); i >= 0; i--)
		{
			for (int j = 0; j < columns; j++)
			{
				if (isActive[i][j])
					System.out.print("x");
				else
					System.out.print("-");
			}
			System.out.println("");
		}
	}

	/**
	 * This function is a helper function for checkAndDelete, which deletes the row
	 * that is given by the parameter.
	 * @param 		int 		row 		The selected row to be deleted
	 * @return 		void
	 */
	void deleteRow(int row)
	{
		System.out.println("ROW TO DELETE: " + row);
		System.out.println("	Board size before delete: " + board.size());
		// for (int i = 0; i < columns; i++)
		// {
		// 	isActive[row][i] = false;
		// }
		int i, j = 0;
		for (Iterator<Cell> it = board.iterator(); it.hasNext();)
		{
			if (it.next().row == row)
			{
				it.remove();
			}
		}

		setActive();
		System.out.println("	Board size after delete: " + board.size());
		printBoard();
	}

	/**
	 * This function is a helper function for checkAndDelete, which compacts the board
	 * after deletion. With the given parameter as the row that was just deleted, we move
	 * everything above it down by one.
	 * @param 		int 		row 		The row that was recently deleted
	 * @return 		void 
	 */
	void pack(int row)
	{
		for (int i = 0; i < board.size(); i++)
		{
			//The last row will need to get place in a temp
			//Replace everything above it with row going down
			if (board.get(i).row > row)
			{
				// Cell temp = new Cell(board.get(i).type, (board.get(i).row - 1), board.get(i).column);
				// board.set(i, temp);
				board.get(i).row--;
			}
		}

		System.out.println("ROW TO PACK: " + row);
		// for(Cell cell : board)
		// {
		// 	if(cell.row > row)
		// 	{
		// 		cell.row--;
		// 	}
		// }

		// for (int i = row; i < rows - 1; i++)
		// {
		// 	//Update the boolean board
		// 	for (int j = 0; j < columns; j++)
		// 	{
		// 		isActive[i][j] = isActive[i + 1][j];
		// 	}
		// }
		// //Make the last row as all false
		// for (int i = 0; i < columns; i++)
		// {
		// 	isActive[(int)rows - 1][i] = false;
		// }

		setActive();
		printBoard();
	}

	int checkAndDeleteFlood()
	{
		int linesCleared = 0;


		ArrayList<Integer> rowsToClear = new ArrayList<Integer>();

		for(int i = (int)rows - 1; i >= 0; i--)
		{
			boolean rowIsFilled = true;
			for(int j = 0; j < columns; j++)
			{
				if(!isActive[i][j])
					rowIsFilled = false;
			}

			if(rowIsFilled)
			{
				linesCleared++;
				deleteRow(i);

				if (i == 0)
					pack(i);
				else
					packFlood(i);

				setActive();
				//Check the line again
				i++;
			}
		}

		// if(linesCleared > 0)
		// 	pack(rowsToClear.get(0), linesCleared);
		return linesCleared;
	}

	void packFlood(int row)
	{
		for (int j = 1; j + row < rows; j++)
		for (int i = 0; i < board.size(); i++)
		{
			// if (board.get(i).row > row && board.get(i).row - 1 > 0)
			// {
			// 		while(!isActive[(int)board.get(i).row - 1][(int)board.get(i).column])
			// 		{
			// 			board.get(i).row--;
			// 			setActive();
			// 			if ((int)board.get(i).row - 1 == 0)
			// 				break;
			// 		}
			// }

			if (board.get(i).row ==  row + j)
			{
				int r = (int) board.get(i).row;
				int c = (int) board.get(i).column;
				while(r > 0 && !isActive[r - 1][c])
				{
					board.get(i).row--;
					r = (int) board.get(i).row;
					c = (int) board.get(i).column;
					setActive();
				}
			}
		}

		System.out.println("ROW TO PACK: " + row);
	
		setActive();
	}


	/**
	 * This function transfer the current tetromino to the board, which only happens
	 * when we cannot move the current tetromino down anymore. Once we transfer the
	 * current tetromino to the board, we set the current piece to the next piece,
	 * then generate a new tetromino in the next piece.
	 * @return 		void
	 */
	void setCurrent()
	{
		int i;
		//Set the current piece down
		for (Cell piece : current.pieces)
		{
			board.add(piece);
			//current.pieces.remove(i);				//Unnecessary step maybe?
		}
		setActive();

		if (collisionFlag)
			isGameOver = true;

		while(collisionOnNext())
		{
			collisionFlag = true;
			System.out.println("Collision whiling?");
			for(Cell cell : next.pieces)
			{
				cell.row++;
			}

			// for(i = next.pieces.size() - 1; i >= 0; i--)
			// {
			// 	if(next.pieces.get(i).row >= rows)
			// 		next.pieces.remove(i);
			// }

			if (next.pieces.size() == 0)
				isGameOver = true;
			
		}


		current = next;

		Random rand = new Random();
		//Get next piece ready
		i = rand.nextInt(7);
		switch(i)
		{
			case 0:
				next = new TetrominoI();
				break;
			case 1:
				next = new TetrominoT();
				break;
			case 2:
				next = new TetrominoO();
				break;
			case 3:
				next = new TetrominoL();
				break;
			case 4:
				next = new TetrominoJ();
				break;
			case 5: 
				next = new TetrominoS();
				break;
			case 6:
				next = new TetrominoZ();
				break;
			default:
				System.out.println("ERROR: randomization has failed with value: " + i);
		}

		

		//setActive();
	}

	boolean collisionOnNext()
	{
		if (next.pieces.size() == 0)
			return false;
		for(Cell cell : next.pieces)
		{
			int r = (int) cell.row;
			int c = (int) cell.column;

			if(isActive[r][c])
				return true;
		}
		return false;
	}

	//This function updates the score
	/**
	 * This function updates the game board when a current piece has settle down.
	 * We update the truth grid, see if there any any full lines and clear. Then
	 * we update the score and total number of lines cleared.
	 * @return 		void
	 */
	void update(boolean naiveGravity)
	{
		//Set the current piece down
		setCurrent();

		//Update the board
		printBoard();

		//Get the total number of lines deleted
		int lineCleared;
		if(naiveGravity)
			lineCleared = checkAndDelete();
		else
			lineCleared = checkAndDeleteFlood();

		//Update score
		switch (lineCleared)
		{
			case 1:
				score += 40 * level;
				lines += lineCleared;
				break;
			case 2:
				score += 100 * level;
				lines += lineCleared;
				break;
			case 3:
				score += 300 * level;
				lines += lineCleared;
				break;
			case 4:
				score += 1200 * level;
				lines += lineCleared;
				break;
			default:  
				return;
		}
		level = lines / 10 + 1;
		int l = level;
		if (level > 24)
			l = 24;
 		delay = ((50.0 - ((double) l * 2.0)) / 60.0) * 1000.0;
	}

	/**
	 * This function updates a list to be render to the GUI. We store the board, and 
	 * the current piece to this list.
	 * @return 			ArrayList <Cell>
	 */
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

		for(Cell cell : current.pieces)
		{

			if(cell.column < 0)
			{
				for(Cell x : current.pieces)
					x.column++;
			}
			else if(cell.column > columns - 1)
			{
				for(Cell x : current.pieces)
					x.column--;
			}
			if(cell.row < 0)
			{
				for(Cell x : current.pieces)
					x.row++;
			}
		}

		//Add the current piece
		for (int i = 0; i < current.pieces.size(); i++)
		{
			Cell temp = current.pieces.get(i);

			newBoard.add(new Cell(temp.type, temp.row, temp.column));
		}

		Shadow.shadow(this.current.pieces, newBoard, isActive);
		return newBoard;
	}

	/**
	 * This function operates when the game is over, when a tetromino that got spawn
	 * has overlapped.
	 * @return  		void
	 */
	//Operate end game
	boolean gameOver()
	{
		return false;
	}

}