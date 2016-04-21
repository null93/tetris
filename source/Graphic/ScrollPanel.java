package Graphic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is meant simplify the process of making a scrollable JPanel.  It also styles the
 * frames based on my default design.  This class extends the original JPanel class.
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
public class ScrollPanel extends JPanel {

	/**
	 * This data member holds the width of the panel, this value is set in the constructor.
	 * @var     int             width               The width of the panel
	 */
	private int width;

	/**
	 * This data member holds the height of the panel, this value is set in the constructor.
	 * @var     int             height              The height of the panel
	 */
	private int height;

	/**
	 * This data member saves the internal panel that contains the actual content
	 * @var     JPanel          content             Panel holding all innards
	 */
	private JPanel content;

	/**
	 * This data member is a reference to the scroll panel.  This is saves primarily for the update
	 * function.
	 * @var     JScrollPane     scrollPanel         The scroll panel
	 */
	private JScrollPane scrollPanel;

	/**
	 * This data member holds a reference to the vertical scroll bar.  It is used primarily for the
	 * update function.
	 * @var     JScrollBar      verticalScrollBar   The vertical scroll bar for the scroll panel
	 */
	private JScrollBar verticalScrollBar;

	/**
	 * This data member holds a reference to the vertical scroll bar.  It is used primarily for the
	 * update function.
	 * @var     JScrollBar      horizontalScrollBar The horizontal scroll bar for the scroll panel
	 */
	private JScrollBar horizontalScrollBar;

	/**
	 * This constructor sets everything up and sets up the default options that are preferred by me.
	 * It uses the BoxLayout in order to append content to the original content panel.
	 * @param   int             width               The width of the panel
	 * @param   int             height              The height of the panel
	 * @param   int             direction           An enum value describing append direction
	 */
	public ScrollPanel ( int width, int height, int direction ) {
		// Use super to create a content panel
		super ( null );
		super.setPreferredSize ( new Dimension ( width, height ) );
		// Save the dimensions internally
		this.width = width;
		this.height = height;
		// Create a new panel to push content, and set properties
		this.content = new JPanel();
		this.content.setLayout ( new BoxLayout ( this.content, direction ) );
		this.content.setBorder ( BorderFactory.createEmptyBorder ( 0, 0, 0, 0 ) );
		this.content.setBackground ( Color.WHITE );
		// Create a new scroll panel
		this.scrollPanel = new JScrollPane ( this.content );
		// If the direction of scrolling is on the y axis, then set it appropriately
		if ( direction == BoxLayout.Y_AXIS ) {
			// Set the scroll bar settings
			this.scrollPanel.setHorizontalScrollBarPolicy ( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
			this.scrollPanel.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		}
		// If the direction of scrolling is on the x axis, then set it appropriately
		else {
			// Set the scroll bar settings
			this.scrollPanel.setHorizontalScrollBarPolicy ( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
			this.scrollPanel.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_NEVER );
		}
		// Set default properties for the scroll panel
		this.scrollPanel.setBounds ( 0, 0, width, height );
		this.scrollPanel.setBorder ( new LineBorder ( Color.BLACK, 0 ) );
		// Save the vertical and horizontal scroll bars and set default settings
		this.verticalScrollBar = scrollPanel.getVerticalScrollBar ();
		this.verticalScrollBar.setValue ( verticalScrollBar.getMaximum () );
		this.verticalScrollBar.setUnitIncrement ( 10 );
		this.verticalScrollBar.setPreferredSize ( new Dimension ( 0, 0 ) );
		this.horizontalScrollBar = scrollPanel.getHorizontalScrollBar ();
		this.horizontalScrollBar.setValue ( this.horizontalScrollBar.getMaximum () );
		this.horizontalScrollBar.setUnitIncrement ( 10 );
		this.horizontalScrollBar.setPreferredSize ( new Dimension ( 0, 0 ) );
		// Append the content to the scroll panel
		super.add ( scrollPanel );
		super.setBounds ( 0, 0, width, height );
	}

	/**
	 * This function is a getter function that returns the handle to the content panel.  This handle
	 * is stored internally and is private.
	 * @return  JPanel                              Returns the handle to the content panel
	 */
	public JPanel getContentPanel () {
		// Return the handle to the content panel
		return this.content;
	}

	/**
	 * This function sets the position of the panel based on the passed parameters and it uses the
	 * dimensions based on the ones passed to the constructor
	 * @param   int             x                   The x coordinate
	 * @param   int             y                   The y coordinate
	 * @return  void
	 */
	public void setPosition ( int x, int y ) {
		// Set the bounds of the panel holding the scroll panel
		this.setBounds ( x, y, this.width, this.height );
	}

	/**
	 * This function revalidates, and repaints the scroll panel, in order to update the content that
	 * was added or deleted from it.
	 * @return  void
	 */
	public void update () {
		// Re-validate and repaint scroll panel
		this.scrollPanel.validate();
		this.scrollPanel.revalidate();
		this.scrollPanel.repaint();
	}

	/**
	 * This function updates the scroll panel and scrolls to the bottom of the scroll panel.
	 * @return  void
	 */
	public void scrollToBottom () {
		// Update the scroll panel
		this.update ();
		// Scroll to the bottom of the panel, based on the content panel height
		this.verticalScrollBar.setValue ( this.content.getHeight () );
	}

	/**
	 * This function updates the scroll panel and scrolls to the top of the scroll panel.
	 * @return  void
	 */
	public void scrollToTop () {
		// Update the scroll panel
		this.update ();
		// Scroll to the top of the panel, based on the content panel height
		this.verticalScrollBar.setValue ( 0 );
	}

	/**
	 * This function updates the scroll panel and scrolls to the left of the scroll panel.
	 * @return  void
	 */
	public void scrollToLeft () {
		// Update the scroll panel
		this.update ();
		// Scroll to the left of the panel, based on the content panel width
		this.horizontalScrollBar.setValue ( 0 );
	}

	/**
	 * This function updates the scroll panel and scrolls to the right of the scroll panel.
	 * @return  void
	 */
	public void scrollToRight () {
		// Update the scroll panel
		this.update ();
		// Scroll to the right of the panel, based on the content panel width
		this.horizontalScrollBar.setValue ( this.content.getWidth () );
	}

	/**
	 * This function adds a Component to the content panel and it acts like a wrapper to directly
	 * access this feature instead of getting the content panel first.
	 * @param   Component       object              The label to append to the content panel
	 * @return  void
	 */
	public void append ( Component object ) {
		// Append to the content panel
		this.content.add ( object );
	}

}