package ca.uwo.cs2212.group2.view.components;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static ca.uwo.cs2212.group2.constants.ViewConstants.*;

public class NavigationBar extends JMenuBar {
  private static final String[] FILE_ITEMS = {"Open", "Save", "Save As", "New"};
  private static final String[] SETTING_ITEMS = {
    "View User Dictionary", "Exit Checker", "Add Word To Dictionary"
  };
  private static final String[] METRIC_ITEMS = {
    "Number of Spelling Errors", "Number of Corrections", "Metrics Related to Document"
  };
  private static final String[] HELP_ITEMS = {"More Stuff"};

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
}
