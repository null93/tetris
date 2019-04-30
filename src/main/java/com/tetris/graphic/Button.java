package com.tetris.graphic;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * This class is meant to replace the JButton swing member.  It improves the functionality of
 * the original class by adding mouse press and mouse release actions.  Also it applies my default
 * styling to the object.
 * @version     1.0.0
 * @university  University of Illinois at Chicago
 * @course      CS342 - Software Design
 * @category    Project #04 - Ninja: Chat Application
 * @package     Graphic
 * @author      Rafael Grigorian
 * @author      Byambasuren Gansukh
 * @license     GNU Public License <http://www.gnu.org/licenses/gpl-3.0.txt>
 */
@SuppressWarnings ( "serial" )
public class Button extends JButton implements MouseListener {

	/**
	 * This internal data member saves the background color.
	 * @var     Color           background          The background color of the button
	 */
	private Color background;

	/**
	 * This internal data member saves the foreground color.
	 * @var     Color           foreground          The foreground color of the button
	 */
	private Color foreground;

	/**
	 * This internal data member saves the background color when the button is pressed.
	 * @var     Color           highlightBackground             The background color of the button when pressed
	 */
	private Color highlightBackground;

	/**
	 * This internal data member saves the foreground color when the button is pressed.
	 * @var     Color           highlightForeground             The foreground color of the button when pressed
	 */
	private Color highlightForeground;

	/**
	 * This constructor sets the button value using the super constructor, and it sets the default
	 * styling that is preferred by me.  It also attached a mouse listener to implement the
	 * change of color on press.
	 * @param   String          value               The placeholder value
	 * @param   int             width               The width of the button
	 * @param   int             height              The height of the button
	 */
	public Button ( String value, int width, int height ) {
		// Call the super constructor to set the text value
		super ( value );
		// Set the default colors
		this.background = new Color ( 0xD94C36 );
		this.foreground = Color.WHITE;
		this.highlightForeground = this.foreground;
		// Set my preferred default styling
		this.setPreferredSize ( new Dimension ( width, height ) );
		this.setFocusPainted ( false );
		this.setForeground ( this.foreground );
		this.setBackground ( this.background );
		this.setCursor ( new Cursor ( Cursor.HAND_CURSOR ) );
		this.setBorderPainted ( false );
		this.setContentAreaFilled ( false );
		this.setOpaque ( true );
		// Add itself to the self implemented mouse listener
		this.addMouseListener ( this );
		// Calculate a shade of the background color
		Color current = this.getBackground ();
		double scale = 0.8;
		int r = Math.min ( 255, ( int ) ( current.getRed () * scale ) );
		int g = Math.min ( 255, ( int ) ( current.getGreen () * scale ) );
		int b = Math.min ( 255, ( int ) ( current.getBlue () * scale ) );
		this.highlightBackground = new Color ( r, g, b );
	}

	/**
	 * This function sets the position of the button using the passed x and y coordinates, and it
	 * sets it in an absolute manner, not caring about the parent.
	 * @param   int             x                   The x coordinate
	 * @param   int             y                   The y coordinate
	 * @return  void
	 */
	public void setPosition ( int x, int y ) {
		// Get the button's dimensions
		Dimension size = this.getPreferredSize ();
		// Set the position based on parameters
		this.setBounds ( x, y, size.width, size.height );
	}

	/**
	 * This function simply sets the highlight colors for the button, so when the button is clicked,
	 * the color would correspond with the passed color.
	 * @param   Color           color               The target color to apply to foreground
	 * @param   Color           color               The target color to apply to apply to background
	 * @return  void
	 */
	public void setHighlight ( Color foreground, Color background ) {
		// Save these values internally
		this.highlightForeground = foreground;
		this.highlightBackground = background;
	}

	/**
	 * This function overrides the super function, and it runs the original function, but also saves
	 * the value internally
	 * @param   Color           color               The target color in question
	 * @return  void
	 * @override
	 */
	@Override
	public void setBackground ( Color color ){
		// Run the super's function and save the color
		super.setBackground ( color );
		// Save the value internally
		this.background = color;
	}

	/**
	 * This function overrides the super function, and it runs the original function, but also saves
	 * the value internally
	 * @param   Color           color               The target color in question
	 * @return  void
	 * @override
	 */
	@Override
	public void setForeground ( Color color ){
		super.setForeground ( color );
		this.foreground = color;
	}

	/**
	 * This function simply sets the original colors for the button, so when the button is clicked,
	 * the color would correspond with the passed color.
	 * @param   Color           color               The target color to apply to foreground
	 * @param   Color           color               The target color to apply to apply to background
	 * @return  void
	 */
	public void setColor ( Color foreground, Color background ) {
		// Save it using the super's function
		super.setForeground ( foreground );
		super.setBackground ( background );
		// Save these values internally
		this.foreground = foreground;
		this.background = background;
	}

	/**
	 * This event handler runs when a mouse press occurs on this button and it makes the background
	 * color of the button be slightly darker.
	 * @param   MouseEvent      event               The mouse event that occurred
	 * @return  void
	 */
	public void mousePressed ( MouseEvent event ) {
		super.setBackground ( this.highlightBackground );
		super.setForeground ( this.highlightForeground );
	}

	/**
	 * This event handler runs when a mouse release occurs on this button and it makes the
	 * background color of the button return to it's original color.
	 * @param   MouseEvent      event               The mouse event that occurred
	 * @return  void
	 */
	public void mouseReleased ( MouseEvent event ) {
		super.setBackground ( this.background );
		super.setForeground ( this.foreground );
	}

	/**
	 * This is a required function that is required to implement the mouse listener, but has no
	 * purpose in this application.
	 * @param   MouseEvent      event               The mouse event that occurred
	 * @return  void
	 */
	public void mouseClicked ( MouseEvent event ) {}

	/**
	 * This is a required function that is required to implement the mouse listener, but has no
	 * purpose in this application.
	 * @param   MouseEvent      event               The mouse event that occurred
	 * @return  void
	 */
	public void mouseEntered ( MouseEvent event ) {}

	/**
	 * This is a required function that is required to implement the mouse listener, but has no
	 * purpose in this application.
	 * @param   MouseEvent      event               The mouse event that occurred
	 * @return  void
	 */
	public void mouseExited ( MouseEvent event ) {}

}
