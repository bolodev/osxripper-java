package org.bolodev.osxripper.mvc;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The JFrameView class is the root class of the view class hierarchy for top level
 * (Swing) Frames. It allows a Controller and a Model to be registered and can register
 * itself with a model as an observer of that model.<br />
 * It requires the implementation of the <code>modelChanged(ModelEvent event)</code>
 * method in order for it to work with the notification mechanism in Java.
 */
public abstract class JFrameView extends JFrame implements View, ModelListener{

  /**
   * UID
   */
  private static final long serialVersionUID = -6836204269107490013L;
  private Model model;
  private Controller controller;

  /**
   * Constructor 
   * @param model the application Model
   * @param controller the application Controller
   */
  public JFrameView(Model model, Controller controller){
    setModel(model);
    setController(controller);
  }
  
  /**
   * Initialise sets the title of the app and specifies the height and width of the window
   * @param title String of the title to be set for the frame
   * @param height int of the height of the Frame
   * @param width int of the width of the Frame
   */
  public void initialise(String title, int height, int width){
    setTitle(title);
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width - width)/2;
    int y = (screen.height - height)/2;
    this.setBounds(x, y, width, height);  
  }
  
  /**
   * Initialise sets the title of the app, but sets the window size to full screen.
   * @param title String of the title to be set for the frame
   */
  public void initialise(String title){
    setTitle(title);
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width)/2;
    int y = (screen.height)/2;
    this.setBounds(x, y, screen.width, screen.height);
  }

  /**
   * Hides the JFrameView and disposes of its resources
   */
  public void screenDispose(){
    setVisible(false);
    dispose();
  }


  /**
   * Register the view with the model
   */
  public void registerWithModel(){
    ((AbstractModel)model).addModelListener(this);
  }

  /**
   * Get the application Controller
   * @return the application Controller
   */
  public Controller getController(){
    return controller;
  }

  /**
   * Set the application Controller
   * @param controller the application Controller
   */
  public void setController(Controller controller){
    this.controller = controller;
  }

  /**
   * Get the application Model
   * @return the application Model
   */
  public Model getModel(){
    return model;
  }

  /**
   * Get the application Model
   * @param model the application Model
   */
  public void setModel(Model model){
    this.model = model;
    registerWithModel();
  }
  
  /* ***************************************************************************************
   * Message dialogs
   */
  
  /**
   * Show an Error Message
   * @param aMessage the message to be displayed
   */
  public void errorMessage(String aMessage){
    JOptionPane.showMessageDialog(this, aMessage, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Show an Information Message
   * @param aMessage the message to be displayed
   */
  public void infoMessage(String aMessage){
    JOptionPane.showMessageDialog(this, aMessage, "Information", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Show a Plain Message
   * @param aMessage the message to be displayed
   */
  public void plainMessage(String aMessage){
    JOptionPane.showMessageDialog(this, aMessage, "Information", JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Show a Warning Message
   * @param aMessage the message to be displayed
   */
  public void warningMessage(String aMessage){
    JOptionPane.showMessageDialog(this, aMessage, "Warning", JOptionPane.WARNING_MESSAGE);
  }

}
