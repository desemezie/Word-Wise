package ca.uwo.cs2212.group2.view.components;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import static ca.uwo.cs2212.group2.constants.ViewConstants.TEXT_BACKGROUND_COLOUR;
import static ca.uwo.cs2212.group2.constants.ViewConstants.MENU_FONT;
import static ca.uwo.cs2212.group2.constants.ViewConstants.MENU_BACKGROUND_COLOUR;

public class TextEditor {

    private static JTextArea textArea;

    public TextEditor(){
        textArea = new JTextArea();
        textArea.setBackground(TEXT_BACKGROUND_COLOUR);
        textArea.setFont(MENU_FONT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(50, 50, 50, 50));

        textArea.setBorder(border);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new MatteBorder(10, 345, 10, 345, MENU_BACKGROUND_COLOUR)); // top, left, bottom, right

    }
    public static void main(String[]args){
        TextEditor page = new TextEditor();
    }
}
