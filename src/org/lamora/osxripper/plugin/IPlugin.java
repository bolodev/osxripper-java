package org.bolodev.osxripper.plugin;

import java.io.File;

/**
 * Plugin interface, do be implemented by Plugin classes
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public interface IPlugin {

	/**
	 * Set a name for the plugin
	 * @param aName a name for the plugin
	 */
	public void setPluginName(String aName);
	
	/**
	 * Set a friendly description for the plugin
	 * @param aDescription a friendly description for the plugin
	 */
	public void setPluginDescription(String aDescription);
	
	/**
	 * Set the category Enum for the plugin
	 * @param aPluginEnum the category Enum for the plugin
	 */
	public void setPluginEnum(PluginEnum aPluginEnum);
	
	/**
	 * Set a boolean flag to indicate whether the plugin is active
	 * @param trueForActive a boolean flag to indicate whether the plugin is active
	 */
	public void setPluginActive(boolean trueForActive);
	
	/**
	 * @return the name of the plugin
	 */
	public String getPluginName();
	
	/**
	 * @return the description of the plugin
	 */
	public String getPluginDescription();
	
	/**
	 * @return the category Enum of the plugin
	 */
	public PluginEnum getPluginEnum();
	
	/**
	 * @return the boolean flag indicating whether the plugin is active
	 */
	public boolean isPluginActive();
	
	/**
	 * Method to implement to process an artifact
	 * @param anInputDirectory the starting directory, prepend this to any path specified for the artifact
	 * @return output to write to the report file
	 */
	public String process(File anInputDirectory);
}
