package org.bolodev.osxripper.util;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Utility class to get a user name from a given OSX /Users path
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class GetUserName {

	/**
	 * Method to break out user name from /Users path
	 * @param aFilePath the path to get the user name from
	 * @return the user name from /Users
	 */
	public static String getUserNameFromPath(File aFilePath){
		String[] subDirs =  aFilePath.getAbsolutePath().split(Pattern.quote(java.io.File.separator));
		String outputPart = "NOUSER";
        for(int j = 0 ; j < subDirs.length ; j++){
        	if(subDirs[j].equals("Users")){
        		outputPart = subDirs[j+1];
        		break;
        	}
        }
        return outputPart;
	}
	
}
