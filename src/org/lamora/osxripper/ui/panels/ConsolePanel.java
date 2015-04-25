package org.bolodev.osxripper.ui.panels;

import java.awt.BorderLayout;

//import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

/**
 * Panel displaying read only text output
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class ConsolePanel extends JPanel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -4349617320029956517L;
	private JScrollPane pnScroll;
	private JTextArea arText;
	
	/**
	 * Constructor
	 */
	public ConsolePanel(){
		initialise();
	}
	
	/**
	 * Initialise the components
	 */
	private void initialise(){
		setLayout(new BorderLayout());
		setBorder(new EtchedBorder());
		
		arText = new JTextArea();
		arText.setEditable(false);
		pnScroll = new JScrollPane(arText);
		
		add(pnScroll, BorderLayout.CENTER);
	}
	
	/**
	 * Append a line of text to the text area, automatically scrolls as text is appended
	 * @param aMessage the string to display
	 */
	public void appendToConsole(String aMessage){
		arText.append(aMessage + '\n');
		arText.setCaretPosition(arText.getDocument().getLength());
	}
	
	/**
	 * Clear the text from the text console
	 */
	public void clearConsole(){
		arText.setText("");
	}
	
}
