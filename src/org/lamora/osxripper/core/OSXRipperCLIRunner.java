package org.bolodev.osxripper.core;

import java.io.File;
import java.util.ArrayList;

import org.bolodev.osxripper.io.Logger;
import org.bolodev.osxripper.plugin.IPlugin;
import org.bolodev.osxripper.plugin.IPluginII;

/**
 * Command line version of OSXRipperWorker
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @since 0.2
 */
public class OSXRipperCLIRunner {
	
	private OSXRipperModel model;
	
	/**
	 * Constructor
	 * @param aModel the application model
	 */
	public OSXRipperCLIRunner(OSXRipperModel aModel) {
		model = aModel;
	}

	/**
	 * Run the active plugins
	 */
	public void runCLI(){
		System.out.println("[INFO] Running plugins.");
		ArrayList<IPlugin> osPlugins = model.getActiveOSPlugins();
		System.out.println("[INFO] OS:       " + osPlugins.size());
		ArrayList<IPlugin> softwarePlugins = model.getActiveSoftwarePlugins();
		System.out.println("[INFO] Software: " + softwarePlugins.size());
		ArrayList<IPlugin> hardwarePlugins = model.getActiveHardwarePlugins();
		System.out.println("[INFO] Hardware: " + hardwarePlugins.size());
		ArrayList<IPlugin> userPlugins = model.getActiveUserPlugins();
		System.out.println("[INFO] User:     " + userPlugins.size());
		ArrayList<IPlugin> iosPlugins = model.getActiveIOSPlugins();
		System.out.println("[INFO] iOS:      " + iosPlugins.size());

		if(osPlugins.size() > 0){
        	runPluginList(osPlugins);
        }
        if(softwarePlugins.size() > 0){
        	runPluginList(softwarePlugins);
        }
        if(hardwarePlugins.size() > 0){
        	runPluginList(hardwarePlugins);
        }
        if(userPlugins.size() > 0){
        	runPluginList(userPlugins);
        }
        if(iosPlugins.size() > 0){
        	runPluginList(iosPlugins);
        }
        System.out.println("[INFO] Finished running Plugins.");
	}
	
	/**
	 * Run the active plugins passed from the Model
	 * @param aPluginList the active plugins passed from the Model
	 */
	private void runPluginList(ArrayList<IPlugin> aPluginList){
    	try{
    		int count = aPluginList.size();
        	StringBuilder sbPluginOutput = new StringBuilder();
    		for(int i = 0 ; i < count ; i++){
    			
    			System.out.println("[INFO] Start " + aPluginList.get(i).getPluginEnum() + " - " + aPluginList.get(i).getPluginName() + ".");
    			
        		if(aPluginList.get(i) instanceof IPluginII){
        			IPluginII plugin = (IPluginII) aPluginList.get(i);
    				plugin.setOutputDir(model.getOutputDirectory());
    				sbPluginOutput.append(plugin.process(model.getMountedDirectory())).append(System.getProperty("line.separator"));
        		}
        		else{
        			sbPluginOutput.append(aPluginList.get(i).process(model.getMountedDirectory())).append(System.getProperty("line.separator"));
        		}
        	}
    		
    		System.out.println("[INFO] Finished Plugin " + aPluginList.get(0).getPluginEnum() + " Plugins.");
    		
			Logger.appendLog(new File(model.getOutputDirectory() + File.separator + aPluginList.get(0).getPluginEnum() + ".txt"), sbPluginOutput.toString());
    	}
    	catch(Exception e){
    		System.out.println("[EXCEPTION] " + e.getLocalizedMessage());
    	}
	}
	
}
