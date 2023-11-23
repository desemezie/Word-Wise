package ca.uwo.cs2212.group2;

import ca.uwo.cs2212.group2.view.pages.FinishedGUI;
import javax.swing.*;

/**
 * This class is the entry point for the application. It contains the main method that is executed
 * when the application is started. It is responsible for bootstrapping the application and
 * initializing and cleaning up resources.
 */
public class Main {
  public static void main(String[] args) {
    init();
    Runtime.getRuntime().addShutdownHook(new Thread(Main::destroy));
  }

  /** Lifecycle hook to perform initialization when the application is starting up. */
  private static void init() {
    // Load configurations
    // Set up logging
    // Initialize MVC components
    SwingUtilities.invokeLater(FinishedGUI::Create);
  }

  /** Lifecycle hook to perform cleanup when the application is shutting down. */
  private static void destroy() {
    // Perform any cleanup tasks
  }
}
