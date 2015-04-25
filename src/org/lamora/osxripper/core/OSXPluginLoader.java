package org.bolodev.osxripper.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bolodev.osxripper.io.Logger;
import org.bolodev.osxripper.mvc.Model;
import org.bolodev.osxripper.plugin.IPlugin;

/**
 * Class responsible for finding, instantiating and loading plugins into the application model
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class OSXPluginLoader {

	private OSXRipperModel theModel;
	
	/**
	 * Constructor
	 * @param aModel cast to OSXRipperModel
	 */
	public OSXPluginLoader(Model aModel){
		theModel = (OSXRipperModel) aModel;
	}
	
	/**
	 * Scan the plugins directory for .jar files, hands off to loadPlugin to instantiate and load the plugin object
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public void scanForPluginJars() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		File pluginDir = new File("plugins");
		if(pluginDir.exists()){
			File[] pluginDirectoryFiles = pluginDir.listFiles();
			for(File f : pluginDirectoryFiles){
				if(f.getName().endsWith(".jar")){
					loadPlugin(f);
				}
			}
		}
		else{
			Logger.appendLog("osxripper.log", "OSXPluginLoader: No plugin directory found.");
		}
	}
	
	/**
	 * Instantiate plugin from osxripper/plugin/Plugin.class and add to the model
	 * @param aFile the jar file to search
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private void loadPlugin(File aFile) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		JarFile jar = new JarFile(aFile.getAbsolutePath());
        Enumeration<JarEntry> e = jar.entries();
        while (e.hasMoreElements()) {
          JarEntry pluginEntry = e.nextElement();
          if(pluginEntry.getName().equals("osxripper/plugin/Plugin.class")){
        	  URL url = new URL("file", null, aFile.getAbsolutePath());
    	      URLClassLoader cl = new URLClassLoader(new URL[] { url });
    	      IPlugin plugin = (IPlugin) cl.loadClass("osxripper.plugin.Plugin").newInstance();
    	      if(plugin != null){
    	    	  theModel.addIPlugin(plugin);
    	      }
    	      else{
    	    	  Logger.appendLog("osxripper.log", "OSXPluginLoader: " + pluginEntry.getName() + " not loaded.");
    	      }
          }     
        }
	}
	
}
