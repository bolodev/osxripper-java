package org.bolodev.osxripper.actions;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.bolodev.osxripper.core.OSXRipperController;
import org.bolodev.osxripper.mvc.Controller;

/**
 * Class to handle window closing event
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @since 0.1
 */
public class FrameViewAdapter extends WindowAdapter {

	private OSXRipperController controller;
		
	/**
	 * Constructor
	 * @param aController Controller to pass to actionPerformed
	 */
	public FrameViewAdapter(Controller aController){
		controller = (OSXRipperController)aController;
	}
	
    /*
     * (non-Javadoc)
     * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
     */
    public void windowClosing(WindowEvent we){
        controller.exitApplication();
    }
  
}