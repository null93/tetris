//This object acts as the game board
//For our case we will let the dimension be 10 x 20
public class Board
{
	//Instance Variable
	protected Block[][] board;
	
	//Dimension of the board
	private int rows;
	private int columns;
	
	private int level;
	private int gravity;		// 0 - Soft gravity
								// 1 - Hard gravity
	private int score;
	
	private boolean isPause;
	
	private int lineCleared;
	
	
	
	//Constructor for the board
	public Board()
	{
		rows = 20;
		columns = 10;
		board = new Block[rows][columns];
		
		//Start at level 1
		level = 1;
		//The game initialize gravity as hard by default
		gravity = 1;
		//Initial score starts at 0
		score = 0;
		
		//Initialize each block
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				board[i][j] = new Block(i, j);
			}
		}
		
		//The game initialize to pause status
		isPause = true;
		
		lineCleared = 0;
	}
	
	//TODO
	//Check if any of the rows are filled and clear them
	private int checkBoard()
	{
		int counter = 0;
		boolean isCleared;
		
		for (int i = 0; i < rows; i++)
		{
			isCleared = true;
			for (int j = 0; j < columns; j++)
			{
				if (board[i][j].getStatus() != 1)
				{
					isCleared = false;
				}
			}
			
			if (isCleared)	//If this current row is filled clear up the row
			{
				//Delete row & increment counter for total lines cleared
				/*** MAKE DELETE ROW FUNCTION ***/
				deleteRow(i);
				counter++;
				lineCleared++;
			}
			
		}

		//If one or more rows have been cleared
		if (counter > 0)
		{
			//Pack the board
			pack();
		}
		//Return the total number of rows cleared
		return counter;
	}
	

	
	private void deleteRow(int row)
	{
		//Clear the current row
		for (int i = 0; i < columns; i++)
		{
			board[row][i].setStatus(0);
			board[row][i].setColor(0);
		}
	}
	
	//Return true if the row is cleared
	private boolean isCleared(int row)
	{
		boolean isCleared = true;
		for (int i = 0; i < columns; i++)
		{
			if (board[row][i].getStatus() != 0)
				isCleared = false;
		}
		
		return isCleared;
	}
	
	//Copy the row from source to destination
	private void copyRow(int src, int dest)
	{
		for (int i = 0; i < columns; i++)
		{
			board[dest][i].setStatus(board[src][i].getStatus());
			board[dest][i].setColor(board[src][i].getColor());
		}
	}
	
	//Pack the board after lines are cleared
	private void pack()
	{
		//Check if each row is cleared
		for (int i = 0; i < rows; i++)
		{
			//The current row is cleared so move everything above down
			if (isCleared(i))
			{
				//Get the next line that is not cleared
				int j = i + 1;
				while (j < rows && isCleared(j))
				{
					j++;
				}
				
				//At the top of the highest level
				if (j == rows)
					return;
				
				//Transfer the next row down
				else
				{
					copyRow(j, i);
					deleteRow(j);
				}
			}
		}
	}
	
	//Summon new piece to the board
	private void callNew()
	{
		//Randomly pick one of the tetrominoes
	}
	
	//The given parameter takes the total number of rows cleared
	private int updateScore(int row)
	{
		switch(row)
		{
		case 1:		return (40 * level);
		case 2:		return (100 * level);
		case 3: 	return (300 * level);
		case 4:		return (1200 * level);
		default:	return 0;
		}
	}
	
	//Simulate each frame
	private void simulate()
	{
		//Checks how many rows are filled and refresh the score and frame
		score = updateScore(checkBoard());
	}
	
	
}
