package ca.uwo.cs2212.group2.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ca.uwo.cs2212.group2.constants.ViewConstants.POPUP_HEIGHT;
import static ca.uwo.cs2212.group2.constants.ViewConstants.POPUP_WIDTH;

public class WelcomePopup extends JDialog {

  private JButton closeButton;
  private JLabel messageLabel;

  private static final String MESSAGE_TEXT =
      "<html><div style='text-align: center;'>"
          + "Welcome to our Spellchecker "
          + "application! To begin, you can choose to upload an existing .txt or .html file or create your own here."
          + "</div></html>";

  /**
   * Constructor for the help popup
   *
   * @param parentFrame the frame to display the popup in
   */
  public WelcomePopup(JFrame parentFrame) {
    super(parentFrame, "Welcome", true); // 'true' for modal

    // Set up the label
    messageLabel = new JLabel(MESSAGE_TEXT);
    messageLabel.setFont(
        new Font(messageLabel.getFont().getName(), messageLabel.getFont().getStyle(), 20));
    messageLabel.setForeground(Color.WHITE);
    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Set up the close button
    closeButton = new JButton("Begin!");
    closeButton.setFont(
        new Font(closeButton.getFont().getName(), closeButton.getFont().getStyle(), 20));
    closeButton.setPreferredSize(new Dimension(150, 75));
    closeButton.setForeground(new Color(0x993399));
    closeButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            WelcomePopup.this.dispose(); // Closes the popup
          }
        });

    // Set up the panel
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(messageLabel, BorderLayout.CENTER);
    panel.add(closeButton, BorderLayout.SOUTH);
    panel.setBackground(new Color(0x993399));

    this.add(panel);
    this.setSize(POPUP_WIDTH, POPUP_HEIGHT);
    this.setLocationRelativeTo(parentFrame); // Center relative to the parent frame
  }

  /**
   * Method to display the popup
   *
   * @param parentFrame the frame to display the popup in
   */
  public static void showWelcomeDialog(JFrame parentFrame) {
    WelcomePopup welcomePopup = new WelcomePopup(parentFrame);
    welcomePopup.setVisible(true);
  }
}
