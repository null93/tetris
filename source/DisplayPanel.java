import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This class is the panel that contains a number of sub panels that display the game uptime, score,
 * level, and lines.  It also contains functions that are protected that can update these values.
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
class DisplayPanel extends JPanel {

	/**
	 * This data member holds the label that contains the value within the time display panel.
	 * @var     JLabel          time                The label containing the time elapsed
	 */
	private JLabel time;

	/**
	 * This data member holds the label that contains the value within the score display panel.
	 * @var     JLabel          score               The label containing the score
	 */
	private JLabel score;

	/**
	 * This data member holds the label that contains the value within the level display panel.
	 * @var     JLabel          level               The label containing the current level
	 */
	private JLabel level;

	/**
	 * This data member holds the label that contains the value within the lines display panel.
	 * @var     JLabel          lines               The label containing the lines that were cleared
	 */
	private JLabel lines;

	/**
	 * This constructor initializes the sub panels that contain various information about the game.
	 * It also adds it to the Display panel.
	 */
	public DisplayPanel () {
		// Initialize the super constructor
		super ( null );
		// Set the background color and the bounds
		super.setBackground ( new Color ( 0x1D1F1F ) );
		super.setBounds ( 25, 25, 175, 500 );
		// Set and save all the display panels internally
		this.time = createDisplay ( "TIME", "0:00:00", 0, 0 );
		this.score = createDisplay ( "SCORE", "0", 0, 133 );
		this.level = createDisplay ( "LEVEL", "0", 0, 265 );
		this.lines = createDisplay ( "LINES", "0", 0, 395 );
	}

	/**
	 * This function creates a display sub panel given a display title, an initial value and the
	 * desired position of the display relative to this parent panel.  It then returns the JLabel
	 * that holds the displays value.
	 * @param   String          title               The title of the display
	 * @param   String          value               The initial value for the display
	 * @param   int             x                   The horizontal position of the display
	 * @param   int             y                   The vertical position of the display
	 * @return  JLabel                              Returns the JLabel holding the value of display
	 */
	private JLabel createDisplay ( String title, String value, int x, int y ) {
		// Create container panel first and set the properties
		JPanel panel = new JPanel ( null );
		panel.setBackground ( new Color ( 0x242626 ) );
		panel.setBounds ( x, y, 175, 105 );
		// Create the title JLabel and set the properties and append
		JLabel tag = new JLabel ( title, SwingConstants.CENTER );
		tag.setForeground ( new Color ( 0xF6FAFA ) );
		tag.setBounds ( 0, 10, 175, 40 );
		tag.setFont ( new Font ( "Muli", Font.PLAIN, 28 ) );
		panel.add ( tag );
		// Create the value JLabel and set the properties and append
		JLabel val = new JLabel ( value, SwingConstants.CENTER );
		val.setForeground ( new Color ( 0x3A3A3A ) );
		val.setBounds ( 0, 50, 175, 40 );
		val.setFont ( new Font ( "Muli", Font.PLAIN, 26 ) );
		panel.add ( val );
		// Add the panel to the parent panel
		this.add ( panel );
		// Return the value panel
		return val;
	}

	/**
	 * This function takes in the number of uptime seconds, and it updates the display after
	 * formatting it into a time string.
	 * @param   int             seconds             The seconds of the game's uptime
	 * @return  void
	 */
	protected void updateTime ( int seconds ) {
		// Parse the seconds to display in proper time format
		String time = String.format (
			"%d:%02d:%02d",
			seconds / 3600,
			( seconds % 3600 ) / 60,
			( seconds % 60 )
		);
		// Update the time display
		this.time.setText ( time );
	}

	/**
	 * This updates the score in the score display given the new score in the passed parameter.
	 * @var     int             score               The new score to update to
	 * @return  void
	 */
	protected void updateScore ( int score ) {
		// Update the score label
		this.score.setText ( score + "" );
	}

	/**
	 * This function updates the level in the level display with the passed level in the parameter.
	 * @var     int             level               The new level to update to
	 * @return  void
	 */
	protected void updateLevel ( int level ) {
		// Update the level label
		this.level.setText ( level + "" );
	}

	/**
	 * This function updates the lines in the lines display with the passed lines in the parameter.
	 * @var     int             lines               The new lines to update to
	 * @return  void
	 */
	protected void updateLines ( int lines ) {
		// Update the lines label
		this.lines.setText ( lines + "" );
	}

}