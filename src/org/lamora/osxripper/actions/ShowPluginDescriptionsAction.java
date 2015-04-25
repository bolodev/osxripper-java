package org.bolodev.osxripper.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.bolodev.osxripper.core.OSXRipperController;
import org.bolodev.osxripper.core.OSXRipperModel;
import org.bolodev.osxripper.mvc.Controller;

/**
 * Class to handle Show Plugin Descriptions menu item
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class ShowPluginDescriptionsAction extends AbstractAction {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -1426740214192731570L;
	private OSXRipperController controller;
	
	/**
	 * Constructor
	 * @param aController raw controller, gets cast to an OSXRipperController object
	 */
	public ShowPluginDescriptionsAction(Controller aController){
		super("Show Plugin Descriptions");
		controller = (OSXRipperController) aController;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		((OSXRipperModel)controller.getModel()).getPluginDescriptionListing();
	}

}
