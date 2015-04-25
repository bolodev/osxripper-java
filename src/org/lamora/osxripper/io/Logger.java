package org.bolodev.osxripper.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to append to a log file
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public final class Logger {

  /**
   * Method to append to a log file
   * @param fileName the name of the log file
   * @param message the String to append to the log file
   */
  public static void appendLog(final File fileName, final String message){
    String line = message + System.getProperty("line.separator");
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
      out.write(line);
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
  
  /**
   * Method to append to a log file
   * @param fileName the name of the log file
   * @param message the String to append to the log file
   */
  public static void appendLog(final String fileName, final String message){
    String line = message + System.getProperty("line.separator");
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
      out.write(line);
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
  
  /**
   * Method to append to a log file without adding new line characters
   * @param fileName the name of the log file
   * @param message the String to append to the log file
   */
  public static void appendLogNoLine(final File fileName, final String message){
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
      out.write(new String(message.getBytes(), "UTF-8"));
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
}