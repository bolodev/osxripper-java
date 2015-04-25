package org.bolodev.osxripper.core;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

import org.bolodev.osxripper.mvc.AbstractModel;
import org.bolodev.osxripper.mvc.ModelEvent;
import org.bolodev.osxripper.plugin.IPlugin;
/**
 * Application model, responsible for holding instantiated plugins
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @version 0.2 updated for command line version
 * @since 0.1
 */
public class OSXRipperModel extends AbstractModel {

	private ArrayList<String> pluginLoadMessageQ = new ArrayList<String>();
	
	private TreeMap<String, IPlugin> osMap = new TreeMap<String, IPlugin>();
	private TreeMap<String, IPlugin> hardwareMap = new TreeMap<String, IPlugin>();
	private TreeMap<String, IPlugin> softwareMap = new TreeMap<String, IPlugin>();
	private TreeMap<String, IPlugin> userMap = new TreeMap<String, IPlugin>();
	private TreeMap<String, IPlugin> iosMap = new TreeMap<String, IPlugin>();
	
	private File mountedDirectory = new File(".");
	private File outputDirectory = new File(".");
	
	private boolean isCLI = false;
	
	/**
	 * Constructor
	 */
	public OSXRipperModel() {	}
	
	/**
	 * Add a plugin object to the appropriate TreeMap based on PluginEnum, if a name collision occurs within a category
	 * @param aPlugin the plugin object to add
	 */
	public void addIPlugin(IPlugin aPlugin){
		switch(aPlugin.getPluginEnum()){
		    case OS:
		    	if(osMap.containsKey(aPlugin.getPluginName())){
		    		pluginLoadMessageQ.add("Cannot add " + aPlugin.getPluginName() + " a plugin by that name already exists in the OS category");
		    	}
		    	else{
		    		osMap.put(aPlugin.getPluginName(), aPlugin);
		    	}
			    break;
		    case HARDWARE:
		    	if(hardwareMap.containsKey(aPlugin.getPluginName())){
		    		pluginLoadMessageQ.add("Cannot add " + aPlugin.getPluginName() + " a plugin by that name already exists in the Hardware category");
		    	}
		    	else{
		    		hardwareMap.put(aPlugin.getPluginName(), aPlugin);
		    	}
			    break;
		    case SOFTWARE:
		    	if(softwareMap.containsKey(aPlugin.getPluginName())){
		    		pluginLoadMessageQ.add("Cannot add " + aPlugin.getPluginName() + " a plugin by that name already exists in the Software category");
		    	}
		    	else{
		    		softwareMap.put(aPlugin.getPluginName(), aPlugin);
		    	}
		    	break;
		    case USER:
		    	if(userMap.containsKey(aPlugin.getPluginName())){
		    		pluginLoadMessageQ.add("Cannot add " + aPlugin.getPluginName() + " a plugin by that name already exists in the User category");
		    	}
		    	else{
		    		userMap.put(aPlugin.getPluginName(), aPlugin);
		    	}
		    	break;
		    case IOS:
		    	if(iosMap.containsKey(aPlugin.getPluginName())){
		    		pluginLoadMessageQ.add("Cannot add " + aPlugin.getPluginName() + " a plugin by that name already exists in the iOS category");
		    	}
		    	else{
		    		iosMap.put(aPlugin.getPluginName(), aPlugin);
		    	}
		    	break;
		    default:
		    	pluginLoadMessageQ.add("Cannot add a plugin it does not have a recognized plugin category value");
		    	break;
		}
	}
	
	/**
	 * Sets the value for the root mounted directory or directory available for scanning
	 * @param aDirectory the directory to scan
	 */
	public void setMountedDirectory(File aDirectory){
		mountedDirectory = aDirectory;
		ModelEvent me = new ModelEvent(this, 1000, "Set Input", mountedDirectory.getAbsolutePath());
		notifyChanged(me);
	}
	
