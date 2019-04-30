package com.tetris;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class simply contains the main game panel grid and it sets the properties for it.  It also
 * contains a function to render out the current grid onto the main game grid panel.
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
@SuppressWarnings ( "serial" )
class GamePanel extends JPanel {

	/**
	 * This constructor simply sets the background color, position and dimensions of the main game
	 * panel.  It also runs the super constructor with a null layout.
	 */
	public GamePanel () {
		// Initialize the super constructor
		super ( null );
		// Set the background color and the bounds
		super.setBackground ( new Color ( 0x242626 ) );
		super.setBounds ( 225, 25, 250, 500 );
	}

	/**
	 * This function simply takes in an array list of Cell instances and traverses through them and
	 * renders them out onto the game display grid.
	 * @param   ArrayList <Cell>        grid            A list of cell instances
	 * @return  void
	 */
	protected void render ( ArrayList <Cell> grid ) {
		// Remove all the components from the game panel
		this.removeAll ();
		// Traverse through the array list with iterators
		for ( Cell cell : grid ) {
			// Create a JLabel from the cell object and add to panel
			this.add ( Cell.create ( cell ) );
		}
		// Re-validate the panel and repaint it
		this.revalidate ();
		this.repaint ();
	}

}
