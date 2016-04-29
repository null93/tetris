import java.util.ArrayList;

/**
 * This class is the driver class that launches everything and starts the game's graphical user
 * interface and the game's logical worker thread.  This is a simple static class that is just used
 * to get things started!
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
public class Tetris {

	/**
	 * This is the driver main method.  All it does is initialize the graphical user interface and
	 * the worker thread.
	 * @param   String []       args                An array of arguments in string form
	 * @return  void
	 * @static
	 */
	public static void main ( String [] args ) {

		/**
		 * This section is an example on who you would interact with the Game panel.
		 */

		// // Create a GUI instance ( You will need to save this internally )
		// GUI gui = new GUI ();

		// // Render out the frame
		// gui.render ();

		// // Create a new array list of Cell instances
		// ArrayList <Cell> grid = new ArrayList <Cell> ();

		// // Add data to the grid array ( type, row, column )
		// grid.add ( new Cell ( Shape.L, 1, 1 ) );
		// grid.add ( new Cell ( Shape.L, 1, 2 ) );
		// grid.add ( new Cell ( Shape.L, 1, 3 ) );
		// grid.add ( new Cell ( Shape.L, 2, 3 ) );
		// grid.add ( new Cell ( Shape.O, 2, 1 ) );
		// grid.add ( new Cell ( Shape.O, 2, 2 ) );
		// grid.add ( new Cell ( Shape.O, 3, 1 ) );
		// grid.add ( new Cell ( Shape.O, 3, 2 ) );
		// grid.add ( new Cell ( Shape.T, 1, 4 ) );
		// grid.add ( new Cell ( Shape.T, 1, 5 ) );
		// grid.add ( new Cell ( Shape.T, 1, 6 ) );
		// grid.add ( new Cell ( Shape.T, 2, 5 ) );
		// grid.add ( new Cell ( Shape.I, 1, 7 ) );
		// grid.add ( new Cell ( Shape.I, 1, 8 ) );
		// grid.add ( new Cell ( Shape.I, 1, 9 ) );
		// grid.add ( new Cell ( Shape.I, 1, 10 ) );
		// grid.add ( new Cell ( Shape.S, 2, 6 ) );
		// grid.add ( new Cell ( Shape.S, 2, 7 ) );
		// grid.add ( new Cell ( Shape.S, 3, 7 ) );
		// grid.add ( new Cell ( Shape.S, 3, 8 ) );
		// grid.add ( new Cell ( Shape.J, 2, 8 ) );
		// grid.add ( new Cell ( Shape.J, 2, 9 ) );
		// grid.add ( new Cell ( Shape.J, 3, 9 ) );
		// grid.add ( new Cell ( Shape.J, 4, 9 ) );
		// grid.add ( new Cell ( Shape.Z, 3, 6 ) );
		// grid.add ( new Cell ( Shape.Z, 4, 6 ) );
		// grid.add ( new Cell ( Shape.Z, 4, 7 ) );
		// grid.add ( new Cell ( Shape.Z, 5, 7 ) );

		// // Call the render function to render out the grid in the game panel
		// gui.renderGrid ( grid );

		// /**
		//  * This section shows how to render out the next piece display
		//  */
		
		// gui.renderNext ( Shape.Z );

		// /**
		//  * This section shows how to change all the display screens such as time, score, lines, and
		//  * level.
		//  */
		
		// // Update the time display ( int seconds )
		// gui.updateTime ( 985 );

		// // Update the score display ( int score )
		// gui.updateScore ( 8228 );

		// // Update the level display ( int level )
		// gui.updateLevel ( 3 );

		// // Update the line display ( int lines )
		// gui.updateLines ( 34 );

		GameLoop gameLoop = new GameLoop();
		Thread thread = new Thread(gameLoop);
		thread.start();
		/**
		 * Function that need to be integrated with the logical classes are as follows.  Side note:
		 * Please look through the functions in GUI.java in order to understand which tools are
		 * already provided for you to use.
		 */
		
		// GUI::moveLeft
		// GUI::moveRight
		// GUI::rotateLeft
		// GUI::rotateRight
		// GUI::softDrop
		// GUI::hardDrop
		// GUI::pause
		// GUI::restart

	}

}