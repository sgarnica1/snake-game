
/**
 * File: App.java
 * Author: Sergio Garnica González
 * Date: 26/10/2023
 * Version: 1.0
 */

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * @brief Main class
 * @file App.java
 * @version 1.0
 */
public class App extends JFrame {
  private static final long serialVersionUID = 1035207996697568561L;

  /**
   * @brief Constructor
   * @return void
   */
  public App() {
    initUI();
  }

  /**
   * @brief Init user interface
   * @return void
   */
  private void initUI() {
    add(new Board());
    setTitle("SnakeGame");
    pack(); // Adjust the size of the window to the size of its components
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  /**
   * @brief Main function
   * @file App.java
   * @param args
   */
  public static void main(String args[]) {
    EventQueue.invokeLater(() -> {
      App app = new App();
      app.setVisible(true);
    });
  }
}
