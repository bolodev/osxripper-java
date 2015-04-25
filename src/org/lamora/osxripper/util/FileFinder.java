package org.bolodev.osxripper.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Class to scan for named file in a given directory 
 * @author bolodev
 * @version 0.1
 * @since 0.1
 */
public class FileFinder {
	
	/**
	 * Scan for a file in a given directory 
	 * @param aStartPoint the starting directory
	 * @param aFileToFind the name of the file to scan for
	 * @return list of files
	 * @throws IOException
	 */
	public static ArrayList<File> findFile(final File aStartPoint, final String aFileToFind) throws IOException{
		final ArrayList<File> cookieFiles = new ArrayList<File>();
		new FileTraversal(){
			public void onFile(final File f) {
				if(f.getName().equalsIgnoreCase(aFileToFind)){
					cookieFiles.add(f);
				}
			}
		}.traverse(aStartPoint);
		
		return cookieFiles;
	}
}
