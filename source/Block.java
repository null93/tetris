//This object will hold description of each block
public class Block
{
	private int status;		// 0 - Empty
							// 1 - Filled/Non-Active
							// 2 - Active
	
	//For GUI component
	private int color;		//0 - Empty Block (White)
							//1 - L Shape
							//2 - T Shape
							//3 - O Shape
							//4 - L Shape
							//5 - J Shape
							//6 - S Shape
							//7 - Z Shape
	
	//For bookkeeping the position
	private int xPos;
	private int yPos;
	
	//Constructor of a block
	public Block(int row, int col)
	{
		status = 0;	//Initialize each block to be empty
		color = 0;	//The initial color will be display as empty
		xPos = row;
		yPos = col;
	}
	
	int getStatus()
	{
		return status;
	}
	
	void setStatus(int val)
	{
		status = val;
	}
	
	int getColor()
	{
		return color;
	}
	
	void setColor(int val)
	{
		color = val;
	}
	
}
