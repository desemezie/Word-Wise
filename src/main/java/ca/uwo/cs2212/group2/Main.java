package ca.uwo.cs2212.group2;

import static ca.uwo.cs2212.group2.constants.ViewConstants.*;

import ca.uwo.cs2212.group2.controller.LandingMenuController;
import ca.uwo.cs2212.group2.controller.NavigationBarController;
import ca.uwo.cs2212.group2.controller.ViewController;
import ca.uwo.cs2212.group2.view.components.NavigationBar;
import ca.uwo.cs2212.group2.view.components.TextEditor;
import ca.uwo.cs2212.group2.view.pages.LandingMenu;
import java.awt.*;
import javax.swing.*;

/**
 * This class is the entry point for the application. It contains the main method that is executed
 * when the application is started. It is responsible for bootstrapping the application and
 * initializing and cleaning up resources.
 */
public class Main {
  private static final Dimension APP_DIMENSIONS = new Dimension(APP_WIDTH, APP_HEIGHT);

  public static void main(String[] args) {
    init();
    Runtime.getRuntime().addShutdownHook(new Thread(Main::destroy));
  }

  /** Lifecycle hook to perform initialization when the application is starting up. */
  private static void init() {
    // Load configurations
    // Set up logging
    // Initialize MVC components

    // Initialize the look and feel
    initLookAndFeel();

    SwingUtilities.invokeLater(
        () -> {
          JFrame frame = new JFrame("Navigation Bar Example");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          ViewController viewController = new ViewController(frame);

          LandingMenu landingMenu = new LandingMenu();
          LandingMenuController landingMenuController =
              new LandingMenuController(landingMenu, viewController);
          NavigationBar navigationBar = new NavigationBar(APP_DIMENSIONS);
          NavigationBarController navigationBarController =
              new NavigationBarController(navigationBar, new JTextArea());
          TextEditor textEditor = new TextEditor(APP_DIMENSIONS);

          viewController.addPanel(landingMenu, "LandingMenu");
          viewController.addPanel(textEditor, "MainPanel");

          viewController.showPanel("LandingMenu");
          viewController.setMenuBar(navigationBar);
          frame.setSize(APP_WIDTH, APP_HEIGHT);
          frame.setLocationRelativeTo(null);
          frame.setVisible(true);
        });
  }

  /** Lifecycle hook to perform cleanup when the application is shutting down. */
  private static void destroy() {
    // Perform any cleanup tasks
  }

  /**
   * Initializes the look and feel of the application. This method will attempt to use the Nimbus
   */
  private static void initLookAndFeel() {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception e) {
      // If Nimbus is not available, fall back to the default L&F.
      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (Exception ex) {
        System.err.println("Failed to initialize the default look and feel.");
      }
    }
  }
}
