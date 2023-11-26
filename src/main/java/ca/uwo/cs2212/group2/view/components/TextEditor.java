package ca.uwo.cs2212.group2.view.components;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class TextEditor {

    private static JTextArea textArea;

    public TextEditor(){
        textArea = new JTextArea();
        textArea.setBackground(TEXT_AREA_BG_COLOR);
        textArea.setFont(MENU_FONT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(50, 50, 50, 50));

        textArea.setBorder(border);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new MatteBorder(10, 345, 10, 345, MARGIN_COLOR)); // top, left, bottom, right

    }
    public static void main(String[]args){
        TextEditor page = new TextEditor();
    }
}
