package ca.uwo.cs2212.group2.view.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static ca.uwo.cs2212.group2.constants.ViewConstants.*;

public class LandingMenu extends JPanel {
  private JButton newFileButton, uploadFileButton, button, button2;
  private JRadioButton engUS, engUK, engCa;
  private ButtonGroup languageGroup;
  private JFileChooser fileChooser;

  /** Constructor for the landing menu. */
  public LandingMenu() {
    initializeComponents();
    layoutComponents();
  }

  /** Initializes the components of the landing menu. */
  private void initializeComponents() {
    newFileButton = createButton("New File", 150, 100, 400, 300);
    uploadFileButton = createButton("Upload File", 880, 100, 400, 300);

    applyButtonStyles(newFileButton);
    applyButtonStyles(uploadFileButton);

    engUS = createRadioButton("English (United States)", 597, 500);
    engUK = createRadioButton("English (United Kingdom)", 597, 550);
    engCa = createRadioButton("English (Canada)", 597, 600);

    languageGroup = new ButtonGroup();
    languageGroup.add(engUS);
    languageGroup.add(engUK);
    languageGroup.add(engCa);

    fileChooser = new JFileChooser();
  }

  private JButton createButton(String text, int x, int y, int width, int height) {
    JButton button = new JButton(text);
    button.setBounds(x, y, width, height);
    return button;
  }

  private void applyButtonStyles(JButton button) {
    Font newButtonFont = new Font(button.getFont().getName(), button.getFont().getStyle(), 20);
    button.setFont(newButtonFont);
    button.setFocusable(false);
    button.setBorderPainted(false);
    button.setOpaque(true);
    button.setForeground(new Color(0xffffff));
    button.setBackground(new Color(PRIMARY_COLOUR.getRGB()));
  }

  private JRadioButton createRadioButton(String text, int x, int y) {
    JRadioButton radioButton = new JRadioButton(text);
    radioButton.setBounds(x, y, 200, 30);
    return radioButton;
  }

  /** Lays out the components on the panel */
  private void layoutComponents() {
    setLayout(null);
    setBackground(new Color(MENU_BACKGROUND_COLOUR.getRGB()));
    add(newFileButton);
    add(uploadFileButton);
    add(engUS);
    add(engUK);
    add(engCa);
    setBounds(0, 0, APP_WIDTH, APP_HEIGHT);
  }

  public void addNewFileButtonListener(ActionListener listener) {
    newFileButton.addActionListener(listener);
  }

  public void addUploadFileButtonListener(ActionListener listener) {
    uploadFileButton.addActionListener(listener);
  }

  //  @Override
  //  public void actionPerformed(ActionEvent e) {
  //
  //    if (e.getSource() == button) {
  //      // do something when create file is clicked
  //      System.out.println("create file");
  //    } else if (e.getSource() == button2) {
  //      // do something when upload file is clicked
  //      int returnValue = fileChooser.showOpenDialog(null);
  //      if (returnValue == JFileChooser.APPROVE_OPTION) {
  //        File selectedFile = fileChooser.getSelectedFile();
  //        System.out.println(selectedFile.getPath());
  //      }
  //    } else if (e.getSource() == engUS) {
  //      System.out.println("us");
  //    } else if (e.getSource() == engUK) {
  //      System.out.println("uk");
  //    } else if (e.getSource() == engCa) {
  //      System.out.println("OH CANADA");
  //    }
  //  }
}
