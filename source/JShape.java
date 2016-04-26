/**
 * This class is an inheritance of the Tetromino. This piece is the J shape piece.
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

public class JShape extends Tetromino
{
	/* This constructor creates an J shape tetromino */
	public JShape()
	{
		super(3);

		matrix[0][1] = new Block(0, 1, Shape.J);
		matrix[1][1] = new Block(1, 1, Shape.J);
		matrix[2][1] = new Block(2, 1, Shape.J);
		matrix[2][0] = new Block(2, 0, Shape.J);
	}
	
}