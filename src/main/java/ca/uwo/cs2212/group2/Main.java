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
 * This class is the entry point for the application. It contains the main
 * method that is executed
 * when the application is started. It is responsible for bootstrapping the
 * application and
 * initializing and cleaning up resources.
 */
public class Main {
  public static void main(String[] args) {
    init();
    Runtime.getRuntime().addShutdownHook(new Thread(Main::destroy));
  }

  /**
   * Lifecycle hook to perform initialization when the application is starting up.
   */
  private static void init() {
    // Load configurations
    // Set up logging
    // Initialize MVC components
    // JFrame masterFrame = new JFrame("Master Frame");
    // masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // masterFrame.setSize(APP_WIDTH, APP_HEIGHT); // Set the size as per your
    // requirement
    //
    // // Create an instance of MyGui
    // LandingMenu landingMenuPanel = new LandingMenu();
    //
    // // Add MyGui to the master frame
    // masterFrame.add(landingMenuPanel);
    // //
    // // // Display the frame
    // // masterFrame.setVisible(true);

    // SwingUtilities.invokeLater(
    // new Runnable() {
    // @Override
    // public void run() {
    // // new MyGui();
    // new SpellCheckerUI();
    // // new FinishedGUI();
    // }
    // });
    // }

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1594, 1030);

    JTextArea textArea = new JTextArea();

    NavigationBar nb = new NavigationBar(frame.getSize());
    NavigationBarController nbController = new NavigationBarController(nb, textArea);
    frame.setJMenuBar(nb);
    frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
    frame.setVisible(true);
  }

  /** Lifecycle hook to perform cleanup when the application is shutting down. */
  private static void destroy() {
    // Perform any cleanup tasks
  }
}
