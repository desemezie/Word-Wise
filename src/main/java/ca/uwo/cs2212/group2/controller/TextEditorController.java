package ca.uwo.cs2212.group2.controller;

import ca.uwo.cs2212.group2.model.Speller;
import ca.uwo.cs2212.group2.model.TextProcessor;
import ca.uwo.cs2212.group2.model.Word;
import ca.uwo.cs2212.group2.view.components.TextEditor;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class TextEditorController {
  private final TextEditor textEditor;
  private Speller speller;

  public TextEditorController(TextEditor textEditor) {
    this.textEditor = textEditor;
    this.speller = new Speller();

    initSubscriptions();
  }

  private void initSubscriptions() {
    Scheduler swingScheduler = Schedulers.from(SwingUtilities::invokeLater);

    textEditor
        .getTextChanges()
        .debounce(500, TimeUnit.MILLISECONDS) // Wait for 500ms of inactivity
        .distinctUntilChanged()
        .map(
            text -> {
              this.speller = new Speller();
              speller.spellcheck(text);
              return speller.getWrongWords();
            }) // Convert the string to a list of Word
        .observeOn(swingScheduler)
        .subscribe(this::underlineMisspelledWords); // Process the list of Words
  }

  private void underlineMisspelledWords(List<Word> misspelledWords) {
    System.out.println("hello!!!");
    StyledDocument doc = textEditor.getTextPane().getStyledDocument();
    SimpleAttributeSet attrs = new SimpleAttributeSet();

    // Reset to default style first
    StyleConstants.setForeground(attrs, Color.BLACK);
    StyleConstants.setUnderline(attrs, false);
    doc.setCharacterAttributes(0, doc.getLength(), attrs, false);

    // Style for misspelled words
    StyleConstants.setForeground(attrs, Color.RED);
    StyleConstants.setUnderline(attrs, true);

    for (Word word : misspelledWords) {
      doc.setCharacterAttributes(word.getPosition(), word.getContent().length(), attrs, false);
    }
  }
}
