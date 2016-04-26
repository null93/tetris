/**
 * This class is an inheritance of the Tetromino. This piece is the I shape piece.
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

public class IShape extends Tetromino
{
	/* This constructor creates an I shape tetromino */
	public IShape()
	{
		super(4);

		matrix[0][1] = new Block(0, 1, Shape.I);
		matrix[1][1] = new Block(1, 1, Shape.I);
		matrix[2][1] = new Block(2, 1, Shape.I);
		matrix[3][1] = new Block(3, 1, Shape.I);
	}
	
}