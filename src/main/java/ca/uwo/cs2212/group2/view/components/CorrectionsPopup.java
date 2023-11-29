package ca.uwo.cs2212.group2.view.components;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectionsPopup extends JDialog {

    private static final String MESSAGE_TEXT = "<html><font color = 'white'>Number of Corrections: 0</font></html>";

    /**
   * Constructor for the corrections popup
   *
   * @param parentFrame the frame to display the popup in
   */
    
        public CorrectionsPopup(JFrame parentFrame){
            super(parentFrame, "Corrections", true); // 'true' for modal
    
            // Set up the content panel
            JPanel contentPanel = new JPanel();
            contentPanel.setBackground(new Color(0x993399));
            contentPanel.setPreferredSize(new Dimension(400, 350));
            contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    
            // Set up the label with the help message
            JLabel helpLabel = new JLabel(MESSAGE_TEXT);
            helpLabel.setPreferredSize(new Dimension(250, 250));
            contentPanel.add(helpLabel);
    
            // Add content panel to the dialog
            this.add(contentPanel);
            this.pack();
            this.setLocationRelativeTo(parentFrame); // Center relative to the parent frame
        }


        public CorrectionsPopup(){
            // Set up the content panel
            JPanel contentPanel = new JPanel();
            contentPanel.setBackground(new Color(0x993399));
            contentPanel.setPreferredSize(new Dimension(400, 350));
            contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    
            // Set up the label with the help message
            JLabel helpLabel = new JLabel(MESSAGE_TEXT);
            helpLabel.setPreferredSize(new Dimension(250, 250));
            contentPanel.add(helpLabel);
    
            // Add content panel to the dialog
            this.add(contentPanel);
            this.pack();
        }
    
    
        /**
       * Method to display the popup
       *
       * @param parentFrame the frame to display the popup in
       */
        public static void showCorrectionsDialog(JFrame parentFrame) {
          CorrectionsPopup popup = new CorrectionsPopup(parentFrame);
          popup.setVisible(true);
        }

        public static void showCorrectionsDialog() {
          CorrectionsPopup popup = new CorrectionsPopup();
          popup.setVisible(true);
        }
        
    
}
