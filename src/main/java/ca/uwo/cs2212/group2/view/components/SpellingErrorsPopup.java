package ca.uwo.cs2212.group2.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ca.uwo.cs2212.group2.constants.ViewConstants.PRIMARY_COLOUR;

public class SpellingErrorsPopup extends JDialog{

    private static final String MESSAGE_TEXT = "<html><font color = 'white'>Number of Spelling Errors: 0</font></html> ";

    /**
   * Constructor for the spelling errors popup
   *
   * @param parentFrame the frame to display the popup in
   */
    public SpellingErrorsPopup(JFrame parentFrame){
        super(parentFrame, "Spelling Errors", true); // 'true' for modal

        // Set up the content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(0x993399));
        contentPanel.setPreferredSize(new Dimension(400, 350));
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Set up the label with the help message
        JLabel helpLabel = new JLabel(MESSAGE_TEXT);
        helpLabel.setPreferredSize(new Dimension(250, 250));
        contentPanel.add(helpLabel);

        // Set up the close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            SpellingErrorsPopup.this.dispose(); // Closes the popup
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
    public static void showSpellingErrorsDialog(JFrame parentFrame) {
            SpellingErrorsPopup popup = new SpellingErrorsPopup(parentFrame);
            popup.setVisible(true);
    }

}
