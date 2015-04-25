package org.bolodev.osxripper.ui.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.SpringLayout;

import org.bolodev.osxripper.actions.RunAction;
import org.bolodev.osxripper.actions.SelectInputDirectoryAction;
import org.bolodev.osxripper.actions.SelectOutputDirectoryActions;
import org.bolodev.osxripper.mvc.Controller;
import org.bolodev.osxripper.ui.SpringUtilities;

/**
 * Panel displaying directory selection options
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class SetupPanel extends JPanel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 6817162515358997740L;
	private JButton btnInputDir;
	private JButton btnOutputDir;
	private JButton btnRun;
	private JTextField fldInputDir;
	private JTextField fldOutputDir;
	
	/**
	 * Constructor
	 * @param aController passed to the buttons' action constructors
	 */
	public SetupPanel(Controller aController){
		initialise(aController);
	}
	
	/**
	 * Initialise the components
	 * @param aController passed to the buttons' action constructors
	 */
	private void initialise(Controller aController){
		setBorder(new EtchedBorder());
		setLayout(new SpringLayout());
		
		btnInputDir = new JButton(new SelectInputDirectoryAction(aController));
		btnOutputDir = new JButton(new SelectOutputDirectoryActions(aController));
		fldInputDir = new JTextField();
		fldInputDir.setEditable(false);
		fldOutputDir = new JTextField();
		fldOutputDir.setEditable(false);
		btnRun = new JButton(new RunAction(aController));
		
		add(btnInputDir);
		add(fldInputDir);
		add(btnOutputDir);
		add(fldOutputDir);
		add(new JLabel()); //empty component otherwise get a NullPointerException at layout
		add(btnRun);
		
		SpringUtilities.makeCompactGrid(
				this, //container
				3, 2, //rows, cols
		        1, 1, //initX, initY
		        1, 1); //xPad, yPad
	}
	
	/**
	 * Sets the path of the selected directory
	 * @param aPath the path of the selected directory
	 */
	public void setInputText(String aPath){
		fldInputDir.setText(aPath);
	}
	
	/**
	 * Sets the path of the selected directory
	 * @param aPath the path of the selected directory
	 */
	public void setOutputText(String aPath){
		fldOutputDir.setText(aPath);
	}

}
