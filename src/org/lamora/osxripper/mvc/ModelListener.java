package org.bolodev.osxripper.mvc;
/**
 * Interface for model listeners
 */
public interface ModelListener{
  
  /**
   * @param event notify the model of a change
   */
  public void modelChanged(ModelEvent event);
}
