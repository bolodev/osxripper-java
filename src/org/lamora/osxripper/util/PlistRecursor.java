package org.bolodev.osxripper.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.dd.plist.NSArray;
import com.dd.plist.NSData;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;

/**
 * Utility class to parse a plist and return tab formatted text
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class PlistRecursor {

	protected static String NL = System.getProperty("line.separator");
	protected ArrayList<String> watchList = new ArrayList<String>();
	private File plistToScan;
	protected int indentLevel = 0;
	
	/**
	 * Constructor
	 * @param aPlistToScan the plist to scan
	 * @param aKeyArray array of special case keys
	 */
	public PlistRecursor(File aPlistToScan, String[] aKeyArray){
		plistToScan = aPlistToScan;
		for(String key : aKeyArray){
			watchList.add(key);
		}
	}
	
	/**
	 * Constructor
	 * @param aPlistToScan the plist to scan
	 */
	public PlistRecursor(File aPlistToScan){
		plistToScan = aPlistToScan;
	}
	
	/**
	 * Start with root NSDictionary or NSArray
	 * @return formatted string of the plist
	 */
	public String dumpPlistToRaw() throws Exception{
		StringBuffer sb = new StringBuffer();
		if(plistToScan.exists()){
			NSObject plistRaw = PropertyListParser.parse(plistToScan);
			if(plistRaw instanceof NSDictionary){
				sb.append(parseNSDictionary((NSDictionary) plistRaw, indentLevel));
			}
			if(plistRaw instanceof NSArray){
				sb.append(parseNSArray((NSArray) plistRaw, indentLevel));
			}
		}
		return sb.toString();
	}
	
	/**
	 * Parse an NSDictionary object to string
	 * @param aDictionary the NSDictionary to parse
	 * @return formatted string of the NSDictionary
	 */
	protected String parseNSDictionary(NSDictionary aDictionary, int aLevel){
		HashMap<String, NSObject> dictMap = aDictionary.getHashMap();
		StringBuffer sb = new StringBuffer();
		if(dictMap.size() > 0){
			for(String s : dictMap.keySet()){
				NSObject sObj = dictMap.get(s);
				if(watchList.size() != 0 && watchList.contains(s)){
					String parsedKey = parseWatchKey(s, sObj, aLevel);
					if(parsedKey != ""){
						sb.append(parsedKey);
					}
				}
				else if(sObj instanceof NSArray){
					sb.append(indentTabs(aLevel)).append(s).append(":").append(NL);
					sb.append(parseNSArray((NSArray) sObj, aLevel+1));
				}
				else if(sObj instanceof NSDictionary){
					sb.append(indentTabs(aLevel)).append(s).append(":").append(NL);
					sb.append(parseNSDictionary((NSDictionary) sObj, aLevel+1));
				}
				else if(sObj instanceof NSData){
					String base64String = ((NSData)sObj).getBase64EncodedData();
					if(base64String.length() > 68){
						int inserts = (int) Math.ceil(base64String.length() / 68.0);
						sb.append(indentTabs(aLevel)).append(s).append(":").append(NL);
						for(int i = 0 ; i < inserts ; i++){
							int outer = (i*68)+68;
							if(outer > base64String.length()){
								sb.append(indentTabs(aLevel+1)).append(base64String.substring(i*68)).append(NL);
							}
							else{
								sb.append(indentTabs(aLevel+1)).append(base64String.subSequence(i*68, outer)).append(NL);
							}
						}
					}
					else{
						sb.append(indentTabs(aLevel)).append(s).append(": ").append(base64String).append(NL);
					}
				}
				else{
					sb.append(indentTabs(aLevel)).append(s).append(": ").append(sObj.toString()).append(NL);
				}
			}
		}
		else{
			sb.append(indentTabs(aLevel)).append("Empty NSDictionary object, no data to parse.").append(NL);
		}
		return sb.toString();
	}
	
	/**
	 * Parse an NSArray object to string
	 * @param anArray the NSArray to parse
	 * @return formatted string of the NSArray
	 */
	protected String parseNSArray(NSArray anArray, int aLevel){
		int arrayCount = anArray.count();
		StringBuffer sb = new StringBuffer();
		if(arrayCount > 0){
			for(int i = 0 ; i < arrayCount ; i++){
				NSObject arrObj = anArray.objectAtIndex(i);
				if(arrObj instanceof NSDictionary){
					sb.append(parseNSDictionary((NSDictionary) arrObj, aLevel+1)).append(NL);
				}
				else if(arrObj instanceof NSArray){
					sb.append(parseNSArray((NSArray) arrObj, aLevel+1));
				} else if(arrObj instanceof NSData){
					sb.append(parseNSData((NSData) arrObj, aLevel)).append(NL);
				}
				else {
					sb.append(indentTabs(aLevel)).append(i).append(": ").append(arrObj.toString()).append(NL);
				}
			}
		}
		else{
			sb.append(indentTabs(aLevel)).append("Empty array object, no data to parse.").append(NL);
		}
		return sb.toString();
	}
    
	/**
	 * Assumes Base64 String content
	 * @param aNSDataObj the NSData object to parse
	 * @param aLevel the indent level
	 * @return formatted Base64 string 68 characters wide
	 */
	protected String parseNSData(NSData aNSDataObj, int aLevel){
    	StringBuffer sb = new StringBuffer();
    	String base64String = aNSDataObj.getBase64EncodedData();
		if(base64String.length() > 68){
			int inserts = (int) Math.ceil(base64String.length() / 68.0);
			for(int i = 0 ; i < inserts ; i++){
				int outer = (i*68)+68;
				if(outer > base64String.length()){
					sb.append(indentTabs(aLevel+1)).append(base64String.substring(i*68)).append(NL);
				}
				else{
					sb.append(indentTabs(aLevel+1)).append(base64String.subSequence(i*68, outer)).append(NL);
				}
			}
		}
		else{
			sb.append(indentTabs(aLevel)).append(base64String).append(NL);
		}
		return sb.toString();
    }
    
    /**
     * Override this method to deal with requirements for the specified key.
     * @param aKey the key to handle
     * @param anObject the NSObject for the key
     * @return formatted string of the NSObject
     */
    protected String parseWatchKey(String aKey, NSObject anObject, int anIndentLevel){
    	return indentTabs(anIndentLevel+1) + anObject.toString() + NL;
    }
	
    /**
     * Provide tab indents for the formatting
     * @param indentLevel the number of tabs to create
     * @return String of \t chars
     */
	protected String indentTabs(int indentLevel){
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < indentLevel ; i++){
			sb.append('\t');
		}
		return sb.toString();
	}
}