	/**
	 * @return the root mounted directory or directory available for scanning
	 */
	public File getMountedDirectory(){
		return mountedDirectory;
	}
	
	/**
	 * Set the directory to write the plugin output to
	 * @param aDirectory directory to write output to
	 */
	public void setOutputDirectory(File aDirectory){
		outputDirectory = aDirectory;
		ModelEvent me = new ModelEvent(this, 1001, "Set Output", outputDirectory.getAbsolutePath());
		notifyChanged(me);
	}
	
	/**
	 * @return directory to write output to
	 */
	public File getOutputDirectory(){
		return outputDirectory;
	}

	/**
	 * @return array of OS plugin names for the PluginPanel table
	 */
	public Object[] getOSPluginNames(){
		return osMap.keySet().toArray();
	}
	
	/**
	 * @return array of Hardware plugin names for the PluginPanel table
	 */
	public Object[] getHardwarePluginNames(){
		return hardwareMap.keySet().toArray();
	}
	
	/**
	 * @return array of Software plugin names for the PluginPanel table
	 */
	public Object[] getSoftwarePluginNames(){
		return softwareMap.keySet().toArray();
	}
	
	/**
	 * @return  array of User plugin names for the PluginPanel table
	 */
	public Object[] getUserPluginNames(){
		return userMap.keySet().toArray();
	}
	
	/**
	 * @return array of iOS plugin names for the PluginPanel table
	 */
	public Object[] getIOSPluginNames(){
		return iosMap.keySet().toArray();
	}

	/**
	 * @return the pluginLoadMessageQ
	 */
	public String getPluginLoadMessageQ() {
		StringBuilder message = new StringBuilder();
		if(pluginLoadMessageQ.size() != 0){
			System.out.println("MODEL: " + pluginLoadMessageQ.toString());
			int messageCount = pluginLoadMessageQ.size();
			for(int i = 0 ; i < messageCount ; i++){
				message.append(pluginLoadMessageQ.get(i)).append('\n');
			}
		}
		return message.toString();
	}
	
	/**
	 * Bulk operation to set a plugin category as active or inactive 
	 * @param aPluginCategory the category to update
	 * @param trueForActive true for active, false for inactive
	 */
	public void setPluginsActive(String aPluginCategory, boolean trueForActive){
		if(aPluginCategory.equals("OS")){
			for(String s : osMap.keySet()){
				osMap.get(s).setPluginActive(trueForActive);
			}
		}
        if(aPluginCategory.equals("Hardware")){
        	for(String s : hardwareMap.keySet()){
        		hardwareMap.get(s).setPluginActive(trueForActive);
			}
		}
        if(aPluginCategory.equals("Software")){
        	for(String s : softwareMap.keySet()){
        		softwareMap.get(s).setPluginActive(trueForActive);
			}
		}
        if(aPluginCategory.equals("User")){
        	for(String s : userMap.keySet()){
        		userMap.get(s).setPluginActive(trueForActive);
			}
		}
        if(aPluginCategory.equals("iOS")){
        	for(String s : iosMap.keySet()){
        		iosMap.get(s).setPluginActive(trueForActive);
			}
		}
	}
	
	/**
	 * Set a named plugin as active or inactive
	 * @param aPluginCategory the plugin category to update
	 * @param aPluginName the name of the plugin to change
	 * @param trueForActive true for active, false for inactive
	 */
	public void setPluginsActive(String aPluginCategory, String aPluginName, boolean trueForActive){
		if(aPluginCategory.equals("OS")){
			osMap.get(aPluginName).setPluginActive(trueForActive);
		}
        if(aPluginCategory.equals("Hardware")){
        	hardwareMap.get(aPluginName).setPluginActive(trueForActive);
		}
        if(aPluginCategory.equals("Software")){
        	softwareMap.get(aPluginName).setPluginActive(trueForActive);
		}
        if(aPluginCategory.equals("User")){
        	userMap.get(aPluginName).setPluginActive(trueForActive);
		}
        if(aPluginCategory.equals("iOS")){
        	iosMap.get(aPluginName).setPluginActive(trueForActive);
		}
	}
	
