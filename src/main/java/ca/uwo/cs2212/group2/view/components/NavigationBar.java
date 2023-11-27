package ca.uwo.cs2212.group2.view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static ca.uwo.cs2212.group2.constants.ViewConstants.*;

public class NavigationBar extends JMenuBar {
  private static final String[] FILE_ITEMS = { "Open", "Save", "Save As", "New" };
  private static final String[] SETTING_ITEMS = {
      "View User Dictionary", "Exit Checker", "Add Word To Dictionary"
  };
  private static final String[] METRIC_ITEMS = {
      "Number of Spelling Errors", "Number of Corrections", "Metrics Related to Document"
  };
  private static final String[] HELP_ITEMS = { "More Stuff" };
  private static final int MENU_BAR_HEIGHT = 89;

  public NavigationBar(
      Dimension frameSize) {
    setPreferredSize(new Dimension(frameSize.width, MENU_BAR_HEIGHT));
    setBackground(PRIMARY_COLOUR);
    setLayout(new GridLayout(1, 6, 0, 0)); // 6 items, no gaps

    add(createMenu("File", FILE_ITEMS));
    add(createMenu("Settings", SETTING_ITEMS));
    add(createMenu("Spell Check"));
    add(createMenu("Metrics", METRIC_ITEMS));
    add(createMenu("Save"));
    add(createMenu("Help", HELP_ITEMS));
    //addActionListeners(null,null,null,null,null,null);

  }

  public NavigationBar(
      Dimension frameSize,
      ActionListener fileActionListener,
      ActionListener settingActionListener,
      ActionListener spellCheckActionListener,
      ActionListener metricsActionListener,
      ActionListener saveActionListener,
      ActionListener helpActionListener) {
    setPreferredSize(new Dimension(frameSize.width, MENU_BAR_HEIGHT));
    setBackground(PRIMARY_COLOUR);
    setLayout(new GridLayout(1, 6, 0, 0)); // 6 items, no gaps

    add(createMenu("File", FILE_ITEMS, fileActionListener));
    add(createMenu("Settings", SETTING_ITEMS, settingActionListener));
    add(createMenu("Spell Check", spellCheckActionListener));
    add(createMenu("Metrics", METRIC_ITEMS, metricsActionListener));
    add(createMenu("Save", saveActionListener));
    add(createMenu("Help", HELP_ITEMS, helpActionListener));
    //addActionListeners(fileActionListener,settingActionListener,spellCheckActionListener,metricsActionListener,saveActionListener,helpActionListener);
  }

  private static JMenu createMenu(String title) {
    JMenu menuItem = new JMenu(title);
    menuItem.setHorizontalAlignment(SwingConstants.CENTER);
    menuItem.setForeground(Color.WHITE); // This sets the text color to white
    menuItem.setBackground(PRIMARY_COLOUR);
    menuItem.setFont(MENU_FONT);
    menuItem.setOpaque(true); // This is necessary for the background and foreground colors to show
    menuItem.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding around the text

    return menuItem;
  }

  private static JMenu createMenu(String title, String[] items) {
    JMenu menu = new JMenu(title);
    menu.setHorizontalAlignment(SwingConstants.CENTER);
    menu.setForeground(Color.WHITE); // This sets the text color to white
    menu.setBackground(PRIMARY_COLOUR);
    menu.setFont(MENU_FONT);
    menu.setOpaque(true); // This is necessary for the background and foreground colors to show
    menu.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding around the text
    for (int i = 0; i < items.length; i++) {
      JMenuItem item = new JMenuItem(items[i]);
      menu.add(item);
    }
    return menu;
  }

  private static JMenu createMenu(String title, ActionListener action) {
    JMenu menuItem = new JMenu(title);
    menuItem.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            // Call the actionPerformed method of the provided ActionListener
            action.actionPerformed(new ActionEvent(menuItem, ActionEvent.ACTION_PERFORMED, title));
          }
        });
    menuItem.setHorizontalAlignment(SwingConstants.CENTER);
    menuItem.setForeground(Color.WHITE); // This sets the text color to white
    menuItem.setBackground(PRIMARY_COLOUR);
    menuItem.setFont(MENU_FONT);
    menuItem.setOpaque(true); // This is necessary for the background and foreground colors to show
    menuItem.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding around the text

    return menuItem;
  }

  private static JMenu createMenu(String title, String[] items, ActionListener action) {
    JMenu menu = new JMenu(title);
    menu.setHorizontalAlignment(SwingConstants.CENTER);
    menu.setForeground(Color.WHITE); // This sets the text color to white
    menu.setBackground(PRIMARY_COLOUR);
    menu.setFont(MENU_FONT);
    menu.setOpaque(true); // This is necessary for the background and foreground colors to show
    menu.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding around the text
    for (int i = 0; i < items.length; i++) {
      JMenuItem item = new JMenuItem(items[i]);
      item.addActionListener(action);
      menu.add(item);
    }
    return menu;
  }

  

  

  public JMenu getFileMenu() {
    // Assuming that "File" is the first menu added to the NavigationBar
    return (JMenu) getComponent(0);
  }

  public JMenu getSettingsMenu() {
    // Assuming that "Settings" is the second menu added to the NavigationBar
    return (JMenu) getComponent(1);
  }

  public JMenu getSpellCheckMenu() {
    // Assuming that "Spell Check" is the third menu added to the NavigationBar
    return (JMenu) getComponent(2);
  }

  public JMenu getMetricsMenu() {
    // Assuming that "Metrics" is the fourth menu added to the NavigationBar
    return (JMenu) getComponent(3);
  }

  public JMenu getSaveMenu() {
    // Assuming that "Save" is the fifth menu added to the NavigationBar
    return (JMenu) getComponent(4);
  }

  public JMenu getHelpMenu() {
    // Assuming that "Help" is the sixth menu added to the NavigationBar
    return (JMenu) getComponent(5);
  }

  // public static void main(String args[]) {
  //   JFrame frame = new JFrame();
  //   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //   frame.setSize(1594, 1030);
  //   Dimension frameSize = frame.getSize();
  //   NavigationBar nb = new NavigationBar(frameSize, null, null, null, null, null,
  //       null);
  //   frame.add(nb);
  //   frame.setVisible(true);

  // }
}
