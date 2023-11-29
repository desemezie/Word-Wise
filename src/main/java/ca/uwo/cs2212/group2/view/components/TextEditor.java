package ca.uwo.cs2212.group2.view.components;

import static ca.uwo.cs2212.group2.constants.ViewConstants.MENU_BACKGROUND_COLOUR;
import static ca.uwo.cs2212.group2.constants.ViewConstants.MENU_FONT;
import static ca.uwo.cs2212.group2.constants.ViewConstants.TEXT_BACKGROUND_COLOUR;

import io.reactivex.rxjava3.subjects.PublishSubject;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextEditor extends JPanel {

  private JTextPane textPane; // JTextPane allows text styling
  private PublishSubject<String> textChanges = PublishSubject.create();

  /** Constructor for the text editor */
  public TextEditor(Dimension frameSize) {
    textPane = new JTextPane();
    textPane.setBackground(TEXT_BACKGROUND_COLOUR);
    textPane.setFont(MENU_FONT);

    initObservables();

    Border border =
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            BorderFactory.createEmptyBorder(50, 50, 50, 50));
    textPane.setBorder(border);

    JScrollPane scrollPane = new JScrollPane(textPane);
    scrollPane.setBorder(
        new MatteBorder(10, 345, 10, 345, MENU_BACKGROUND_COLOUR)); // top, left, bottom, right

    int preferredWidth = (int) (frameSize.width * 0.5); // 50% of frame width
    int preferredHeight = (int) (preferredWidth * Math.sqrt(2));
    scrollPane.setPreferredSize(new Dimension(preferredWidth, preferredHeight));

    this.setLayout(new BorderLayout());
    this.add(scrollPane, BorderLayout.CENTER); // Add the scrollPane to this panel
  }

  private void initObservables() {
    // Attach a document listener to the JTextPane's document
    textPane
        .getDocument()
        .addDocumentListener(
            new DocumentListener() {
              public void insertUpdate(DocumentEvent e) {
                textChanges.onNext(textPane.getText());
              }

              public void removeUpdate(DocumentEvent e) {
                textChanges.onNext(textPane.getText());
              }

              public void changedUpdate(DocumentEvent e) {
                // Plain text components do not fire these events
              }
            });
  }

  // Method to randomly mark words as misspelled and underline them
  public void simulateSpellCheck() {
    System.out.println("Simulating spell check");
  }

  public JTextPane getTextPane() {
    return textPane;
  }

  // Method to expose the text changes observable
  public PublishSubject<String> getTextChanges() {
    return textChanges;
  }
}
