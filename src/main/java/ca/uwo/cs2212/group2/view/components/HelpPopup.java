package ca.uwo.cs2212.group2.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ca.uwo.cs2212.group2.constants.ViewConstants.MENU_BACKGROUND_COLOUR;

public class HelpPopup extends JDialog {

  private static final String MESSAGE_TEXT =
      "<html><font color = 'white'>Welcome to our spellchecker! <br><br>"
          + "We're so happy you're here. <br><br>"
          + "There are 5 major tabs for navigating through the application: <br><br>"
          + "Settings, File, Spell Check, Metrics, Save. <br><br>"
          + "Feel free to look around!!</font></html>";

  /**
   * Constructor for the help popup
   *
   * @param parentFrame the frame to display the popup in
   */
  public HelpPopup(JFrame parentFrame) {
    super(parentFrame, "Help", true); // 'true' for modal

    // Set up the content panel
    JPanel contentPanel = new JPanel();
    contentPanel.setBackground(new Color(0x993399));
    contentPanel.setPreferredSize(new Dimension(375, 200));
    contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    // Set up the label with the help message
    JLabel helpLabel = new JLabel(MESSAGE_TEXT);
    helpLabel.setPreferredSize(new Dimension(200, 150));
    contentPanel.add(helpLabel);

    // Set up the close button
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            HelpPopup.this.dispose(); // Closes the popup
          }
        });
    contentPanel.add(closeButton);

    // Add content panel to the dialog
    this.add(contentPanel);
    this.pack();
    this.setLocationRelativeTo(parentFrame); // Center relative to the parent frame
  }

  /**
   * Method to display the popup
   *
   * @param parentFrame the frame to display the popup in
   */
  public static void showHelpDialog(JFrame parentFrame) {
    HelpPopup helpPopup = new HelpPopup(parentFrame);
    helpPopup.setVisible(true);
  }

  
  
}
