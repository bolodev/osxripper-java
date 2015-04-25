package org.bolodev.osxripper;

import org.bolodev.osxripper.core.OSXRipperController;

/**
 * Driver class
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @version 0.2 added command line arguments
 * @since 0.1
 */
public class OSXRipperMain {

	/**
	 * @param args used for command line configuration
	 */
	public static void main(String[] args) {
		processArgs(args);
	}
	
	private static void processArgs(String[] args){
		switch(args.length){
			case 0:
				new OSXRipperController().run();
				break;
			case 1:
				if(args[0].equals("-V")){
					printVersion();
				}
				if(args[0].equals("-h")){
					printUsage();
				}
				break;
			case 5:
				String mode = "";
				if(args[0].equalsIgnoreCase("--ALL")){
					mode = "all";
				}
				if(args[0].equalsIgnoreCase("--OS")){
					mode = "os";
				}
				if(args[0].equalsIgnoreCase("--HW")){
					mode = "hw";
				}
				if(args[0].equalsIgnoreCase("--SW")){
					mode = "sw";
				}
				if(args[0].equalsIgnoreCase("--USER")){
					mode = "user";
				}
				if(args[0].equalsIgnoreCase("--IOS")){
					mode = "ios";
				}
				if(!mode.equals("")){
					new OSXRipperController().run(mode, args[2], args[4]);
				}
				else{
					printUsage();
				}
				break;
			default:
				printUsage();
				break;
		}
	}
	
	/**
	 * Print the command line usage to Standard Out
	 */
	private static void printUsage(){
		System.out.println("For UI version:");
		System.out.println("java -jar osxripper.jar");
		System.out.println();
		System.out.println("For CLI version:");
		System.out.println("java -jar osxripper.jar --[ALL|OS|HW|SW|USER|IOS] -i <inputDir> -o <outputDir>");
		System.out.println("--ALL\tRun all plugins");
		System.out.println("--OS\tOnly run OS plugins");
		System.out.println("--HW\tOnly run Hardware plugins");
		System.out.println("--SW\tOnly run Software plugins");
		System.out.println("--USER\tOnly run User plugins");
		System.out.println("--IOS\tOnly run iOS plugins");
		System.out.println();
		System.out.println("Print usage:");
		System.out.println("java -jar osxripper.jar -h");
		System.out.println();
		System.out.println("Print version:");
		System.out.println("java -jar osxripper.jar -V");
		System.out.println();
	}
	
	private static void printVersion(){
		System.out.println();
		System.out.println("OSXRipper Version 0.2");
		System.out.println();
	}

}