	/**
	 * @return arraylist of active plugins
	 */
	protected ArrayList<IPlugin> getActiveOSPlugins(){
		ArrayList<IPlugin> list = new ArrayList<IPlugin>();
		for(String s : osMap.keySet()){
			if(osMap.get(s).isPluginActive()){
				list.add(osMap.get(s));
			}
		}
		return list;
	}
	
	/**
	 * @return arraylist of active plugins
	 */
	protected ArrayList<IPlugin> getActiveHardwarePlugins(){
		ArrayList<IPlugin> list = new ArrayList<IPlugin>();
		for(String s : hardwareMap.keySet()){
			if(hardwareMap.get(s).isPluginActive()){
				list.add(hardwareMap.get(s));
			}
		}
		return list;
	}
	
	/**
	 * @return arraylist of active plugins
	 */
	protected ArrayList<IPlugin> getActiveSoftwarePlugins(){
		ArrayList<IPlugin> list = new ArrayList<IPlugin>();
		for(String s : softwareMap.keySet()){
			if(softwareMap.get(s).isPluginActive()){
				list.add(softwareMap.get(s));
			}
		}
		return list;
	}
	
	/**
	 * @return arraylist of active plugins
	 */
	protected ArrayList<IPlugin> getActiveUserPlugins(){
		ArrayList<IPlugin> list = new ArrayList<IPlugin>();
		for(String s : userMap.keySet()){
			if(userMap.get(s).isPluginActive()){
				list.add(userMap.get(s));
			}
		}
		return list;
	}
	
	/**
	 * @return arraylist of active plugins
	 */
	protected ArrayList<IPlugin> getActiveIOSPlugins(){
		ArrayList<IPlugin> list = new ArrayList<IPlugin>();
		for(String s : iosMap.keySet()){
			if(iosMap.get(s).isPluginActive()){
				list.add(iosMap.get(s));
			}
		}
		return list;
	}
	
	/**
	 * Run the plugins with OSXRipperWorker
	 */
	public void runPlugins(){
		if(!isCLI){
			OSXRipperWorker osxWorker = new OSXRipperWorker(this);
			osxWorker.execute();
		}
		else{
			if(getMountedDirectory().exists() && getOutputDirectory().exists()){
				new OSXRipperCLIRunner(this).runCLI();
			}
			else{
				if(!getMountedDirectory().exists()){
					System.out.println("The input directory does not exist: " + getMountedDirectory().getAbsolutePath());
				}
				if(!getOutputDirectory().exists()){
					System.out.println("The output directory does not exist: " + getOutputDirectory().getAbsolutePath());
				}
			}
		}
	}
	
