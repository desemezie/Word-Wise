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

  /**
   * Creates a button with the given text, x, y, width, and height.
   *
   * @param text the text of the button
   * @param x the x coordinate of the button
   * @param y the y coordinate of the button
   * @param width the width of the button
   * @param height the height of the button
   * @return the button
   */
  private JButton createButton(String text, int x, int y, int width, int height) {
    JButton button = new JButton(text);
    button.setBounds(x, y, width, height);
    return button;
  }

  /**
   * Applies the button styles to the given button.
   *
   * @param button the button to apply the styles to
   */
  private void applyButtonStyles(JButton button) {
    Font newButtonFont = new Font(button.getFont().getName(), button.getFont().getStyle(), 20);
    button.setFont(newButtonFont);
    button.setFocusable(false);
    button.setBorderPainted(false);
    button.setOpaque(true);
    button.setForeground(new Color(0xffffff));
    button.setBackground(new Color(PRIMARY_COLOUR.getRGB()));
  }

  /**
   * Creates a radio button with the given text, x, and y coordinates.
   *
   * @param text the text of the radio button
   * @param x the x coordinate of the radio button
   * @param y the y coordinate of the radio button
   * @return the radio button
   */
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
}
