package Graphic;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;

/**
 * This class is meant to replace the JPasswordField swing member.  It improves the functionality of
 * the original class by adding placeholder support.  Also it applies my default styling to the
 * object.
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
public class PasswordField extends JPasswordField implements FocusListener {

	/**
	 * This data member holder the placeholder value, and it's used in the Focus Listener's handler.
	 * @var     String          placeholder         The placeholder value
	 */
	private String placeholder;

	/**
	 * This data member saves the default font color, and its used later in the Focus Listener's
	 * handler.
	 * @var     Color           fontColor           The default font color
	 */
	private Color fontColor;

	/**
	 * This data member holder the desired placeholder color, and its used later in the Focus
	 * Listener's handler.
	 * @var     Color           placeholderColor    The placeholder color
	 */
	private Color placeholderColor;

	/**
	 * This data member is a boolean that specifies whether or not the password field's value is
	 * empty.
	 * @var     boolean         empty               Is the password value technically empty?
	 */
	private boolean empty;

	/**
	 * This constructor saves some of the passed values, sets the default values and styling
	 * preferences, and finally it adds itself to the focus listener.  This focus listener allows us
	 * to implement a placeholder.
	 * @param   String          placeholder         This is the placeholder value
	 * @param   int             width               The password field's width
	 * @param   int             height              The password field's height
	 * @param   int             padding             The password field's padding for all four sides
	 */
	public PasswordField ( String placeholder, int width, int height, int padding ) {
		// Run the JPasswordField constructor and set the value to be the placeholder
		super ( placeholder );
		// Save the passed variables internally
		this.placeholder = placeholder;
		this.fontColor = new Color ( 0x6D6D6D );
		this.placeholderColor = new Color ( 0xBABABA );
		// Set the dimensions
		this.setPreferredSize ( new Dimension ( width, height ) );
		// Set the default foreground and background color
		this.setForeground ( this.placeholderColor );
		this.setBackground ( Color.WHITE );
		// Remove that ugly blue outline
		this.setBorder ( new LineBorder ( Color.BLACK, 0 ) );
		// Create padding based on passed parameter
		this.setBorder ( BorderFactory.createCompoundBorder (
			this.getBorder (),
			BorderFactory.createMatteBorder (
				0, padding, 0, padding,
				this.getBackground ()
			)
		));
		// Set empty boolean to be true initially
		this.empty = true;
		// Initially, don't mask password
		this.setEchoChar ( ( char ) 0 );
		// Attach a focus listener
		this.addFocusListener ( this );
	}

	/**
	 * This function sets the position of the text field using the passed x and y coordinates, and
	 * it sets it in an absolute manner, not caring about the parent.
	 * @param   int             x                   The x coordinate
	 * @param   int             y                   The y coordinate
	 * @return  void
	 */
	public void setPosition ( int x, int y ) {
		// Get the text fields dimensions
		Dimension size = this.getPreferredSize ();
		// Set the position based on parameters
		this.setBounds ( x, y, size.width, size.height );
	}

	/**
	 * This function sets the placeholder color and it is saved internally, so when we encounter the
	 * focus listener handler, we can use this placeholder dynamically.
	 * @param   Color           color               The color to set the placeholder to
	 */
	public void setPlaceholderColor ( Color color ) {
		// Set the placeholder color
		this.placeholderColor = color;
	}

	/**
	 * This function overrides the inherited JPasswordField and returns the actual value, ignoring the
	 * placeholder if it is set.
	 * @return  String                              The value of the text field
	 * @override
	 */
	public String getPasswordString () {
		// Check to see if the value is technically empty
		if ( this.empty ) {
			// Return an empty string
			return "";
		}
		// Return the actual value
		return String.valueOf ( super.getPassword () );
	}

	/**
	 * This function resets all the tracking variables, and also resets the text to be the
	 * placeholder and resets the font color to the placeholder color.
	 * @return  void
	 */
	public void empty () {
		// Reset flag to be true
		this.empty = true;
		// Reset text, mask char, and font color
		this.setText ( this.placeholder );
		this.setEchoChar ( ( char ) 0 );
		this.setForeground ( this.placeholderColor );
	}

	/**
	 * This is the focus listener and it runs whenever focus is gained for this text field.
	 * @param   FocusEvent      event               Unused focus event
	 * @return  void
	 * @override
	 */
	@Override
	public void focusGained ( FocusEvent event ) {
		// Check to see what if the value is technically empty
		if ( this.getPasswordString ().isEmpty () ) {
			// Set the mask char to hide value
			this.setEchoChar ( '*' );
			// Set the value to nothing and change font
			this.setText ( "" );
			this.setForeground ( fontColor );
			// Change the empty boolean
			this.empty = false;
		}
	}

	/**
	 * This is the focus listener and it runs whenever focus is lost for this text field.
	 * @param   FocusEvent      event               Unused focus event
	 * @return  void
	 * @override
	 */
	@Override
	public void focusLost ( FocusEvent event ) {
		// Check to see what if the value is technically empty
		if ( this.getPasswordString ().isEmpty () ) {
			// Unmask to show placeholder
			this.setEchoChar ( ( char ) 0 );
			// Set the value to be the placeholder and change the font
			super.setText ( this.placeholder );
			this.setForeground ( this.placeholderColor );
			// Change the empty boolean
			this.empty = true;
		}
	}

}