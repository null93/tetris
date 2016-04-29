import javax.swing.border.MatteBorder;
import javax.swing.JLabel;

/**
 * This class is essentially implemented in order to bind data together into a tuple like structure.
 * It stores the type of shape and the row and column coordinates internally.  They are public for
 * ease of use, and it also contains static functions that are used to create JLabels that are used
 * for the GUI section for the main grid and the next game piece grid.
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
public class Cell {

	/**
	 * This data member stores the cells type and it is part of the purpose of this class, which is
	 * to bind data together.
	 * @var     Shape           type                The type of Tetris block
	 */
	public Shape type;

	/**
	 * This data member stores the cells row position and it is part of the purpose of this class,
	 * which is to bind data together.  It is a double on purpose, if we want to position the cell
	 * in between two unit positions of a cell in the grid.
	 * @var     double          row                 The row of the cell
	 */
	public double row;

	/**
	 * This data member stores the cells column position and it is part of the purpose of this
	 * class, which is to bind data together.  It is a double on purpose, if we want to position the
	 * cell in between two unit positions of a cell in the grid.
	 * @var     double          column              The column of the cell
	 */
	public double column;

	/**
	 * This constructor simply takes in the shape type, the row position and the column position and
	 * saves all the data members internally.
	 */
	public Cell ( Shape type, double row, double column ) {
		// Save all the values
		this.type = type;
		this.row = row;
		this.column = column;
	}

	/**
	 * This static function simply returns a JLabel that can be appended to the main game grid using
	 * the data members within the Cell class.
	 * @param   Cell            cell                The data which we will use to create the JLabel
	 * @return  JLabel                              Used for the main game panel
	 * @static
	 */
	protected static JLabel create ( Cell cell ) {
		// Create a new label and set the properties
		JLabel label = new JLabel ();
		label.setOpaque ( true );
		label.setBackground ( Shape.color ( cell.type ) );
		label.setBorder ( new MatteBorder ( 5, 5, 5, 5, Shape.border ( cell.type ) ) );
		label.setBounds (
			( int ) ( 25 * ( cell.column) ),
			( int ) ( 25 * ( 19 - cell.row ) ),
			25, 25
		);
		// Return the label
		return label;
	}

	/**
	 * This static function simply returns a JLabel that can be appended to the next game piece grid
	 * using the data members within the Cell class.
	 * @param   Cell            cell                The data which we will use to create the JLabel
	 * @return  JLabel                              Used for the next game piece panel
	 * @static
	 */
	protected static JLabel createNext ( Cell cell ) {
		// Create a new label and set the properties
		JLabel label = new JLabel ();
		label.setOpaque ( true );
		label.setBackground ( Shape.color ( cell.type ) );
		label.setBorder ( new MatteBorder ( 5, 5, 5, 5, Shape.border ( cell.type ) ) );
		label.setBounds (
			( int ) ( 25 * ( cell.column - 1 ) ),
			( int ) ( 3 + ( 25 * ( 4 - cell.row ) ) ),
			25, 25
		);
		// Return the label
		return label;
	}

}