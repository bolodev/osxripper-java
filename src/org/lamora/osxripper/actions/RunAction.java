package org.bolodev.osxripper.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.bolodev.osxripper.core.OSXRipperController;
import org.bolodev.osxripper.core.OSXRipperModel;
import org.bolodev.osxripper.mvc.Controller;

/**
 * Class to handle Run button
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class RunAction extends AbstractAction {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -1257995630582300131L;
	private OSXRipperController controller;
	
	/**
	 * Constructor
	 * @param aController application controller
	 */
	public RunAction(Controller aController){
		super("Run");
		controller = (OSXRipperController) aController;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		((OSXRipperModel)controller.getModel()).runPlugins();
	}

}
