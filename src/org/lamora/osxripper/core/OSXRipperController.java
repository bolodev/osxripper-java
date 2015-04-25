package org.bolodev.osxripper.core;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.bolodev.osxripper.io.Logger;
import org.bolodev.osxripper.mvc.AbstractController;
import org.bolodev.osxripper.ui.frames.OSXRipperFrame;

/**
 * Application controller class, runs OSXPluginLoader and starts the UI
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @version 0.2 changed application version in showAboutDialog()<br />
 * @version 0.3 updated to run with command line arguments<br />
 * @since 0.1
 */
public class OSXRipperController extends AbstractController {

	/**
	 * Constructor, instantiates model and loads plugins
	 */
	public OSXRipperController() {
		setModel(new OSXRipperModel());
		try {
			OSXPluginLoader pluginLoader = new OSXPluginLoader(getModel());
			pluginLoader.scanForPluginJars();
		} catch (InstantiationException e) {
			Logger.appendLog("osxripper.log", "OSXRipperController: InstantiationException - " + e.getLocalizedMessage());
		} catch (IllegalAccessException e) {
			Logger.appendLog("osxripper.log", "OSXRipperController: IllegalAccessException - " + e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			Logger.appendLog("osxripper.log", "OSXRipperController: ClassNotFoundException - " + e.getLocalizedMessage());
		} catch (IOException e) {
			Logger.appendLog("osxripper.log", "OSXRipperController: IOException - " + e.getLocalizedMessage());
		}
		setView(new OSXRipperFrame(getModel(), this));
	}
	
	/**
	 * Run the UI
	 */
	public void run(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				((OSXRipperFrame)getView()).setVisible(true);
				String pluginLoadErrors = ((OSXRipperModel)getModel()).getPluginLoadMessageQ();
				if(pluginLoadErrors.length() > 0){
					((OSXRipperFrame)getView()).warningMessage(pluginLoadErrors);
				}
				else{
					((OSXRipperModel)getModel()).updatePluginCount();
				}
			}
		});
	}
	
	/**
	 * 
	 * @param aMode
	 * @param anInputDir
	 * @param anOutputDir
	 */
	public void run(String aMode, String anInputDir, String anOutputDir){
		String pluginLoadErrors = ((OSXRipperModel)getModel()).getPluginLoadMessageQ();
		if(pluginLoadErrors.length() > 0){
			System.out.println();
			System.out.println("[ERROR] " + pluginLoadErrors);
			System.out.println();
			System.exit(1);
		}
		else{
			((OSXRipperModel)getModel()).setCLI(true);
			((OSXRipperModel)getModel()).setMountedDirectory(new File(anInputDir));
			((OSXRipperModel)getModel()).setOutputDirectory(new File(anOutputDir));
			System.out.println("[INFO] Input Directory set to:  " + ((OSXRipperModel)getModel()).getMountedDirectory().getAbsolutePath());
			System.out.println("[INFO] Output Directory set to: " + ((OSXRipperModel)getModel()).getOutputDirectory().getAbsolutePath());
			if(aMode.equals("os")){
				((OSXRipperModel)getModel()).setPluginsActive("Hardware", false);
				((OSXRipperModel)getModel()).setPluginsActive("Software", false);
				((OSXRipperModel)getModel()).setPluginsActive("User", false);
				((OSXRipperModel)getModel()).setPluginsActive("iOS", false);
				System.out.println("[INFO] Only running OS plugins.");
			}
			if(aMode.equals("hw")){
				((OSXRipperModel)getModel()).setPluginsActive("OS", false);
				((OSXRipperModel)getModel()).setPluginsActive("Software", false);
				((OSXRipperModel)getModel()).setPluginsActive("User", false);
				((OSXRipperModel)getModel()).setPluginsActive("iOS", false);
				System.out.println("[INFO] Only running Hardware plugins.");
			}
			if(aMode.equals("sw")){
				((OSXRipperModel)getModel()).setPluginsActive("OS", false);
				((OSXRipperModel)getModel()).setPluginsActive("Hardware", false);
				((OSXRipperModel)getModel()).setPluginsActive("User", false);
				((OSXRipperModel)getModel()).setPluginsActive("iOS", false);
				System.out.println("[INFO] Only running Software plugins.");
			}
			if(aMode.equals("user")){
				((OSXRipperModel)getModel()).setPluginsActive("OS", false);
				((OSXRipperModel)getModel()).setPluginsActive("Hardware", false);
				((OSXRipperModel)getModel()).setPluginsActive("Software", false);
				((OSXRipperModel)getModel()).setPluginsActive("iOS", false);
				System.out.println("[INFO] Only running User plugins.");
			}
			if(aMode.equals("ios")){
				((OSXRipperModel)getModel()).setPluginsActive("OS", false);
				((OSXRipperModel)getModel()).setPluginsActive("Hardware", false);
				((OSXRipperModel)getModel()).setPluginsActive("Software", false);
				((OSXRipperModel)getModel()).setPluginsActive("User", false);
				System.out.println("[INFO] Only running iOS plugins.");
			}
			((OSXRipperModel)getModel()).runPlugins();
		}
	}
	
	/**
	 * Show a confirm dialog to exit the application
	 */
	public void exitApplication(){
	    int exit = JOptionPane.showConfirmDialog((OSXRipperFrame)getView(), "Quit Application?", "Quit", JOptionPane.YES_NO_OPTION);
	    if (exit == JOptionPane.YES_OPTION) {
	      System.exit(0);
	    }
	}
	
	/**
	 * Show an information dialog with 'About' details
	 */
	public void showAboutDialog(){
		String nl =  "\n";
		String title = "OSXRipper - A tool to extract System and User data from OSX plists, logs and databases." + nl;
		String version = "Version: 0.2" + nl;
		String author = "Author: bolodev (c) 2013" + nl + nl;
		String thirdParty = "Third Party Code and Libraries" +nl;
		String kmpCredit = "KMPMatch: http://stackoverflow.com/questions/1507780/searching-for-a-sequence-of-bytes-in-a-binary-file-with-java" + nl;
		String fileTraverseCredit = "File Traversal: http://vafer.org/blog/20071112204524/" + nl;
		String mvcCredit = "MVC Code: http://www.scribd.com/doc/16646891/Model-View-Controller" + nl;
		String apacheCredit = "Apache Compress: https://commons.apache.org/proper/commons-compress/index.html" + nl;
		String ddplistCredit = "Plist library: https://code.google.com/p/plist/" + nl;
		String sqliteCredit = "SQLite JDBC: https://bitbucket.org/xerial/sqlite-jdbc/downloads" + nl;
		((OSXRipperFrame)getView()).infoMessage(title + version + author + thirdParty + kmpCredit + fileTraverseCredit + mvcCredit + apacheCredit + ddplistCredit + sqliteCredit);
	}
	
	/**
	 * Clear the text from the text console
	 */
	public void clearConsole(){
		((OSXRipperFrame)getView()).clearConsole();
	}
	
}
