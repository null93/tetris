/**
 * This class is an inheritance of the Tetromino. This piece is the S shape piece.
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

public class SShape extends Tetromino
{
	/* This constructor creates an S shape tetromino */
	public SShape()
	{
		super(3);

		matrix[0][1] = new Block(0, 1, Shape.S);
		matrix[0][2] = new Block(0, 2, Shape.S);
		matrix[1][0] = new Block(1, 0, Shape.S);
		matrix[1][1] = new Block(1, 1, Shape.S);
	}
	
}