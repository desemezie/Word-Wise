package ca.uwo.cs2212.group2.view.components;

import static ca.uwo.cs2212.group2.constants.ViewConstants.MENU_BACKGROUND_COLOUR;
import static ca.uwo.cs2212.group2.constants.ViewConstants.MENU_FONT;
import static ca.uwo.cs2212.group2.constants.ViewConstants.TEXT_BACKGROUND_COLOUR;

import io.reactivex.rxjava3.subjects.PublishSubject;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

public class TextEditor extends JPanel {

  private final JTextPane textPane;
  private final PublishSubject<String> textChanges = PublishSubject.create();

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

    this.setCustomCaret();
  }

  public void spellCheckClicked() {
    System.out.println("Spell check clicked");
  }

  public JTextPane getTextPane() {
    return textPane;
  }

  // Method to expose the text changes observable
  public PublishSubject<String> getTextChanges() {
    return textChanges;
  }

  /**
   * Method to set the caret to a custom caret so that it does not change to red when the user
   * backspaces over a misspelled word.
   */
  private void setCustomCaret() {
    textPane.setCaret(
        new DefaultCaret() {
          @Override
          public void paint(Graphics g) {
            JTextComponent comp = getComponent();
            if (comp == null) {
              return;
            }

            try {
              Rectangle2D r2D = comp.modelToView2D(getDot());
              if (r2D == null) {
                return;
              }

              if (isVisible()) {
                g.setColor(comp.getCaretColor());
                int y1 = (int) r2D.getY();
                int y2 = y1 + (int) r2D.getHeight() - 1;
                g.drawLine((int) r2D.getX(), y1, (int) r2D.getX(), y2);
              }
            } catch (BadLocationException e) {
              return;
            }
          }
        });
    textPane.setCaretColor(Color.BLACK);
  }

  /** Method to initialize the observables for the text editor. */
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
}
