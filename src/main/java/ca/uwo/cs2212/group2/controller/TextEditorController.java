package ca.uwo.cs2212.group2.controller;

import ca.uwo.cs2212.group2.model.Speller;
import ca.uwo.cs2212.group2.model.TextProcessor;
import ca.uwo.cs2212.group2.model.Word;
import ca.uwo.cs2212.group2.view.components.SuggestionsPopup;
import ca.uwo.cs2212.group2.view.components.TextEditor;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.text.*;

public class TextEditorController {
  private final TextEditor textEditor;
  private Speller speller;
  private SuggestionsPopup currentPopup;

  public TextEditorController(TextEditor textEditor) {
    this.textEditor = textEditor;
    this.speller = new Speller();

    initSubscriptions();
    attachMouseMotionListener();
  }

  private void initSubscriptions() {
    Scheduler swingScheduler = Schedulers.from(SwingUtilities::invokeLater);

    Observable<String> textChanges =
        textEditor
            .getTextChanges()
            .publish(
                shared -> {
                  Observable<String> debounced = shared.debounce(300, TimeUnit.MILLISECONDS);
                  Observable<String> periodic = shared.sample(5, TimeUnit.SECONDS);

                  return Observable.merge(debounced, periodic).distinctUntilChanged();
                });

    textChanges
        .observeOn(Schedulers.computation())
        .map(
            text -> {
              this.speller = new Speller();
              speller.spellcheck(text);
              return speller.getWrongWords();
            })
        .observeOn(swingScheduler)
        .subscribe(this::underlineMisspelledWords);
  }

  private void attachMouseMotionListener() {
    JTextPane textPane = textEditor.getTextPane();

    // Create an Observable for mouse movement events
    Observable<MouseEvent> mouseMoveObservable =
        Observable.create(
            emitter -> {
              MouseMotionAdapter mouseAdapter =
                  new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                      emitter.onNext(e);
                    }
                  };
              textPane.addMouseMotionListener(mouseAdapter);

              // Remove the listener when the Observable is disposed
              emitter.setCancellable(() -> textPane.removeMouseMotionListener(mouseAdapter));
            });

    Disposable mouseMoveSubscription =
        mouseMoveObservable
            .debounce(300, TimeUnit.MILLISECONDS, Schedulers.io())
            .observeOn(Schedulers.from(SwingUtilities::invokeLater))
            .subscribe(this::processMouseMovement);
  }

  private void processMouseMovement(MouseEvent e) {
    JTextPane textPane = textEditor.getTextPane();
    int pos = textPane.viewToModel2D(e.getPoint());
    if (pos >= 0) {
      try {
        StyledDocument doc = textPane.getStyledDocument();
        int wordStart = getWordStart(doc, pos);
        int wordEnd = getWordEnd(doc, pos);

        String stringUnderMouse = doc.getText(wordStart, wordEnd - wordStart);
        Word wordUnderMouse = findWordInSpeller(stringUnderMouse);
        if (wordUnderMouse != null) {
          showSuggestionsDialogAtPosition(wordUnderMouse, e.getPoint());
        }
      } catch (BadLocationException ex) {
        ex.printStackTrace();
      }
    }
  }

  private int getWordStart(StyledDocument doc, int pos) throws BadLocationException {
    while (pos > 0 && !Character.isWhitespace(doc.getText(pos - 1, 1).charAt(0))) {
      pos--;
    }
    return pos;
  }

  private int getWordEnd(StyledDocument doc, int pos) throws BadLocationException {
    while (pos < doc.getLength() && !Character.isWhitespace(doc.getText(pos, 1).charAt(0))) {
      pos++;
    }
    return pos;
  }

  private void showSuggestionsDialogAtPosition(Word word, Point mousePosition) {
    try {
      JTextPane textPane = textEditor.getTextPane();
      Rectangle wordRect = textPane.modelToView2D(word.getPosition()).getBounds();

      // Get suggestions for the word
      String[] suggestions = word.getOption();
      if (suggestions.length > 0) {
        SuggestionsPopup popup =
            new SuggestionsPopup(
                suggestions[0],
                suggestions[1],
                suggestions[2],
                suggestions[3],
                suggestion -> replaceWordInTextPane(word, suggestion));

        // Position the popup directly below the word
        Point popupLocation = new Point(wordRect.x, wordRect.y + wordRect.height);
        SwingUtilities.convertPointToScreen(popupLocation, textPane);
        popup.setLocation(popupLocation);
        popup.showSuggestionsDialog();

        // Before showing the new popup, close the existing one if it's open
        if (currentPopup != null) {
          currentPopup.dispose();
        }
        currentPopup = popup;
      }
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
  }

  private void replaceWordInTextPane(Word originalWord, String newWord) {
    try {
      JTextPane textPane = textEditor.getTextPane();
      StyledDocument doc = textPane.getStyledDocument();
      doc.remove(originalWord.getPosition(), originalWord.getContent().length());
      doc.insertString(originalWord.getPosition(), newWord, null);
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
  }

  /**
   * THIS IS WRONG: WE DON'T CHECK FOR POSITION AT ALL HERE!
   *
   * @param stringUnderMouse
   * @return
   */
  private Word findWordInSpeller(String stringUnderMouse) {
    System.out.println("Looking for word: " + stringUnderMouse);
    for (Word word : speller.getWrongWords()) {
      if (word.getContent().equals(stringUnderMouse)) {
        return word;
      }
    }
    return null;
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
}
