package ca.uwo.cs2212.group2.view.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static ca.uwo.cs2212.group2.constants.ViewConstants.TEXT_BACKGROUND_COLOUR;
import static ca.uwo.cs2212.group2.constants.ViewConstants.MENU_FONT;
import static ca.uwo.cs2212.group2.constants.ViewConstants.MENU_BACKGROUND_COLOUR;

public class TextEditor extends JPanel {

  private JTextArea textArea;

  /** Constructor for the text editor */
  public TextEditor(Dimension frameSize) {
    textArea = new JTextArea();
    textArea.setBackground(TEXT_BACKGROUND_COLOUR);
    textArea.setFont(MENU_FONT);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);

    Border border =
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            BorderFactory.createEmptyBorder(50, 50, 50, 50));
    textArea.setBorder(border);

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setBorder(
        new MatteBorder(10, 345, 10, 345, MENU_BACKGROUND_COLOUR)); // top, left, bottom, right

    int preferredWidth = (int) (frameSize.width * 0.5); // 50% of frame width
    int preferredHeight = (int) (preferredWidth * Math.sqrt(2));
    scrollPane.setPreferredSize(new Dimension(preferredWidth, preferredHeight));

    this.setLayout(new BorderLayout());
    this.add(scrollPane, BorderLayout.CENTER); // Add the scrollPane to this panel
  }

  public JTextArea getTextArea() {
    return textArea;
  }
}
