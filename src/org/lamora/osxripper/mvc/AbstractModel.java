package org.bolodev.osxripper.mvc;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Abstract root class of Model hierarchy - provides basic notification behaviour.
 */
public abstract class AbstractModel implements Model{

  private ArrayList<ModelListener> listeners = new ArrayList<ModelListener>(5);

  /**
   * Method that is called by subclasses of AbstractModel when they want to notify 
   * other classes of changes to themselves.
   */
  public void notifyChanged(ModelEvent event){
    Iterator<ModelListener> it = listeners.iterator();
    while(it.hasNext()){
      ModelListener ml = (ModelListener)it.next();
      ml.modelChanged(event);
    } 
  }

  /**
   * Add a ModelListener to the list of objects interested in ModelEvents
   */
  public void addModelListener(ModelListener l){
    listeners.add(l);
  }

  /**
   * Remove a ModelListener to the list of objects interested in ModelEvents
   */
  public void removeModelListener(ModelListener l){
    listeners.remove(l);
  }

}
