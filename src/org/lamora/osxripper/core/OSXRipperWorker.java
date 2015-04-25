package org.bolodev.osxripper.core;

import java.io.File;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import org.bolodev.osxripper.io.Logger;
import org.bolodev.osxripper.mvc.ModelEvent;
import org.bolodev.osxripper.plugin.IPlugin;
import org.bolodev.osxripper.plugin.IPluginII;

/**
 * Extended SwingWorker to run plugins in the background
 * 
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @since 0.1
 */
public class OSXRipperWorker extends SwingWorker<Void, Void> {

	private OSXRipperModel model;
	private ArrayList<Exception> exceptionsList = new ArrayList<Exception>();

	/**
	 * Constructor
	 * 
	 * @param aModel
	 *            the application model
	 */
	public OSXRipperWorker(OSXRipperModel aModel) {
		model = aModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() throws Exception {
		ModelEvent me = new ModelEvent(this, 100, "Clear console");
		model.notifyChanged(me);
		me = new ModelEvent(this, 2003, "Threads Started", true);
		model.notifyChanged(me);
		
		ArrayList<IPlugin> osPlugins = model.getActiveOSPlugins();
		ArrayList<IPlugin> softwarePlugins = model.getActiveSoftwarePlugins();
		ArrayList<IPlugin> hardwarePlugins = model.getActiveHardwarePlugins();
		ArrayList<IPlugin> userPlugins = model.getActiveUserPlugins();
		ArrayList<IPlugin> iosPlugins = model.getActiveIOSPlugins();

		if (osPlugins.size() > 0) {
			runPluginList(osPlugins);
		}
		if (softwarePlugins.size() > 0) {
			runPluginList(softwarePlugins);
		}
		if (hardwarePlugins.size() > 0) {
			runPluginList(hardwarePlugins);
		}
		if (userPlugins.size() > 0) {
			runPluginList(userPlugins);
		}
		if (iosPlugins.size() > 0) {
			runPluginList(iosPlugins);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#done()
	 */
	protected void done() {
		ModelEvent me = null;
		me = new ModelEvent(this, 2000, "Finito", "All active Plugins finished");
		model.notifyChanged(me);
		me = new ModelEvent(this, 2003, "Progress bar", false);
		model.notifyChanged(me);

		if (exceptionsList.size() > 0) {
			for (int i = 0; i < exceptionsList.size(); i++) {
				Logger.appendLog("osxripper.log", "OSXRipperWorker: Exception - " + exceptionsList.get(i).getLocalizedMessage());
				me = new ModelEvent(this, 2000, "Exception: " + exceptionsList.get(i).getMessage());
				model.notifyChanged(me);
			}
		}
	}

	/**
	 * Run the active plugins passed from the Model
	 * 
	 * @param aPluginList
	 *            the active plugins passed from the Model
	 */
	private void runPluginList(ArrayList<IPlugin> aPluginList) {
		try {
			int count = aPluginList.size();
			StringBuilder sbPluginOutput = new StringBuilder();
			for (int i = 0; i < count; i++) {
				ModelEvent me = new ModelEvent(model, 2000, "Plugin Started", "Start " + aPluginList.get(i).getPluginEnum() + " - "	+ aPluginList.get(i).getPluginName() + ".");
				model.notifyChanged(me);
				if (aPluginList.get(i) instanceof IPluginII) {
					IPluginII plugin = (IPluginII) aPluginList.get(i);
					plugin.setOutputDir(model.getOutputDirectory());
					sbPluginOutput.append(plugin.process(model.getMountedDirectory())).append(System.getProperty("line.separator"));
				} else {
					sbPluginOutput.append(aPluginList.get(i).process(model.getMountedDirectory())).append(System.getProperty("line.separator"));
				}
			}
			ModelEvent me = new ModelEvent(model, 2000, "Plugin Finished", 	"Finished " + aPluginList.get(0).getPluginEnum() + " Plugins.");
			model.notifyChanged(me);
			Logger.appendLog(new File(model.getOutputDirectory() + File.separator + aPluginList.get(0).getPluginEnum() + ".txt"), sbPluginOutput.toString());
		} catch (Exception e) {
			exceptionsList.add(e);
		}
	}

}
