package org.bolodev.osxripper.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import org.bolodev.osxripper.core.OSXRipperController;
import org.bolodev.osxripper.core.OSXRipperModel;
import org.bolodev.osxripper.mvc.Controller;
import org.bolodev.osxripper.ui.frames.OSXRipperFrame;

/**
 * Class to handle Select Output Directory button
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class SelectOutputDirectoryActions extends AbstractAction {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 6604050534287788415L;
	private OSXRipperController controller;
	
	/**
	 * Constructor
	 * @param aController raw controller, gets cast to an OSXRipperController object
	 */
	public SelectOutputDirectoryActions(Controller aController){
		super("Output");
		controller = (OSXRipperController) aController;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setMultiSelectionEnabled(false);
		int returnVal = jfc.showDialog(((OSXRipperFrame)controller.getView()), "Select Output Directory");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			 try {
				 ((OSXRipperModel)controller.getModel()).setOutputDirectory(jfc.getSelectedFile());
			} catch (Exception e1) {
				((OSXRipperFrame)controller.getView()).errorMessage("Exception:\n" + e1.getMessage() + "\n\nCause:\n" + e1.getCause());
			}
		}
	}

}
