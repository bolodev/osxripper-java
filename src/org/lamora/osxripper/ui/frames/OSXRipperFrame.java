package org.bolodev.osxripper.ui.frames;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;

import org.bolodev.osxripper.actions.FrameViewAdapter;
import org.bolodev.osxripper.core.OSXRipperModel;
import org.bolodev.osxripper.io.Logger;
import org.bolodev.osxripper.mvc.Controller;
import org.bolodev.osxripper.mvc.JFrameView;
import org.bolodev.osxripper.mvc.Model;
import org.bolodev.osxripper.mvc.ModelEvent;
import org.bolodev.osxripper.ui.menus.OSXRipperMenuBar;
import org.bolodev.osxripper.ui.panels.ConsolePanel;
import org.bolodev.osxripper.ui.panels.PluginPanel;
import org.bolodev.osxripper.ui.panels.SetupPanel;
import org.bolodev.osxripper.ui.panels.StatusPanel;

/**
 * Main application view frame
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class OSXRipperFrame extends JFrameView {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -8352295080597298323L;
	private OSXRipperMenuBar brMenu;
	private SetupPanel pnlSetup;
	private JSplitPane pnSplit;
	private ConsolePanel pnlConsole;
	private StatusPanel pnlStatus;
	
	private JTabbedPane pnTabbed;

	/**
	 * Constructor
	 * @param model the application Model
	 * @param controller the application Controller
	 */
	public OSXRipperFrame(Model model, Controller controller) {
		super(model, controller);
		initialise();
	}
	
	/**
	 * Initialise the UI
	 */
	private void initialise(){
		super.initialise("OSX Ripper", 800, 800);
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			Logger.appendLog("osxripper.log", "OSXRipperFrame: Exception - " + e.getLocalizedMessage());
		}
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout());
		addWindowListener(new FrameViewAdapter(getController()));
		
		brMenu = new OSXRipperMenuBar(getController());
		pnlSetup = new SetupPanel(getController());
		
		pnTabbed = new JTabbedPane();
		pnTabbed.addTab("OS", new PluginPanel(getController(), ((OSXRipperModel)getModel()).getOSPluginNames()));
		pnTabbed.addTab("Hardware", new PluginPanel(getController(), ((OSXRipperModel)getModel()).getHardwarePluginNames()));
		pnTabbed.addTab("Software", new PluginPanel(getController(), ((OSXRipperModel)getModel()).getSoftwarePluginNames()));
		pnTabbed.addTab("User", new PluginPanel(getController(), ((OSXRipperModel)getModel()).getUserPluginNames()));
		pnTabbed.addTab("iOS", new PluginPanel(getController(), ((OSXRipperModel)getModel()).getIOSPluginNames()));
		pnTabbed.setBorder(new EtchedBorder());
		
		pnlConsole = new ConsolePanel();
		pnSplit = new JSplitPane();
		pnSplit.setDividerSize(5);
		pnSplit.setDividerLocation(400);
		pnSplit.setLeftComponent(pnTabbed);
		pnSplit.setRightComponent(pnlConsole);
		pnlStatus = new StatusPanel();
		
		setJMenuBar(brMenu);
		getContentPane().add(pnlSetup, BorderLayout.NORTH);
		getContentPane().add(pnSplit, BorderLayout.CENTER);
		getContentPane().add(pnlStatus, BorderLayout.SOUTH);
	}
	
    /* (non-Javadoc)
	 * @see org.bolodev.osxripper.mvc.ModelListener#modelChanged(org.bolodev.osxripper.mvc.ModelEvent)
	 */
	@Override
	public void modelChanged(ModelEvent event) {
		if(event.getID() == 1000){
			pnlSetup.setInputText((String) event.getObject());
		}
		if(event.getID() == 1001){
			pnlSetup.setOutputText((String) event.getObject());
		}
		if(event.getID() == 2000){
			pnlConsole.appendToConsole((String) event.getObject());
		}
		if(event.getID() == 2003){
			pnlStatus.setProgress(event.getTest());
		}
		if(event.getID() == 3000){
			PluginDescriptionFrame pdf = new PluginDescriptionFrame();
			pdf.setPluginDescriptionText((String) event.getObject());
			pdf.setVisible(true);
		}
		if(event.getID() == 4000){
			pnlStatus.setPluginCount(event.getInt());
		}
		if(event.getID() == 100){
			clearConsole();
		}
	}
	
	/**
	 * Clear the text from the text console
	 */
	public void clearConsole(){
		pnlConsole.clearConsole();
	}

}
