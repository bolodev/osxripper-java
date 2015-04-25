package org.bolodev.osxripper.plugin;

import java.io.File;

/**
 * Interface extending original IPlugin, adds output directory so that plugins can write or append to specialised log files, other than the defaults.
 * @author bolodev
 * @version 0.1
 * @since 0.1
 */
public interface IPluginII extends IPlugin {

	/**
	 * @param aDirectory the directory to write the log file, intended to be picked up from the OSXRipperModel.getOutputDirectory()
	 */
	public void setOutputDir(File aDirectory);
	
	/**
	 * @return the directory to write to
	 */
	public File getOutputDir();
	
}
