package ca.uwo.cs2212.group2.view.components;

import javax.swing.*;

import java.awt.*;

public class UserDictPopup extends JDialog{
    private static final String MESSAGE_TEXT = "<html><font color = 'white'>User Dictionary: </font></html>";

     /**
   * Constructor for the corrections popup
   *
   * @param parentFrame the frame to display the popup in
   */
    public UserDictPopup(JFrame parentFrame) {
        super(parentFrame, "Dictionary", true); // 'true' for modal

        // Set up the content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(0x993399));
        contentPanel.setPreferredSize(new Dimension(400, 600));
        //contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Set up the label with the help message
        JLabel dictLabel = new JLabel(MESSAGE_TEXT);
        dictLabel.setFont(
            new Font(dictLabel.getFont().getName(), dictLabel.getFont().getStyle(), 16));
        dictLabel.setPreferredSize(new Dimension(400, 600));
        dictLabel.setVerticalAlignment(SwingConstants.NORTH);
        contentPanel.add(dictLabel);
    
        //Add content panel to the dialog
        this.add(contentPanel);
        this.pack();
        this.setLocationRelativeTo(parentFrame); // Center relative to the parent frame

    }
    

     /**
   * Method to display the popup
   *
   * @param parentFrame the frame to display the popup in
   */

    public static void showUserDict(JFrame parentFrame) {
    UserDictPopup pop = new UserDictPopup(parentFrame);
    pop.setVisible(true);
  }
}

 
  
    

