package ca.uwo.cs2212.group2.view.components;

import ca.uwo.cs2212.group2.model.Word;
import ca.uwo.cs2212.group2.service.WordsToIgnoreOnceService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuggestionsPopup extends JDialog {

  public interface SuggestionSelectedCallback {
    void onSuggestionSelected(String suggestion);
  }

  private JButton suggestion1Button;
  private JButton suggestion2Button;
  private JButton suggestion3Button;
  private JButton suggestion4Button;
  private SuggestionSelectedCallback callback;

  private Word currentWord; // The word being processed

  public SuggestionsPopup(
      Word word,
      String sugg1,
      String sugg2,
      String sugg3,
      String sugg4,
      SuggestionSelectedCallback callback) {

    this.currentWord = word;

    // Set up the content panel
    JPanel contentPanel = new JPanel();
    contentPanel.setBackground(new Color(0x993399));
    contentPanel.setPreferredSize(new Dimension(400, 350));
    // contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    contentPanel.setLayout(new GridLayout(0, 1, 10, 10)); // GridLayout with 2 columns

    // Add buttons
    suggestion1Button = new JButton(sugg1);
    suggestion2Button = new JButton(sugg2);
    suggestion3Button = new JButton(sugg3);
    suggestion4Button = new JButton(sugg4);
    JButton addToDictionaryButton = new JButton("Add word to dictionary");
    JButton ignoreOnceButton = new JButton("Ignore once");
    JButton ignoreAlwaysButton = new JButton("Ignore always");
    JButton manualCorrectionButton = new JButton("Manual correction");

    // Add action listeners to buttons
    suggestion1Button.addActionListener(e -> callback.onSuggestionSelected(sugg1));
    suggestion2Button.addActionListener(e -> callback.onSuggestionSelected(sugg2));
    suggestion3Button.addActionListener(e -> callback.onSuggestionSelected(sugg3));
    suggestion4Button.addActionListener(e -> callback.onSuggestionSelected(sugg4));
    addToDictionaryButton.addActionListener(new ButtonClickListener());
    ignoreOnceButton.addActionListener(
        event -> {
          if (currentWord != null) {
            WordsToIgnoreOnceService.getInstance().ignoreWordOnce(currentWord);
            this.dispose();
          }
        });
    ignoreAlwaysButton.addActionListener(new ButtonClickListener());
    manualCorrectionButton.addActionListener(new ButtonClickListener());

    // Add buttons to the content panel
    contentPanel.add(suggestion1Button);
    contentPanel.add(suggestion2Button);
    contentPanel.add(suggestion3Button);
    contentPanel.add(suggestion4Button);
    contentPanel.add(addToDictionaryButton);
    contentPanel.add(ignoreOnceButton);
    contentPanel.add(ignoreAlwaysButton);
    contentPanel.add(manualCorrectionButton);

    // Add content panel to the dialog
    this.add(contentPanel);
    this.pack();
  }

  // ActionListener implementation
  private static class ButtonClickListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // Handle button clicks here
      JButton source = (JButton) e.getSource();
      System.out.println("Button clicked: " + source.getText());
      // Handle each button click using if statements
      if ("Suggestion #1".equals(source.getText())) {
        // Handle suggestion #1 button click
        System.out.println("Handling Suggestion #1");
      } else if ("Suggestion #2".equals(source.getText())) {
        // Handle suggestion #2 button click
        System.out.println("Handling Suggestion #2");
      } else if ("Suggestion #3".equals(source.getText())) {
        // Handle suggestion #3 button click
        System.out.println("Handling Suggestion #3");
      } else if ("Suggestion #4".equals(source.getText())) {
        // Handle suggestion #4 button click
        System.out.println("Handling Suggestion #4");
      } else if ("Add word to dictionary".equals(source.getText())) {
        // Handle add to dictionary button click
        System.out.println("Handling Add word to dictionary");
      } else if ("Ignore once".equals(source.getText())) {

        System.out.println("Handling Ignore once");
      } else if ("Ignore always".equals(source.getText())) {
        // Handle ignore always button click
        System.out.println("Handling Ignore always");
      } else if ("Manual correction".equals(source.getText())) {
        // Handle manual correction button click
        System.out.println("Handling Manual correction");
      } else {
        System.out.println("Unknown button clicked");
      }
    }
  }

  public void showSuggestionsDialog() {
    this.setVisible(true);
  }
}