	/**
	 * Collate plugin names and descriptions and then notify the View to display the results
	 */
	public void getPluginDescriptionListing(){
		StringBuilder sbListing = new StringBuilder();
		String pluginDivider = "------------------------------------------------------------------------------------------\n";
		String categoryDivider = "==========================================================================================\n";
		sbListing.append(categoryDivider).append('\n');
		for(String s : osMap.keySet()){
			IPlugin tempPlugin = osMap.get(s);
			if(osMap.firstKey().equals(tempPlugin.getPluginName())){
				sbListing.append(tempPlugin.getPluginEnum()).append('\n');
				sbListing.append(tempPlugin.getPluginName()).append('\n');
				sbListing.append(tempPlugin.getPluginDescription()).append('\n');
			}
			else{
				sbListing.append(pluginDivider).append('\n');
				sbListing.append(tempPlugin.getPluginEnum()).append('\n');
				sbListing.append(tempPlugin.getPluginName()).append('\n');
				sbListing.append(tempPlugin.getPluginDescription()).append('\n');
			}
		}
		sbListing.append(categoryDivider);
		for(String s : hardwareMap.keySet()){
			IPlugin tempPlugin = hardwareMap.get(s);
			if(hardwareMap.firstKey().equals(tempPlugin.getPluginName())){
				sbListing.append(tempPlugin.getPluginEnum()).append('\n');
				sbListing.append(tempPlugin.getPluginName()).append('\n');
				sbListing.append(tempPlugin.getPluginDescription()).append('\n');
			}
			else{
				sbListing.append(pluginDivider).append('\n');
				sbListing.append(tempPlugin.getPluginEnum()).append('\n');
				sbListing.append(tempPlugin.getPluginName()).append('\n');
				sbListing.append(tempPlugin.getPluginDescription()).append('\n');
			}
		}
		sbListing.append(categoryDivider);
		for(String s : softwareMap.keySet()){
			IPlugin tempPlugin = softwareMap.get(s);
			if(softwareMap.firstKey().equals(tempPlugin.getPluginName())){
				sbListing.append(tempPlugin.getPluginEnum()).append('\n');
				sbListing.append(tempPlugin.getPluginName()).append('\n');
				sbListing.append(tempPlugin.getPluginDescription()).append('\n');
			}
			else{
				sbListing.append(pluginDivider).append('\n');
				sbListing.append(tempPlugin.getPluginEnum()).append('\n');
				sbListing.append(tempPlugin.getPluginName()).append('\n');
				sbListing.append(tempPlugin.getPluginDescription()).append('\n');
			}
		}
		sbListing.append(categoryDivider);
		for(String s : userMap.keySet()){
			IPlugin tempPlugin = userMap.get(s);
			if(userMap.firstKey().equals(tempPlugin.getPluginName())){
				sbListing.append(tempPlugin.getPluginEnum()).append('\n');
				sbListing.append(tempPlugin.getPluginName()).append('\n');
				sbListing.append(tempPlugin.getPluginDescription()).append('\n');
			}
			else{
				sbListing.append(pluginDivider).append('\n');
				sbListing.append(tempPlugin.getPluginEnum()).append('\n');
				sbListing.append(tempPlugin.getPluginName()).append('\n');
				sbListing.append(tempPlugin.getPluginDescription()).append('\n');
			}
		}
		sbListing.append(categoryDivider);
		for(String s : iosMap.keySet()){
			IPlugin tempPlugin = iosMap.get(s);
			if(iosMap.firstKey().equals(tempPlugin.getPluginName())){
				sbListing.append(tempPlugin.getPluginEnum()).append('\n');
				sbListing.append(tempPlugin.getPluginName()).append('\n');
				sbListing.append(tempPlugin.getPluginDescription()).append('\n');
			}
			else{
				sbListing.append(pluginDivider).append('\n');
				sbListing.append(tempPlugin.getPluginEnum()).append('\n');
				sbListing.append(tempPlugin.getPluginName()).append('\n');
				sbListing.append(tempPlugin.getPluginDescription()).append('\n');
			}
		}
		sbListing.append(categoryDivider);
		ModelEvent me = new ModelEvent(this, 3000, "Show Plugin descriptions", sbListing.toString());
		notifyChanged(me);
	}
	
	/**
	 * Update the status bar count of the number of loaded plugins
	 */
	public void updatePluginCount(){
		int pluginCount = osMap.size() + softwareMap.size() + hardwareMap.size() + userMap.size() + iosMap.size();
		if(!isCLI){
			ModelEvent me = new ModelEvent(this, 4000, "update plugin count", pluginCount);
			notifyChanged(me);
		}
		else{
			System.out.println("[INFO] " + pluginCount + " Plugins loaded");
		}
	}

	/**
	 * @return the isCLI
	 */
	public boolean isCLI() {
		return isCLI;
	}

	/**
	 * @param isCLI the isCLI to set
	 */
	public void setCLI(boolean isCLI) {
		this.isCLI = isCLI;
	}

}
