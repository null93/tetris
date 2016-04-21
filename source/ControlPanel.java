import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import Graphic.Button;

/**
 * This class implements the JPanel class and serves as a container that holds all the control
 * buttons and the sub panel to display the next upcoming Tetris piece.  It also contains a function
 * to render said Tetris piece onto the panel.
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
class ControlPanel extends JPanel {

	/**
	 * This data member holds the panel that displays the next game piece that will be spawned.  It
	 * is a panel, because it uses the static Cell function to populate it.
	 * @var     JPanel          nextPiece           The next game piece panel
	 */
	private JPanel nextPiece;

	/**
	 * This data member is the button that is used to move the current Tetris piece to the left.
	 * @var     Button          moveLeft            The move left button
	 */
	protected Button moveLeft;

	/**
	 * This data member is the button that is used to move the current Tetris piece to the right.
	 * @var     Button          moveRight           The move right button
	 */
	protected Button moveRight;

	/**
	 * This data member is the button that is used to rotate the current Tetris piece to the left.
	 * @var     Button          rotateLeft          The rotate left button
	 */
	protected Button rotateLeft;

	/**
	 * This data member is the button that is used to rotate the current Tetris piece to the right.
	 * @var     Button          rotateRight         The rotate right button
	 */
	protected Button rotateRight;

	/**
	 * This data member is the hard drop button that is used to drop the current Tetris piece right
	 * away to the bottom.
	 * @var     Button          hardDrop            The hard drop button
	 */
	protected Button hardDrop;

	/**
	 * This data member is the soft drop button that is used to drop the current Tetris piece
	 * incrementally, and does down in a fast manner, row by row.
	 * @var     Button          softDrop            The soft drop button
	 */
	protected Button softDrop;

	/**
	 * This data member is the gravity toggle button that toggles between the flood fill mode and
	 * the naive gravity mode.  Flood fill moves all the blocks down without gaps.
	 * @var     Button          gravity             The gravity toggle button
	 */
	protected Button gravity;

	/**
	 * This data member is the play / pause toggle button that simple either pauses the game or
	 * resets the game to play.
	 * @var     Button          pause               The play / pause button
	 */
	protected Button pause;

	/**
	 * This data member is the play / pause toggle button that simple either mutes or plays the
	 * theme music.
	 * @var     Button          music               The mute / un-mute button
	 */
	protected Button music;

	/**
	 * This constructor initializes all the buttons and the next piece panel and appends it to the
	 * control panel.
	 */
	public ControlPanel () {
		// Call the super constructor and initialize the JPanel
		super ( null );
		// Set the background color and the bounds
		super.setBackground ( new Color ( 0x1D1F1F ) );
		super.setBounds ( 500, 25, 175, 500 );
		// Create the JPanel for the next piece and attach it internally
		this.nextPiece = new JPanel ( null );
		this.nextPiece.setBackground ( new Color ( 0x242626 ) );
		this.nextPiece.setBounds ( 0, 0, 175, 105 );
		// Create all the buttons
		this.moveLeft = createButton ( "./assets/images/Left.png", 75, 40, 0, 133 );
		this.moveRight = createButton ( "./assets/images/Right.png", 75, 40, 100, 133 );
		this.rotateLeft = createButton ( "./assets/images/Counter-Clockwise.png", 75, 40, 0, 198 );
		this.rotateRight = createButton ( "./assets/images/Clockwise.png", 75, 40, 100, 198 );
		this.softDrop = createButton ( "Soft Drop", 175, 40, 0, 265 );
		this.hardDrop = createButton ( "Hard Drop", 175, 40, 0, 330 );
		this.gravity = createButton ( "Naive Gravity", 175, 40, 0, 395 );
		this.pause = createButton ( "Pause", 75, 40, 0, 460 );
		this.music = createButton ( "Mute", 75, 40, 100, 460 );
		// Set a smaller font for these buttons
		this.pause.setFont ( new Font ( "Muli", Font.PLAIN, 10 ) );
		this.music.setFont ( new Font ( "Muli", Font.PLAIN, 10 ) );
		// Add all these elements to the control panel
		this.add ( this.nextPiece );
		this.add ( this.moveLeft );
		this.add ( this.moveRight );
		this.add ( this.rotateLeft );
		this.add ( this.rotateRight );
		this.add ( this.softDrop );
		this.add ( this.hardDrop );
		this.add ( this.gravity );
		this.add ( this.pause );
		this.add ( this.music );
	}

	/**
	 * This is a helper function that is able to create a button given the dimensions, position, and
	 * value.  If the value contains an image URL, then an image is loaded, otherwise the text is
	 * displayed.
	 * @param   String          src                 The text for the button or the URL to the image
	 * @param   int             width               The width of the button
	 * @param   int             height              The height of the button
	 * @param   int             x                   The horizontal position of the button
	 * @param   int             y                   The vertical position of the button
	 * @return  Button                              The resulting constructed button
	 */
	private Button createButton ( String src, int width, int height, int x, int y ) {
		// Create a new button with the dimensions
		Button button = new Button ( "", width, height );
		// Set the button properties
		button.setPosition ( x, y );
		button.setBackground ( new Color ( 0x242626 ) );
		button.setForeground ( new Color ( 0xF6FAFA ) );
		button.setHighlight ( new Color ( 0xF6FAFA ), new Color ( 0x313333 ) );
		// Set the icon image if the src contains an href to a PNG image
		if ( src.contains (".png") ) {
			// Add the image to the button
			try {
				Image image = ImageIO.read ( getClass ().getResource ( src ) );
				button.setIcon ( new ImageIcon ( image ) );
			}
			catch ( Exception exception ) {}
		}
		// Otherwise set the text
		else {
			// Set the text for the button
			button.setText ( src );
		}
		// Return the button
		return button;
	}

	/**
	 * This function simply clears the next piece panel, and renders out the new next piece in the
	 * next piece panel.
	 * @param   Shape           type                The Shape enum of which piece to render
	 * @return  void
	 */
	protected void renderNext ( Shape type ) {
		// Empty out the next panel
		this.nextPiece.removeAll ();
		// Check if it is null
		if ( type == null ) {
			// Re-validate the panel and repaint it
			this.nextPiece.revalidate ();
			this.nextPiece.repaint ();
			return;
		}
		// Switch between the types
		switch ( type ) {
			case I:
				// Append the appropriate pieces
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2.5, 2.5 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2.5, 3.5 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2.5, 4.5 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2.5, 5.5 ) ) );
				break;
			case T:
				// Append the appropriate pieces
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 3 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 4 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 5 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2, 4 ) ) );
				break;
			case O:
				// Append the appropriate pieces
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 3.5 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2, 3.5 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 4.5 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2, 4.5 ) ) );
				break;
			case L:
				// Append the appropriate pieces
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2, 3 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2, 4 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2, 5 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 5 ) ) );
				break;
			case J:
				// Append the appropriate pieces
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 3 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 4 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 5 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2, 5 ) ) );
				break;
			case S:
				// Append the appropriate pieces
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2, 3 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 4 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2, 4 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 5 ) ) );
				break;
			case Z:
				// Append the appropriate pieces
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 3 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 3, 4 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2, 4 ) ) );
				this.nextPiece.add ( Cell.createNext ( new Cell ( type, 2, 5 ) ) );
				break;
		}
		// Re-validate the panel and repaint it
		this.nextPiece.revalidate ();
		this.nextPiece.repaint ();
	}

}