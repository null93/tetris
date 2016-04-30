import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Object;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Graphic.Display;

/**
 * This class handles and initializes every element in the graphical user interface.  It initializes
 * the main panels, and the theme music as well as the menu items.  It also handles all the event
 * listeners in this class.  Since there are multiple event listeners that will serve the same
 * purpose, but are triggered differently, there are handler functions for those actions.  That way
 * there is no useless repetition.
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
public class GUI extends Display implements ActionListener, KeyListener {

	/**
	 * This data member holds the instance that is created in the constructor of the container panel
	 * that displays the main game grid.
	 * @var     GamePanel       gamePanel           The main game panel grid
	 */
	private GamePanel gamePanel;

	/**
	 * This data member holds the instance that is created in the constructor of the container panel
	 * that displays all the informational display sub panels.
	 * @var     DisplayPanel    displayPanel        The container containing informational displays
	 */
	private DisplayPanel displayPanel;

	/**
	 * This data member holds the instance that is created in the constructor of the container panel
	 * that displays all the game control buttons and the next piece grid sub panel.
	 * @var     ControlPanel    controlPanel        The container containing the game controls
	 */
	private ControlPanel controlPanel;

	/**
	 * This data member contains the clip instance that is responsible for playing the theme song.
	 * It is saved, because you are able to mute the audio with the in game controls.
	 * @var     Clip            clip                The theme music clip
	 */
	private Clip clip;

	protected Board board;

	protected HighscoresManager highscoresManager;

	protected boolean pause;

	/**
	 * This constructor creates all the main panels and appends it to the main window panel. It also
	 * initializes the theme music and creates the menu items.  In addition it also attaches all the
	 * event listeners.
	 */
	public GUI () {
		// Define the frame title using super constructor and set the background color
		super ( "Tetris", 700, 590 );
		super.panel.setBackground ( new Color ( 0x1D1F1F ) );
		// Initialize the panels
		this.gamePanel = new GamePanel ();
		this.displayPanel = new DisplayPanel ();
		this.controlPanel = new ControlPanel ();
		this.board = new Board(20, 10);
		//System.out.println("IN GUI delay: " + this.board.delay);
		this.highscoresManager = new HighscoresManager();

		this.pause = false;

		// Add all the elements into the main panel
		super.panel.add ( this.gamePanel );
		super.panel.add ( this.displayPanel );
		super.panel.add ( this.controlPanel );
		
		// Add the buttons to the action listener
		this.controlPanel.moveLeft.addActionListener ( this );
		this.controlPanel.moveRight.addActionListener ( this );
		this.controlPanel.rotateLeft.addActionListener ( this );
		this.controlPanel.rotateRight.addActionListener ( this );
		this.controlPanel.softDrop.addActionListener ( this );
		this.controlPanel.hardDrop.addActionListener ( this );
		this.controlPanel.gravity.addActionListener ( this );
		this.controlPanel.pause.addActionListener ( this );
		this.controlPanel.music.addActionListener ( this );

		// Add to itself as a key listener and set focus properties
		super.addKeyListener ( this );
		super.setFocusable ( true );
		super.setFocusTraversalKeysEnabled ( false );
		// Set the audio player to play theme song
		try {
			// Load in the audio file as a stream and play it
			AudioInputStream audio = AudioSystem.getAudioInputStream (
				this.getClass ().getResource ( "./assets/audio/Theme.wav" )
			);
			this.clip = AudioSystem.getClip ();
			this.clip.open ( audio );
			this.clip.loop ( Clip.LOOP_CONTINUOUSLY );
		}
		// Catch any exception, if caught, we want to ignore this error
		catch ( Exception exception ) {}
		// Create the menu bar
		JMenuBar menu = new JMenuBar ();
		// Create the menus
		JMenu file = new JMenu ( "File" );
		JMenu information = new JMenu ( "Information" );
		// Create all the menu items
		JMenuItem restart = new JMenuItem ( "Restart Game" );
		JMenuItem quit = new JMenuItem ( "Quit" );
		JMenuItem highscores = new JMenuItem( "Highscores" );
		JMenuItem about = new JMenuItem ( "About" );
		JMenuItem help = new JMenuItem ( "Help" );

		// Attach an action listener to the items
		restart.addActionListener ( new ActionListener () {
			@Override
			public void actionPerformed ( ActionEvent event ) {
				restart ();
			}
		});
		quit.addActionListener ( new ActionListener () {
			@Override
			public void actionPerformed ( ActionEvent event ) {
				quit ();
			}
		});
		highscores.addActionListener ( new ActionListener () {
			@Override
			public void actionPerformed ( ActionEvent event ) {
				highscores ();
			}
		});
		about.addActionListener ( new ActionListener () {
			@Override
			public void actionPerformed ( ActionEvent event ) {
				about ();
			}
		});
		help.addActionListener ( new ActionListener () {
			@Override
			public void actionPerformed ( ActionEvent event ) {
				help ();
			}
		});
		// Attach key shortcuts
		file.setMnemonic ( KeyEvent.VK_F );
		information.setMnemonic ( KeyEvent.VK_I );
		restart.setMnemonic ( KeyEvent.VK_R );
		quit.setMnemonic ( KeyEvent.VK_Q );
		highscores.setMnemonic( KeyEvent.VK_S);
		about.setMnemonic ( KeyEvent.VK_A );
		help.setMnemonic ( KeyEvent.VK_H );
		// Append the items to the file menu
		file.add ( restart );
		file.add ( quit );
		// Append the items to the information menu
		information.add( highscores );
		information.add ( about );
		information.add ( help );
		// Add the menus to the menu bar
		menu.add ( file );
		menu.add ( information );
		// Set the menu bar to be the created one
		super.setJMenuBar ( menu );
	}

	/**
	 * This function simply forwards the the request to the appropriate main panel, and it does not
	 * handle the request directly.
	 * @param   ArrayList <Cell>    grid            The array of cell pieces to render
	 * @return  void
	 */
	protected void renderBoard ( ) {
		// Forward back to the game panel

		this.gamePanel.render ( this.board.render() );
	}

	/**
	 * This function simply forwards the the request to the appropriate main panel, and it does not
	 * handle the request directly.
	 * @param   Shape           type                The type of shape to render in next panel grid
	 * @return  void
	 */
	protected void renderNext ( Shape type ) {
		// Forward back to the control panel
		this.controlPanel.renderNext ( type );
	}

	/**
	 * This function simply forwards the the request to the appropriate main panel, and it does not
	 * handle the request directly.
	 * @param   int             seconds             The number of seconds elapsed in the game
	 * @return  void
	 */
	protected void updateTime ( int seconds ) {
		// Forward this to the display panel
		this.displayPanel.updateTime ( seconds );
	}

	/**
	 * This function simply forwards the the request to the appropriate main panel, and it does not
	 * handle the request directly.
	 * @param   int             score               The new score to set display
	 * @return  void
	 */
	protected void updateScore ( int score ) {
		// Forward this to the display panel
		this.displayPanel.updateScore ( score );
	}

	/**
	 * This function simply forwards the the request to the appropriate main panel, and it does not
	 * handle the request directly.
	 * @param   int             level               The new level to set display
	 * @return  void
	 */
	protected void updateLevel ( int level ) {
		// Forward this to the display panel
		this.displayPanel.updateLevel ( level );
	}

	/**
	 * This function simply forwards the the request to the appropriate main panel, and it does not
	 * handle the request directly.
	 * @param   int             lines               The new number of cleared lines to display
	 * @return  void
	 */
	protected void updateLines ( int lines ) {
		// Forward this to the display panel
		this.displayPanel.updateLines ( lines );
	}

	/**
	 * This function simply returns which gravity option the user picked.  If naive gravity is
	 * chosen then true is returned, otherwise if its flood fill, then false is returned.
	 * @return  boolean                             True if Naive gravity, false if flood fill
	 */
	protected boolean getGravity () {
		// Get the current value
		String current = this.controlPanel.gravity.getText ();
		// Which gravity is currently set
		if ( current.equals ( "Flood Fill" ) ) {
			// Return false
			return false;
		}
		// By default return true
		return true;
	}

	/**
	 * This function is used in event listeners and simply forwards the request to the appropriate
	 * class that handles the actual game logic.  This function does not handle the result directly.
	 * @return  void
	 */
	protected void moveLeft () {
		//System.out.println ( "MOVE LEFT!" );
		// Run the appropriate handler from the logic class
		//
		//
		if(!this.board.isGameOver && !this.pause)
		{
			this.board.moveLeft();
			this.renderBoard();
		}
	}

	/**
	 * This function is used in event listeners and simply forwards the request to the appropriate
	 * class that handles the actual game logic.  This function does not handle the result directly.
	 * @return  void
	 */
	protected void moveRight () {
		//System.out.println ( "MOVE RIGHT!" );
		// Run the appropriate handler from the logic class
		//
		//
		if(!this.board.isGameOver && !this.pause)
		{
			this.board.moveRight();
			this.renderBoard();
		}
	}

	/**
	 * This function is used in event listeners and simply forwards the request to the appropriate
	 * class that handles the actual game logic.  This function does not handle the result directly.
	 * @return  void
	 */
	protected void rotateLeft () {
		//System.out.println ( "ROTATE LEFT!" );
		// Run the appropriate handler from the logic class
		//
		//
		if(!this.board.isGameOver && !this.pause)
		{
			this.board.current.rotate(Tetromino.Rotation.LEFT);
			this.renderBoard();
		}
	}

	/**
	 * This function is used in event listeners and simply forwards the request to the appropriate
	 * class that handles the actual game logic.  This function does not handle the result directly.
	 * @return  void
	 */
	protected void rotateRight () {
		//System.out.println ( "ROTATE RIGHT!" );
		// Run the appropriate handler from the logic class
		//
		//
		if(!this.board.isGameOver && !this.pause)
		{
			this.board.current.rotate(Tetromino.Rotation.RIGHT);
			this.renderBoard();
		}
	}

	/**
	 * This function is used in event listeners and simply forwards the request to the appropriate
	 * class that handles the actual game logic.  This function does not handle the result directly.
	 * @return  void
	 */
	protected void softDrop () {
		//System.out.println ( "SOFT DROP!" );
		// Run the appropriate handler from the logic class
		//
		//
		if(!this.board.isGameOver && !this.pause)
		{
			if(!this.board.moveDown())
			{
				this.board.update(getGravity());
				this.renderNext(this.board.next.pieces.get(0).type);
			}
			this.renderBoard();
		}
	}

	/**
	 * This function is used in event listeners and simply forwards the request to the appropriate
	 * class that handles the actual game logic.  This function does not handle the result directly.
	 * @return  void
	 */
	protected void hardDrop () {
		//System.out.println ( "HARD DROP!" );
		// Run the appropriate handler from the logic class
		//
		//
		if(!this.board.isGameOver && !this.pause)
		{
			while(this.board.moveDown())
			{
				
			}
			this.board.update(getGravity());
			this.renderNext(this.board.next.pieces.get(1).type);
			this.renderBoard();
		}
	}

	/**
	 * This function is used in event listeners and simply forwards the request to the appropriate
	 * class that handles the actual game logic.  This function does not handle the result directly.
	 * Although it does toggle the value of the button between play and pause.
	 * @return  void
	 */
	protected void pause () {
		// Get the current value
		String current = this.controlPanel.pause.getText ();
		// If the text is pause, change to play
		if ( current.equals ( "Pause" ) ) {
			// Change the text value
			pause = true;
			this.controlPanel.pause.setText ( "Play" );
			// Run the appropriate handler from the logic class
			//
			//
			// super.setFocusable(false);
			// this.controlPanel.setFocusable(false);
		}
		// Otherwise do the opposite
		else {
			pause = false;
			// Change the text value
			this.controlPanel.pause.setText ( "Pause" );
			// Run the appropriate handler from the logic class
			//
			//
			// super.setFocusable(true);
			// this.controlPanel.setFocusable(true);
		}
	}

	/**
	 * This function resets the GUI and also resets elements in the logical class by launching the
	 * appropriate handler that takes care of that.
	 * @return  void
	 */
	protected void restart () {
		// Reset the displays
		this.updateTime ( 0 );
		this.updateLines ( 0 );
		this.updateLevel ( 0 );
		this.updateScore ( 0 );
		// Clear the game grid panel and the next piece panel grid
		this.board = new Board(20, 10);
		this.renderBoard ( );
		this.renderNext ( null );
		// Reset the options
		this.controlPanel.gravity.setText ( "Naive Gravity" );
		this.controlPanel.pause.setText ( "Pause" );
		// Run the appropriate handler from the logic class
		//
		//
	}

	/**
	 * This function is triggered by keyboard and button shortcuts and it toggles the theme music
	 * being played and paused.
	 * @return  void
	 */
	protected void music () {
		// Get the current value
		String current = this.controlPanel.music.getText ();
		// If the text is pause, change to play
		if ( current.equals ( "Music" ) ) {
			// Change the text value
			this.controlPanel.music.setText ( "Mute" );
			// Play the clip
			this.clip.loop ( Clip.LOOP_CONTINUOUSLY );
		}
		// Otherwise do the opposite
		else {
			// Change the text value
			this.controlPanel.music.setText ( "Music" );
			// Pause the clip
			this.clip.stop ();
		}
	}

	/**
	 * This function quits out of the program.  Pretty simple, thats all it does.
	 * @return  void
	 */
	protected void quit () {
		// Simply quit the program
		System.exit ( 0 );
	}

	/**
	 * This function creates a new JFrame window and renders out the about menu in HTML.
	 * @return  void
	 */
	protected void highscores () {
		// Create a new JFrame instance and set the default properties
		Display highscores = new Display ( "Highscores", 300, 350 );
		highscores.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
		highscores.panel.setBackground ( new Color ( 0x1D1F1F ) );
		// Create an HTML JLabel filled with the content

		ArrayList<Player> sortedPlayers = highscoresManager.getSortedPlayers();

		StringBuilder strBuilder = new StringBuilder();

		for(int i = 1; i <= 10; i++)
		{
			if(i <= sortedPlayers.size())
				strBuilder.append(i + ". " + sortedPlayers.get(i - 1).toString());
			else
				strBuilder.append(i + ". ...\n");
		}

		JLabel label = new JLabel (
			"<html>" +
				"<head>" +
					"<style>" +
						"b { color: #F6FAFA }" +
						"body { color: #A8A8A8; font-family: 'Muli' }" +
						"pre { font-family: 'Muli'; margin: 0px; }" +
						"p { display: inline-block; }" +
					"</style>" +
				"</head>" +
				"<body>" +
					"<h2>" + 
						"Top 10 Scores" + 
					"</h2>" +
					"<pre>" +
						strBuilder.toString() +
					"</pre>" +
				"</body>" +
			"</html>"
		);
		// Set the bounds of the label, and add it to the main window panel
		label.setBounds ( 75, 35, 200, 200 );
		highscores.panel.add ( label );
		// Render out the about window
		highscores.render ();
	}

	/**
	 * This function creates a new JFrame window and renders out the about menu in HTML.
	 * @return  void
	 */
	protected void about () {
		// Create a new JFrame instance and set the default properties
		Display about = new Display ( "About", 400, 400 );
		about.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
		about.panel.setBackground ( new Color ( 0x1D1F1F ) );
		// Create an HTML JLabel filled with the content
		JLabel label = new JLabel (
			"<html>" +
				"<head>" +
					"<style>" +
						"b { color: #F6FAFA }" +
						"body { color: #A8A8A8; font-family: 'Muli' }" +
						"pre { font-family: 'Muli'; margin: 0px; }" +
						"p { display: inline-block; }" +
					"</style>" +
				"</head>" +
				"<body>" +
					"<p>" +
						"This <b>Tetris</b> game is part of an assignment that was given out in a" +
						" software design course in the University of Illinois at Chicago. It doe" +
						"s not intend to break any copy right law, instead it was made in a group" +
						" effort for the sake of education." +
					"</p>" +
					"<br>" +
					"<pre><b>@version</b>:\t1.0.0</pre>"+
					"<pre><b>@university</b>:\tUniversity of Illinois at Chicago</pre>"+
					"<pre><b>@course</b>:\tCS342 - Software Design</pre>"+
					"<pre><b>@semester</b>:\tSpring 2016</pre>"+
					"<pre><b>@project</b>:\tProject #05 - Tetris Game</pre>"+
					"<pre><b>@author</b>:\tRafael Grigorian</pre>"+
					"<pre><b>@author</b>:\tByambasuren Gansukh</pre>"+
					"<pre><b>@author</b>:\tPaul Nguyen</pre>"+
					"<pre><b>@license</b>:\tGNU Public License</pre>"+
					"<br>" +
				"</body>" +
			"</html>"
		);
		// Set the bounds of the label, and add it to the main window panel
		label.setBounds ( 35, 35, 330, 330 );
		about.panel.add ( label );
		// Render out the about window
		about.render ();
	}

	/**
	 * This function creates a new JFrame window and renders out the help menu in HTML.
	 * @return  void
	 */
	protected void help () {
		// Create a new JFrame instance and set the default properties
		Display help = new Display ( "Help", 800, 590 );
		help.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
		help.panel.setBackground ( new Color ( 0x1D1F1F ) );
		// Create an HTML JLabel filled with the content
		JLabel label = new JLabel (
			"<html>" +
				"<head>" +
					"<style>" +
						"b { color: #F6FAFA }" +
						"u { color: #F6FAFA; font-weight: bold }" +
						"body { color: #A8A8A8; font-family: 'Muli' }" +
						"pre { font-family: 'Muli'; margin: 0px; }" +
						"p { display: inline-block; }" +
					"</style>" +
				"</head>" +
				"<body>" +
					"<table cellspacing='10' cellpadding='10' >" +
						"<tr>" +
							"<td>" +
								"<u>Graphical User Interface</u>:<br><br>" +
								"<p>" +
									"This Tetris game follows the classical rules that you can se" +
									"arch online.  The left panel contains a variety of displays " +
									"that display information about the game to the user.  The ti" +
									"me section describes the uptime of the current game, and the" +
									" score, level, and lines displays show exactly what you thin" +
									"k they display.  The center panel is where the main Tetris g" +
									"ame grid is displayed.  The panel on the right shows all the" +
									" control buttons along with a panel that shows the next Tetr" +
									"is piece that will show up.  The two arrow icon buttons move" +
									" the piece left and right respectively, and the two rotation" +
									" icon buttons rotate the piece clockwise and counter clockwi" +
									"se respectively.  Below those buttons there are two types of" +
									" drop buttons, the soft drop and the hard drop.  The hard dr" +
									"op drops the piece in place right away.  Below that you can " +
									"see the gravity toggle that toggles between naive gravity an" +
									"d flood fill.  Finally in the lowest row, you can see that t" +
									"he pause button pauses and resumes the game play, whereas th" +
									"e music button toggles the game's audio." +
								"</p>" +
							"</td>" +
							"<td>" +
								"<u>Keyboard Shortcuts</u>:<br><br>" +
								"<pre><b>Left Arrow</b>:\t\tMove piece left</pre>" +
								"<pre><b>Right Arrow</b>:\t\tMove piece right</pre>" +
								"<pre><b>Up Arrow or X</b>:\t\tRotate clockwise</pre>" +
								"<pre><b>Z</b>:\t\tRotate counter clockwise</pre>" +
								"<pre><b>Down Arrow</b>:\t\tSoft drop</pre>" +
								"<pre><b>Space</b>:\t\tHard drop</pre>" +
								"<pre><b>G</b>:\t\tToggle gravity setting</pre>" +
								"<pre><b>P</b>:\t\tToggle pausing game</pre>" +
								"<pre><b>M</b>:\t\tToggle music</pre>" +
								"<pre><b>R</b>:\t\tRestart game</pre>" +
								"<pre><b>Q</b>:\t\tQuit game</pre>" +
								"<pre><b>A</b>:\t\tDisplay about window</pre>" +
								"<pre><b>H</b>:\t\tDisplay this window</pre>" +
								"<br><u>Menu Items</u>:<br><br>" +
								"<pre><b>File</b>-><b>Restart</b>:\t\tRestart the game</pre>" +
								"<pre><b>File</b>-><b>Quit</b>:\t\tQuit the game</pre>" +
								"<pre><b>Information</b>-><b>About</b>:\tDisplay about window</pre>" +
								"<pre><b>Information</b>-><b>Help</b>:\tDisplay this window</pre>" +
							"</td>" +
						"</tr>" +
					"</table>" +
					"<br><br>" +
				"</body>" +
			"</html>"
		);
		// Set the bounds of the label, and add it to the main window panel
		label.setBounds ( 15, 15, 770, 560 );
		help.panel.add ( label );
		// Render out the about window
		help.render ();
	}

	/**
	 * This function simply toggles the button value between naive gravity and flood fill mode. That
	 * is all that needs to happen in this function since we can use the getGravity function to get
	 * the currently set value.
	 * @return  void
	 * @see     GUI::getGravity
	 */
	protected void gravity () {
		// Get the current value
		String current = this.controlPanel.gravity.getText ();
		// If the text is flood fill, change to naive gravity
		if ( current.equals ( "Flood Fill" ) ) {
			this.controlPanel.gravity.setText ( "Naive Gravity" );
		}
		// Otherwise do the opposite
		else {
			this.controlPanel.gravity.setText ( "Flood Fill" );
		}
	}

	/**
	 * This function is here due to the fact that this class implements the action listener class
	 * and it handles each button press and forwards it to the correct corresponding handler
	 * function.
	 * @param   ActionEvent      event              The caught action event
	 * @return  void
	 */
	public void actionPerformed ( ActionEvent event ) {
		// Save the source caller
		Object target = event.getSource ();
		// Switch between the types of buttons that were added
		if ( target == this.controlPanel.moveLeft ) {
			// Call the function that is shared with the key listener
			this.moveLeft ();
		}
		else if ( target == this.controlPanel.moveRight ) {
			// Call the function that is shared with the key listener
			this.moveRight ();
		}
		else if ( target == this.controlPanel.rotateLeft ) {
			// Call the function that is shared with the key listener
			this.rotateLeft ();
		}
		else if ( target == this.controlPanel.rotateRight ) {
			// Call the function that is shared with the key listener
			this.rotateRight ();
		}
		else if ( target == this.controlPanel.softDrop ) {
			// Call the function that is shared with the key listener
			this.softDrop ();
		}
		else if ( target == this.controlPanel.hardDrop ) {
			// Call the function that is shared with the key listener
			this.hardDrop ();
		}
		else if ( target == this.controlPanel.gravity ) {
			this.gravity ();
		}
		else if ( target == this.controlPanel.pause ) {
			// Call the function that is shared with the key listener
			this.pause ();
		}
		else if ( target == this.controlPanel.music ) {
			// Call the function that is shared with the key listener
			this.music ();
		}
	}

	/**
	 * This function is triggered whenever a key press is pressed whenever focus is on the main
	 * JFrame, which should be always since I disabled focus on every other element.  It calls the
	 * appropriate handler.
	 * @param   KeyEvent        event               This is the caught key event
	 * @return  void
	 */
	public void keyPressed ( KeyEvent event ) {
		// Get the key code
		int key = event.getKeyCode ();
		// Switch between the type types
		switch ( key ) {
			case KeyEvent.VK_LEFT:
				// Highlight the appropriate button
				this.controlPanel.moveLeft.setBackground ( new Color ( 0x313333 ) );
				// Bind the key press to the handler
				moveLeft ();
				break;
			case KeyEvent.VK_RIGHT:
				// Highlight the appropriate button
				this.controlPanel.moveRight.setBackground ( new Color ( 0x313333 ) );
				// Bind the key press to the handler
				moveRight ();
				break;
			case KeyEvent.VK_UP:
				// Highlight the appropriate button
				this.controlPanel.rotateRight.setBackground ( new Color ( 0x313333 ) );
				// Bind the key press to the handler
				rotateRight ();
				break;
			case KeyEvent.VK_X:
				// Highlight the appropriate button
				this.controlPanel.rotateRight.setBackground ( new Color ( 0x313333 ) );
				// Bind the key press to the handler
				rotateRight ();
				break;
			case KeyEvent.VK_Z:
				// Highlight the appropriate button
				this.controlPanel.rotateLeft.setBackground ( new Color ( 0x313333 ) );
				// Bind the key press to the handler
				rotateLeft ();
				break;
			case KeyEvent.VK_P:
				// Highlight the appropriate button
				this.controlPanel.pause.setBackground ( new Color ( 0x313333 ) );
				// Bind the key press to the handler
				pause ();
				break;
			case KeyEvent.VK_G:
				// Highlight the appropriate button
				this.controlPanel.gravity.setBackground ( new Color ( 0x313333 ) );
				// Bind the key press to the handler
				gravity ();
				break;
			case KeyEvent.VK_SPACE:
				// Highlight the appropriate button
				this.controlPanel.hardDrop.setBackground ( new Color ( 0x313333 ) );
				// Bind the key press to the handler
				break;
			case KeyEvent.VK_M:
				// Highlight the appropriate button
				this.controlPanel.music.setBackground ( new Color ( 0x313333 ) );
				// Bind the key press to the handler
				music ();
				break;
			case KeyEvent.VK_R:
				// Bind the key press to the handler
				restart ();
				break;
			case KeyEvent.VK_S:
				// Bind the key press to the handler
				highscores ();
				break;
			case KeyEvent.VK_A:
				// Bind the key press to the handler
				about ();
				break;
			case KeyEvent.VK_H:
				// Bind the key press to the handler
				help ();
				break;
			case KeyEvent.VK_Q:
				// Bind the key press to the handler
				quit ();
				break;
			case KeyEvent.VK_DOWN:
				// Highlight the appropriate button
				this.controlPanel.softDrop.setBackground ( new Color ( 0x313333 ) );
				// Bind the key press to the handler
				softDrop ();
				break;
		}
	}

	/**
	 * This function is triggered whenever a key press is pressed whenever focus is on the main
	 * JFrame, which should be always since I disabled focus on every other element.  It calls the
	 * appropriate handler.
	 * @param   KeyEvent        event               This is the caught key event
	 * @return  void
	 */
	public void keyReleased ( KeyEvent event ) {
		// Get the key code
		int key = event.getKeyCode ();
		// Switch between the type types
		switch ( key ) {
			case KeyEvent.VK_LEFT:
				// Highlight the appropriate button
				this.controlPanel.moveLeft.setBackground ( new Color ( 0x242626 ) );
				break;
			case KeyEvent.VK_RIGHT:
				// Highlight the appropriate button
				this.controlPanel.moveRight.setBackground ( new Color ( 0x242626 ) );
				break;
			case KeyEvent.VK_UP:
				// Highlight the appropriate button
				this.controlPanel.rotateRight.setBackground ( new Color ( 0x242626 ) );
				break;
			case KeyEvent.VK_DOWN:
				// Highlight the appropriate button
				this.controlPanel.rotateLeft.setBackground ( new Color ( 0x242626 ) );
				break;
			case KeyEvent.VK_P:
				// Highlight the appropriate button
				this.controlPanel.pause.setBackground ( new Color ( 0x242626 ) );
				break;
			case KeyEvent.VK_G:
				// Highlight the appropriate button
				this.controlPanel.gravity.setBackground ( new Color ( 0x242626 ) );
				break;
			case KeyEvent.VK_D:
				// Highlight the appropriate button
				this.controlPanel.hardDrop.setBackground ( new Color ( 0x242626 ) );
				break;
			case KeyEvent.VK_M:
				// Highlight the appropriate button
				this.controlPanel.music.setBackground ( new Color ( 0x242626 ) );
				break;
			case KeyEvent.VK_SPACE:
				// Highlight the appropriate button
				this.controlPanel.softDrop.setBackground ( new Color ( 0x242626 ) );
				hardDrop ();
				break;
		}
	}

	/**
	 * This function does not do anything and is not used in this program.  It only exists because
	 * it is required when implementing the KeyListener class.
	 * @param   KeyEvent        event               This is the caught key event
	 * @return  void
	 */
	public void keyTyped ( KeyEvent event ) {}

}