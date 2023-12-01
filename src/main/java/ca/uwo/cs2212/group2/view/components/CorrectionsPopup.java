package ca.uwo.cs2212.group2.view.components;

import javax.swing.*;

import ca.uwo.cs2212.group2.model.Speller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ca.uwo.cs2212.group2.service.SessionCorrectionStatisticsService;

public class CorrectionsPopup extends JDialog {

    
    int manualCorrrections = SessionCorrectionStatisticsService.getInstance().getNumberOfManualCorrections();
    int acceptedSuggestions = SessionCorrectionStatisticsService.getInstance().getNumberOfSuggestionCorrections();
    int wordDeletions = SessionCorrectionStatisticsService.getInstance().getNumberOfDeletionCorrections();

    private  final String MESSAGE_TEXT = "<html>" +
    "<head>" +
    "<style>" +
    "body { font-family: Arial, sans-serif; }" +
    ".header { font-size: 20px; color: #FFFFFF; }" +
    ".metric { font-size: 16px; color: #FFFFFF; }" +
    "</style>" +
    "</head>" +
    "<body>" +
    "<div class='header'>Number of Corrections:</div>" +
    "<div class='metric'>Manual Correction: " + manualCorrrections + "</div>" +
    "<div class='metric'>Accepted suggestions: " + acceptedSuggestions + "</div>" +
    "<div class='metric'>Word deletion: " + wordDeletions + "</div>" +
    "</body>" ;  

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
