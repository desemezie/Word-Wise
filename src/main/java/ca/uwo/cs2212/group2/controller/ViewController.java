package ca.uwo.cs2212.group2.controller;

import javax.swing.*;
import java.awt.CardLayout;

public class ViewController {

  private JFrame frame;
  private CardLayout cardLayout;
  private JPanel cardPanel;
  private JMenuBar menuBar;

  /**
   * Constructor for the ViewController
   *
   * @param frame the frame to display the panels in
   */
  public ViewController(JFrame frame) {
    this.frame = frame;
    this.cardLayout = new CardLayout();
    this.cardPanel = new JPanel(cardLayout);
    frame.add(cardPanel);
  }

  /**
   * Method to show a panel
   *
   * @param name the name of the panel to show
   */
  public void showPanel(String name) {
    if ("MainPanel".equals(name)) {
      showMenuBar();
    } else {
      hideMenuBar();
    }

    cardLayout.show(cardPanel, name);
    frame.pack();
    frame.setLocationRelativeTo(null); // Center the frame
  }

  private void showMenuBar() {
    frame.setJMenuBar(menuBar);
  }

  private void hideMenuBar() {
    frame.setJMenuBar(null);
  }

  public void addPanel(JPanel view, String name) {
    cardPanel.add(view, name);
  }

  public void setMenuBar(JMenuBar menuBar) {
    this.menuBar = menuBar;
  }
}
