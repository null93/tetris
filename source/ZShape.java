/**
 * This class is an inheritance of the Tetromino. This piece is the Z shape piece.
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

public class ZShape extends Tetromino
{
	/* This constructor creates an Z shape tetromino */
	public ZShape()
	{
		super(3);

		matrix[0][0] = new Block(0, 0, Shape.Z);
		matrix[0][1] = new Block(0, 1, Shape.Z);
		matrix[1][1] = new Block(1, 1, Shape.Z);
		matrix[1][2] = new Block(1, 2, Shape.Z);
	}
	
}