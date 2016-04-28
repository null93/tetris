import java.util.ArrayList;
/**
 *
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
public class TetrominoI extends Tetromino
{
	public TetrominoI()
	{
		super();
		Cell pos1 = new Cell(Shape.I, Board.rows - 1, (double)(((int)Board.columns / 2)));
		Cell pos2 = new Cell(Shape.I, Board.rows - 2, (double)(((int)Board.columns / 2)));
		Cell pos3 = new Cell(Shape.I, Board.rows - 3, (double)(((int)Board.columns / 2)));
		Cell pos4 = new Cell(Shape.I, Board.rows - 4, (double)(((int)Board.columns / 2)));

		pieces.add(pos1);
		pieces.add(pos2);
		pieces.add(pos3);
		pieces.add(pos4);
	}
}