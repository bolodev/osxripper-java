package org.bolodev.osxripper.ui.panels;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;

/**
 * Status bar
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class StatusPanel extends JPanel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -3811807483617367767L;
	
	private JProgressBar brProgress;
	private JLabel lblPlugins;
	
	/**
	 * Constructor
	 */
	public StatusPanel(){
		initialise();
	}
	
	/**
	 * Initialise the components
	 */
	private void initialise(){
		setBorder(new EtchedBorder());
		setLayout(new BorderLayout());
		lblPlugins = new JLabel(" Plugins:");
		brProgress = new JProgressBar();
		
		add(lblPlugins, BorderLayout.WEST);
		add(brProgress, BorderLayout.EAST);
	}
	
	/**
	 * @param trueForOn switch the indeterminate mode on or off
	 */
	public void setProgress(boolean trueForOn){
		brProgress.setIndeterminate(trueForOn);
	}
	
	/**
	 * @param aCount the number of loaded plugins
	 */
	public void setPluginCount(int aCount){
		lblPlugins.setText(" Loaded Plugins: " + aCount);
	}

}
