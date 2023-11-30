package ca.uwo.cs2212.group2.controller;

import ca.uwo.cs2212.group2.view.components.*;
import org.w3c.dom.Text;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.*;

public class NavigationBarController {
  private NavigationBar view;
  // private JFileChooser fileChooser;
  private TextEditor textEditor;
  private JTextPane textPane;
  private static String filePath;
  private static Boolean isSaved = false;

  /**
   * Constructor for the navigation bar controller.
   *
   * @param view the navigation bar view
   * @param textEditor the text area
   */
  public NavigationBarController(NavigationBar view, TextEditor textEditor) {
    this.view = view;
    this.textEditor = textEditor;
    this.textPane = textEditor.getTextPane();
    attachListeners();
    System.out.println("constructor");
  }

  /** Attaches listeners to the menu items. */
  private void attachListeners() {
    this.view.addFileMenuListener(createFileActionListener());
    this.view.addSettingsMenuListener(createSettingActionListener());
    this.view.addSpellCheckMenuMouseListener(createSpellCheckActionListener());
    this.view.addMetricsMenuListener(createMetricsActionListener());
    this.view.addSaveMenuListener(createSaveActionListener());
    this.view.addHelpMenuListener(createHelpActionListener());
  }

  /**
   * Saves the file as a new file.
   *
   * @param textPane the text area
   */
  private static void saveAsFile(JTextPane textPane) {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      java.io.File selectedFile = fileChooser.getSelectedFile();
      try (FileWriter fileWriter = new FileWriter(selectedFile)) {
        fileWriter.write(textPane.getText());
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Opens a file.
   *
   * @param textPane the text area
   */
  private static void openFile(JTextPane textPane) {
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
        // Assuming 'textPane' is the JtextPane component
        textPane.setText(content.toString());
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
          openFile(textPane);
        }
        if (source.getText().equals("New")) {
          System.out.println("new file");
          textPane.setText("");
        }
        if (source.getText().equals("Save As")) {
          saveAsFile(textPane);
          isSaved = true;
        }
        if (source.getText().equals("Save")) {
          try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(textPane.getText());
            isSaved = true;
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
            fileWriter.write(textPane.getText());
            System.out.println("File saved successfully at: " + filePath);
            isSaved = true;
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
   * Creates a mouse listener for the spell check menu.
   *
   * @return the mouse listener
   */
  private MouseListener createSpellCheckActionListener() {
    return new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        textEditor.spellCheckClicked();
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
        HelpPopup help = new HelpPopup();
        help.showHelpDialog();

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
          SpellingErrorsPopup spellpopup = new SpellingErrorsPopup();
          spellpopup.showSpellingErrorsDialog();
        }
        if (source.getText().equals("Number of Corrections")) {
          CorrectionsPopup popup = new CorrectionsPopup();
          popup.showCorrectionsDialog();
          // show corrections popup
        }
        if (source.getText().equals("Metrics Related to Document")) {
          // show metrics popup
          MetricsPopup metricspop = new MetricsPopup();
          metricspop.showMetricsDialog();
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
        if (source.getText().equals("View User Dictionary")) {
          UserDictPopup dictpopup = new UserDictPopup();
          dictpopup.showUserDict();
        } else if (source.getText().equals("Exit Checker")) {
          // exit the checker
        } else if (source.getText().equals("Add Word To Dictionary")) {
          // add word to user dict
          AddWordPopup word = new AddWordPopup();
          word.showAddWordDialog();
        }
      }
    };
  }
}
