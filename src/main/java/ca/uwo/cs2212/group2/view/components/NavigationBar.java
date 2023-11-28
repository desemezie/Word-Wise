package ca.uwo.cs2212.group2.view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static ca.uwo.cs2212.group2.constants.ViewConstants.*;

/** This class represents the navigation bar at the top of the application. */
public class NavigationBar extends JMenuBar {
  private static final String[] FILE_ITEMS = {"Open", "Save", "Save As", "New"};
  private static final String[] SETTING_ITEMS = {
    "View User Dictionary", "Exit Checker", "Add Word To Dictionary"
  };
  private static final String[] METRIC_ITEMS = {
    "Number of Spelling Errors", "Number of Corrections", "Metrics Related to Document"
  };
  private static final String[] HELP_ITEMS = {"More Stuff"};
  private static final int MENU_BAR_HEIGHT = 89;

  // Instance variables for each menu
  private JMenu fileMenu;
  private JMenu settingsMenu;
  private JMenu spellCheckMenu;
  private JMenu metricsMenu;
  private JMenu saveMenu;
  private JMenu helpMenu;

  /**
   * Constructor for the navigation bar.
   *
   * @param frameSize the size of the frame
   */
  public NavigationBar(Dimension frameSize) {
    setPreferredSize(new Dimension(frameSize.width, MENU_BAR_HEIGHT));
    setLayout(new GridLayout(1, 6, 0, 0)); // 6 items, no gaps

    // Initialize menus with action listeners set to null.
    // The controller will set appropriate listeners.
    fileMenu = createMenu("File", FILE_ITEMS, null);
    settingsMenu = createMenu("Settings", SETTING_ITEMS, null);
    spellCheckMenu = createMenu("Spell Check", new String[] {}, null);
    metricsMenu = createMenu("Metrics", METRIC_ITEMS, null);
    saveMenu = createMenu("Save", new String[] {}, null);
    helpMenu = createMenu("Help", HELP_ITEMS, null);

    add(fileMenu);
    add(settingsMenu);
    add(spellCheckMenu);
    add(metricsMenu);
    add(saveMenu);
    add(helpMenu);
  }

  /**
   * Creates a menu with the given title, items, and listener.
   *
   * @param title the title of the menu
   * @param items the items in the dropdown menu
   * @param listener the listener for the menu
   * @return the menu
   */
  private JMenu createMenu(String title, String[] items, ActionListener listener) {
    JMenu menu = new JMenu(title);
    menu.setHorizontalAlignment(SwingConstants.CENTER);
    menu.setBackground(PRIMARY_COLOUR);
    menu.setFont(MENU_FONT);
    menu.setOpaque(true); // Necessary for the background and foreground colors to show
    menu.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding around the text

    for (String item : items) {
      JMenuItem menuItem = new JMenuItem(item);
      menuItem.setOpaque(true);
      menuItem.setForeground(PRIMARY_COLOUR); // Set the foreground color to PRIMARY_COLOR
      if (listener != null) {
        menuItem.addActionListener(listener);
      }
      menu.add(menuItem);
    }

    return menu;
  }

  // Getters for each menu
  public JMenu getFileMenu() {
    return fileMenu;
  }

  public JMenu getSettingsMenu() {
    return settingsMenu;
  }

  public JMenu getSpellCheckMenu() {
    return spellCheckMenu;
  }

  public JMenu getMetricsMenu() {
    return metricsMenu;
  }

  public JMenu getSaveMenu() {
    return saveMenu;
  }

  public JMenu getHelpMenu() {
    return helpMenu;
  }
}
