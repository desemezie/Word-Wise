package ca.uwo.cs2212.group2;

import ca.uwo.cs2212.group2.controller.NavigationBarController;
import ca.uwo.cs2212.group2.view.components.NavigationBar;
import ca.uwo.cs2212.group2.view.pages.LandingMenu;
import ca.uwo.cs2212.group2.view.pages.SpellCheckerUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ca.uwo.cs2212.group2.constants.ViewConstants.APP_HEIGHT;
import static ca.uwo.cs2212.group2.constants.ViewConstants.APP_WIDTH;

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

    // Be very careful here this code is for making sure the text is white
    // don't ask me how it works i used chatgtp
    // i might need to look for a simpler way to do it
    // this is where the code for making the text white ends

    SwingUtilities.invokeLater(
        () -> {
          JFrame frame = new JFrame("Navigation Bar Example");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          NavigationBar navigationBar = new NavigationBar(new Dimension(800, 600));

          frame.setJMenuBar(navigationBar);
          frame.setSize(800, 600);
          frame.setLocationRelativeTo(null);
          frame.setVisible(true);
        });
  }

  /** Lifecycle hook to perform cleanup when the application is shutting down. */
  private static void destroy() {
    // Perform any cleanup tasks
  }
}
