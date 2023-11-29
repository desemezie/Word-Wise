package ca.uwo.cs2212.group2.controller;

import ca.uwo.cs2212.group2.model.SpellChecker;
import ca.uwo.cs2212.group2.model.Word;
import ca.uwo.cs2212.group2.view.components.TextEditor;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.awt.Color;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TextEditorController {
  private final TextEditor textEditor;

  public TextEditorController(TextEditor textEditor) {
    this.textEditor = textEditor;

    initSubscriptions();
    //    attachListeners();
  }

  private void initSubscriptions() {
    Scheduler swingScheduler = Schedulers.from(SwingUtilities::invokeLater);

    SwingUtilities.invokeLater(
        () -> {
          textEditor
              .getTextChanges()
              .debounce(200, TimeUnit.MILLISECONDS) // Wait for 500ms of inactivity
              .map(this::convertStringToWords) // Convert the string to a list of Words
              .distinctUntilChanged() // Only emit if the list of Words has changed
              .subscribe(this::underlineMisspelledWords); // Process the list of Words
        });
    textEditor
        .getTextChanges()
        .debounce(200, TimeUnit.MILLISECONDS) // Wait for 500ms of inactivity
        .map(this::convertStringToWords) // Convert the string to a list of Words
        .distinctUntilChanged() // Only emit if the list of Words has changed
        .subscribe(this::underlineMisspelledWords); // Process the list of Words
  }

  private List<Word> convertStringToWords(String text) {
    List<Word> words = new ArrayList<>();
    int start = 0;

    while (start < text.length()) {
      int end = text.indexOf(' ', start);
      if (end == -1) {
        end = text.length();
      }

      String wordContent = text.substring(start, end);
      if (!wordContent.isEmpty()) {
        Word word = new Word(wordContent, start);
        words.add(word);
      }

      start = end + 1;
    }

    return words;
  }

  private void underlineMisspelledWords(List<Word> misspelledWords) {
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

  //  private void attachListeners() {
  //    textEditor.attachCaretListener(createCaretListener());
  //  }
  //
  //  public CaretListener createCaretListener() {
  //    return e -> resetStyleAtCaretPosition(e.getDot());
  //  }
  //
  //  private void resetStyleAtCaretPosition(int position) {
  //    SwingUtilities.invokeLater(
  //        () -> {
  //          try {
  //            StyledDocument doc = textEditor.getTextPane().getStyledDocument();
  //            SimpleAttributeSet attrs = new SimpleAttributeSet();
  //            StyleConstants.setUnderline(attrs, false);
  //            StyleConstants.setForeground(attrs, Color.BLACK); // or any default color
  //
  //            // Apply the default style at the current caret position
  //            // Check bounds to ensure position is valid
  //            if (position >= 0 && position < doc.getLength()) {
  //              doc.setCharacterAttributes(position, 1, attrs, false);
  //            }
  //          } catch (Exception e) {
  //            e.printStackTrace();
  //          }
  //        });
  //  }
}
