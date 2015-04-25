package org.bolodev.osxripper.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.bolodev.osxripper.core.OSXRipperController;
import org.bolodev.osxripper.mvc.Controller;

/**
 * Class to handle Clear Console menu item
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class ClearConsoleAction extends AbstractAction {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 217130712308951053L;
	private OSXRipperController controller;
	
	/**
	 * Constructor
	 * @param aController application controller
	 */
	public ClearConsoleAction(Controller aController){
		super("Clear Console");
		controller = (OSXRipperController) aController;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.clearConsole();
	}

}
