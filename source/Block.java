/**
 * This class will contain all the information required for each block of the
 * board. This component will be used to tranfer information to the GUI.
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
public class Block
{
	/**
	 * This data member stores the block's position
	 * @var     Coordinate          coord              The position of the block
	 */
	protected Coordinate coord;

	/**
	 * This data member stores what block type this objects belongs part of
	 * @var		Shape				type				The type of block this is part of
	 */
	protected Shape type;

	/**
	 * This data member inform if this block is currently in used or not
	 * @var		Boolean				isFilled			Determine if this block is in used or not	
	 */
	protected boolean isFilled;

	/* This constructor takes the block's position and type as the parameter and saves 
	 * all the data member internally.
	 */
	public Block(int row, int col, Shape info)
	{
		coord = new Coordinate(row, col);
		type = info;
		if (info == Shape.Empty)
			isFilled = false;
		else
			isFilled = true;
	}

	/**
	 * This function allows us to change the block information by the given parameters
	 * @param 	int 				row					The new row positon of this block
	 * @param 	int 				col					The new column positon of this block
	 * @param 	Shape 				info				The new shape type of this block
	 * @return 	void
	 */
	protected void setBlock(int row, int col, Shape info)
	{
		coord = new Coordinate(row, col);
		type = info;
		if (info == Shape.Empty)
			isFilled = false;
		else
			isFilled = true;
	}


	/**
	 * This functions access the type of this block and returns its value
	 * @return Shape 									Used to get the shape type/color
	 */
	protected Shape getType()
	{
		return type;
	}

	/**
	 * This functions access the type of this block and returns its value
	 * @return int 										Used to get the row position
	 */
	protected int getRow()
	{
		return coord.getRow();
	}

	/**
	 * This functions access the type of this block and returns its value
	 * @return int 										Used to get the row position
	 */
	protected int getCol()
	{
		return coord.getCol();
	}

	/**
	 * This functions access the type of this block and returns its value
	 * @return Coordinate 								Used to get the position
	 */
	protected Coordinate getCoord()
	{
		return coord;
	}

	/**
	 * This function will allow us to change the coordinates of an object, by giving the proper
	 * location of the object. This is a function overload to take in Coordinates as a parameter
	 * @param 	Coordinate 	newCoord		The new position
	 * @return	void
	 */
	protected void setCoordinate(Coordinate newCoord)
	{
		coord.setRow(newCoord.getRow());
		coord.setCol(newCoord.getCol());
	}
}