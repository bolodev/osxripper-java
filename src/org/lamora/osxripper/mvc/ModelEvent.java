package org.bolodev.osxripper.mvc;
import java.awt.event.ActionEvent;
/**
 * Used to notify interested objects of changes in the state of a model
 */
public class ModelEvent extends ActionEvent{

  /**
   * UID
   */
  private static final long serialVersionUID = 267216244091660970L;
  private int evtInt;
  private float evtFloat;
  private double evtDouble;
  private boolean evtTest;
  private char evtChar;
  private byte evtByte;
  private Object evtObj;
  
  /**
   * Basic Constructor
   */
  public ModelEvent(Object obj, int id, String actionCommand){
    super(obj, id, actionCommand);  
  }

  /**
   * Adapt the Model event to carry an int, note this overrides JDK where key modifiers are transmitted
   */
  public ModelEvent(Object obj, int id, String actionCommand, int anInt){
    super(obj, id, actionCommand);
    evtInt = anInt;
  }

  /**
   * Adapt the Model event to carry a floating point number
   */  
  public ModelEvent(Object obj, int id, String actionCommand, float aFloat){
    super(obj, id, actionCommand);
    evtFloat = aFloat;
  }

  /**
   * Adapt the Model event to carry a double number type
   */  
  public ModelEvent(Object obj, int id, String actionCommand, double aDouble){
    super(obj, id, actionCommand);
    evtDouble = aDouble;
  }

  /**
   * Adapt the Model event to carry a byte
   */  
  public ModelEvent(Object obj, int id, String actionCommand, byte aByte){
    super(obj, id, actionCommand);
    evtByte = aByte;
  }

  /**
   * Adapt the Model event to carry a char
   */  
  public ModelEvent(Object obj, int id, String actionCommand, char aChar){
    super(obj, id, actionCommand);
    evtChar = aChar;
  }

  /**
   * Adapt the Model event to carry an Object
   */
  public ModelEvent(Object obj, int id, String actionCommand, Object aObj){
    super(obj, id, actionCommand);
    evtObj = aObj;
  }
  
  /**
   * Adapt the Model event to carry a boolean value
   */
  public ModelEvent(Object obj, int id, String actionCommand, boolean aTest){
    super(obj, id, actionCommand);
    evtTest = aTest;
  }

  /**
   * @return an int passed through the event
   */
  public int getInt(){ return evtInt; }

  /**
   * @return a double passed through the event
   */
  public double getDouble(){ return evtDouble; }

  /**
   * @return a float passed through the event
   */
  public float getFloat(){ return evtFloat; }

  /**
   * @return a boolean value passed through the event
   */
  public boolean getTest(){ return evtTest; }

  /**
   * @return an Object passed through the event
   */
  public Object getObject(){ return evtObj; }

  /**
   * @return a char passed through the event
   */
  public char getChar(){ return evtChar; }

  /**
   * @return a byte passed through the event
   */
  public byte getByte(){ return evtByte; }
 
}
