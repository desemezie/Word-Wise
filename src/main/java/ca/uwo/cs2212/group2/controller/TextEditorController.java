package ca.uwo.cs2212.group2.controller;

import ca.uwo.cs2212.group2.model.Speller;
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
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.*;

/**
 * @author Ryan Hecht
 *     <p>Controller for the text editor.
 */
public class TextEditorController {
  private final TextEditor textEditor;
  private final Speller speller;
  private SuggestionsPopup currentPopup;

  /**
   * Constructor for the text editor controller.
   *
   * @param textEditor the text editor
   */
  public TextEditorController(TextEditor textEditor) {
    this.textEditor = textEditor;
    this.speller = Speller.getInstance();

    initSubscriptions();
    attachMouseMotionListener();
    attachCaretListener();
  }

  /** Initializes the subscriptions for the text editor. */
  private void initSubscriptions() {
    Scheduler swingScheduler = Schedulers.from(SwingUtilities::invokeLater);

    Observable<String> textChanges =
        textEditor
            .getTextChanges()
            .publish(
                shared -> {
                  Observable<String> debounced = shared.debounce(200, TimeUnit.MILLISECONDS);
                  Observable<String> periodic = shared.sample(2, TimeUnit.SECONDS);

                  return Observable.merge(debounced, periodic).distinctUntilChanged();
                });

    textChanges
        .observeOn(Schedulers.computation())
        .map(
            text -> {
              speller.spellcheck(text);
              return speller.getWrongWords();
            })
        .observeOn(swingScheduler)
        .subscribe(this::underlineMisspelledWords);
  }

  /** Attaches a mouse motion listener to the text pane. */
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

  /**
   * Processes the mouse movement event.
   *
   * @param e the mouse event
   */
  private void processMouseMovement(MouseEvent e) {
    JTextPane textPane = textEditor.getTextPane();
    int pos = textPane.viewToModel2D(e.getPoint());
    if (pos >= 0) {
      try {
        StyledDocument doc = textPane.getStyledDocument();
        int wordStart = getWordStart(doc, pos);
        int wordEnd = getWordEnd(doc, pos);

        String stringUnderMouse = doc.getText(wordStart, wordEnd - wordStart);
        Word wordUnderMouse = findWordAtPosition(stringUnderMouse, wordStart);
        if (wordUnderMouse != null) {
          showSuggestionsDialogAtPosition(wordUnderMouse);
        }
      } catch (BadLocationException ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Returns the start position of the word at the given position.
   *
   * @param doc the document
   * @param pos the position
   * @return the start position of the word
   * @throws BadLocationException if the position is invalid
   */
  private int getWordStart(StyledDocument doc, int pos) throws BadLocationException {
    while (pos > 0 && !Character.isWhitespace(doc.getText(pos - 1, 1).charAt(0))) {
      pos--;
    }
    return pos;
  }

  /**
   * Returns the end position of the word at the given position.
   *
   * @param doc the document
   * @param pos the position
   * @return the end position of the word
   * @throws BadLocationException if the position is invalid
   */
  private int getWordEnd(StyledDocument doc, int pos) throws BadLocationException {
    while (pos < doc.getLength() && !Character.isWhitespace(doc.getText(pos, 1).charAt(0))) {
      pos++;
    }
    return pos;
  }

  /**
   * Shows the suggestions dialog for the given word at the given position.
   *
   * @param word the misspelled word
   */
  private void showSuggestionsDialogAtPosition(Word word) {
    try {
      JTextPane textPane = textEditor.getTextPane();
      Rectangle wordRect = textPane.modelToView2D(word.getPosition()).getBounds();

      // Get suggestions for the word
      String[] suggestions = word.getOptionsAsStringArray();
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

  /**
   * Replaces the misspelled word in the text pane with the selected suggestion.
   *
   * @param originalWord the misspelled word
   * @param newWord the suggestion
   */
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
   * Finds the word with the given content and position.
   *
   * @param content the content
   * @param position the position
   * @return the word
   */
  private Word findWordAtPosition(String content, int position) {
    for (Word word : speller.getWrongWords()) {
      if (word.getContent().equals(content) && word.getPosition() == position) {
        return word;
      }
    }
    return null;
  }

  /**
   * Underlines the misspelled words in the text pane.
   *
   * @param misspelledWords the list of misspelled words
   */
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

  /** Attaches a caret listener to the text pane. */
  private void attachCaretListener() {
    JTextPane textPane = textEditor.getTextPane();
    textPane.addCaretListener(event -> resetCaretStyle(event.getDot()));
  }

  /**
   * Resets the caret style at the given position.
   *
   * @param position the position
   */
  private void resetCaretStyle(int position) {
    SwingUtilities.invokeLater(
        () -> {
          JTextPane textPane = textEditor.getTextPane();
          StyledDocument doc = textPane.getStyledDocument();
          SimpleAttributeSet attrs = new SimpleAttributeSet();
          StyleConstants.setForeground(attrs, Color.BLACK);
          StyleConstants.setUnderline(attrs, false);

          try {
            // Ensure the position is within the document's length
            if (position < doc.getLength()) {
              doc.setCharacterAttributes(position, 1, attrs, false);
            }
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        });
  }
}
