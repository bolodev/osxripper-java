package org.bolodev.osxripper.ui.menus;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.bolodev.osxripper.actions.AboutAction;
import org.bolodev.osxripper.actions.ClearConsoleAction;
import org.bolodev.osxripper.actions.ExitAction;
import org.bolodev.osxripper.actions.ShowPluginDescriptionsAction;
import org.bolodev.osxripper.mvc.Controller;

/**
 * Application menu bar
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class OSXRipperMenuBar extends JMenuBar {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -3356466131613648577L;
	private JMenu mnFile;
	private JMenu mnHelp;
	
	/**
	 * Constructor, passes controller for menu item action constructors
	 * @param aController controller for menu item action constructors
	 */
	public OSXRipperMenuBar(Controller aController){
		initialise(aController);
	}
	
	/**
	 * Initialise the components
	 * @param aController controller for menu item action constructors
	 */
	private void initialise(Controller aController){
		mnFile = new JMenu("File");
		mnHelp = new JMenu("Help");

		mnFile.add(new ClearConsoleAction(aController));
		mnFile.addSeparator();
		mnFile.add(new ExitAction(aController));
		mnHelp.add(new ShowPluginDescriptionsAction(aController));
		mnHelp.add(new AboutAction(aController));
		
		add(mnFile);
		add(mnHelp);
	}

}
