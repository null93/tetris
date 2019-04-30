package com.tetris.graphic;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is meant to initialize a JFrame instance and set the default properties to this window
 * frame.  It also has a function to set the visibility to true and render the window dead center
 * relative to the screen.
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
public class Display extends JFrame {

	/**
	 * This data member saves the main panel reference that belongs to the JFrame.  It is used to
	 * add other components to said panel and build off from that.
	 */
	public Container panel;

	/**
	 * This constructor takes in a window title, a width and a height and creates a new JFrame
	 * instance, and sets the default properties that fit my styling choices.
	 * @param   String          title               The title of the main window frame
	 * @param   int             width               The width of the window
	 * @param   int             height              The height of the window
	 */
	public Display ( String title, int width, int height ) {
		// Run the super constructor and set properties
		super ( title );
		super.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		super.setResizable ( false );
		super.setSize ( width, height );
		// Save the main container reference and set layout type
		this.panel = super.getContentPane ();
		this.panel.setLayout ( null );
	}

	/**
	 * This function simply renders out the JFrame, makes it visible, and sets the location to be
	 * dead center on your screen on initialization.
	 * @return  void
	 */
	public void render () {
		// Get current screen size
		Dimension dimension = Toolkit.getDefaultToolkit ().getScreenSize ();
		// Center frame in the middle of the screen
		super.setLocation (
			dimension.width / 2 - getSize ().width / 2,
			dimension.height / 2 - getSize ().height / 2
		);
		// Set the frame to be visible
		super.setVisible ( true );
	}

}