package org.bolodev.osxripper.ui.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.bolodev.osxripper.ui.panels.ConsolePanel;

/**
 * Basic JFrame to display names and descriptions from loaded plugins
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class PluginDescriptionFrame extends JFrame {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -8295518809082535679L;
	private JScrollPane pnScroll;
	private ConsolePanel pnlConsole;
	
	/**
	 * Constructor
	 */
	public PluginDescriptionFrame(){
		initialise();
	}
	
	/**
	 * Initialise the components
	 */
	private void initialise(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Plugin Descriptions");
		setSize(600, 800);
		setLayout(new BorderLayout());
		
		pnlConsole = new ConsolePanel();
		pnScroll = new JScrollPane(pnlConsole);
		
		getContentPane().add(pnScroll, BorderLayout.CENTER);
	}
	
	/**
	 * Set the collated description text in the console panel text area
	 * @param aDescriptionListing the collated description text
	 */
	public void setPluginDescriptionText(String aDescriptionListing){
		pnlConsole.appendToConsole(aDescriptionListing);
	}

}
