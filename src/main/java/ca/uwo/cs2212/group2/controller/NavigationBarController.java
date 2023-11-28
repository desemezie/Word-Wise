package ca.uwo.cs2212.group2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

import ca.uwo.cs2212.group2.view.components.NavigationBar;

public class NavigationBarController {
  private NavigationBar view;
  // private JFileChooser fileChooser;
  private JTextArea textArea;
  private static String filePath;

  /**
   * Constructor for the navigation bar controller.
   *
   * @param view the navigation bar view
   * @param textArea the text area
   */
  public NavigationBarController(NavigationBar view, JTextArea textArea) {
    this.view = view;
    this.textArea = textArea;
    attachListeners();
    System.out.println("constructor"); 
  }

  /** Attaches listeners to the menu items. */
  private void attachListeners() {
    this.view.addFileMenuListener(createFileActionListener());
    this.view.addSettingsMenuListener(createSettingActionListener());
    this.view.addSpellCheckMenuListener(createSpellCheckActionListener());
    this.view.addMetricsMenuListener(createMetricsActionListener());
    this.view.addSaveMenuListener(createSaveActionListener());
    this.view.addHelpMenuListener(createHelpActionListener());
  }

  /**
   * Saves the file as a new file.
   *
   * @param textArea the text area
   */
  private static void saveAsFile(JTextArea textArea) {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      java.io.File selectedFile = fileChooser.getSelectedFile();
      try (FileWriter fileWriter = new FileWriter(selectedFile)) {
        fileWriter.write(textArea.getText());
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Opens a file.
   *
   * @param textArea the text area
   */
  private static void openFile(JTextArea textArea) {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      java.io.File selected = fileChooser.getSelectedFile();
      filePath = selected.getAbsolutePath();
      try (BufferedReader reader =
          new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          content.append(line).append("\n");
        }
        // Assuming 'textArea' is the JTextArea component
        textArea.setText(content.toString());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Creates an action listener for the file menu.
   *
   * @return the action listener
   */
  private ActionListener createFileActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        System.out.println("File menu item clicked: " + source.getText());
        if (source.getText().equals("Open")) {
          System.out.println("open a file");
          openFile(textArea);
        }
        if (source.getText().equals("New")) {
          System.out.println("new file");
          textArea.setText("");
        }
        if (source.getText().equals("Save As")) {
          saveAsFile(textArea);
        }
        if (source.getText().equals("Save")) {
          try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(textArea.getText());
            System.out.println("File saved successfully at: " + filePath);
          } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error saving the file.");
          }
        }
      }
    };
  }

  /**
   * Creates an action listener for the save menu.
   *
   * @return the action listener
   */
  private ActionListener createSaveActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JMenu source = (JMenu) e.getSource();
        System.out.println("Save menu clicked: " + source.getText());
        if (filePath != null) {
          try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(textArea.getText());
            System.out.println("File saved successfully at: " + filePath);
          } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error saving the file.");
          }
        } else {
          System.out.println("Please select a file before saving.");
        }
      }
    };
  }

  /**
   * Creates an action listener for the spell check menu.
   *
   * @return the action listener
   */
  private ActionListener createSpellCheckActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        System.out.println("SpellCheck menu clicked: " + source.getText());

        // Implement your logic for the spell check action
      }
    };
  }

  /**
   * Creates an action listener for the help menu.
   *
   * @return the action listener
   */
  private ActionListener createHelpActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        System.out.println("Help menu item clicked: " + source.getText());

        // Show help popup
      }
    };
  }

  /**
   * Creates an action listener for the metrics menu.
   *
   * @return the action listener
   */
  private ActionListener createMetricsActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        System.out.println("Metrics menu item clicked: " + source.getText());
        if (source.getText().equals("Number of Spelling Errors")) {
          // show spelling error popup
        }
        if (source.getText().equals("Number of Corrections")) {
          // show corrections popup
        }
        if (source.getText().equals("Metrics Related to Document")) {
          // show metrics popup
        }
      }
    };
  }

  /**
   * Creates an action listener for the settings menu.
   *
   * @return the action listener
   */
  private ActionListener createSettingActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        System.out.println("Settings menu item clicked: " + source.getText());

        // Implement your logic for the settings action
      }
    };
  }
}
