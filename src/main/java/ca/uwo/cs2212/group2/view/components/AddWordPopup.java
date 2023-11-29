package ca.uwo.cs2212.group2.view.components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWordPopup extends JDialog {

    private static final String MESSAGE_TEXT = "<html><font color = 'white'>Enter a word to add to your dictionary:</font></html>";

    public AddWordPopup(){
        // Set up the content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(0x993399));
        contentPanel.setPreferredSize(new Dimension(400, 350));
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        

        // Set up the label with the help message
        JLabel helpLabel = new JLabel(MESSAGE_TEXT);
        helpLabel.setPreferredSize(new Dimension(250, 250));
        contentPanel.add(helpLabel);


        // Add text field
        JTextField wordTextField = new JTextField();
        wordTextField.setPreferredSize(new Dimension(250, 30));
        contentPanel.add(wordTextField);

        // Add submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your logic here to handle the submitted word
                String word = wordTextField.getText();
                System.out.println("Submitted word: " + word);
                // You can close the dialog if needed
                dispose();
            }
        });
        contentPanel.add(submitButton);


        // Add content panel to the dialog
        this.add(contentPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the dialog
    }

    public static void showAddWordDialog() {
        AddWordPopup popup = new AddWordPopup();
        popup.setVisible(true);
      }
    
}
