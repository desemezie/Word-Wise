package ca.uwo.cs2212.group2.model;

import java.util.ArrayList;
import java.util.Objects;
import static ca.uwo.cs2212.group2.constants.ModelConstants.MAX_NUMBER_OF_CORRECTION_SUGGESTIONS;

/**
 * Represents a word with its content and correction features.
 *
 * @author Shaylan Pratt
 * @author Ryan Hecht
 *     <p>This class provides functionalities to handle a word's content, whether it is correct, its
 *     position in a sentence, and possible correction suggestions.
 */
public class Word {
  private String content;
  private boolean correct;
  private boolean beginningOfSentence;
  private boolean isDouble;
  private int position;

  private boolean shouldBeIgnored;
  private ArrayList<CorrectionSuggestions> options;

  /**
   * Initializes a new Word instance with the specified content.
   *
   * <p>By default, the word is marked as correct, not at the beginning of a sentence, and with no
   * correction suggestions.
   *
   * @param content The actual word in string form, without additional metadata.
   */
  public Word(String content) {
    this.content = content;
    this.correct = true;
    this.beginningOfSentence = false;
    this.isDouble = false;
    this.options = new ArrayList<CorrectionSuggestions>();
    this.position = 0;
    this.shouldBeIgnored = false;
  }

  public Word(String content, int position) {
    this.content = content;
    this.correct = true;
    this.beginningOfSentence = false;
    this.isDouble = false;
    this.options = new ArrayList<CorrectionSuggestions>();
    this.position = position;
    this.shouldBeIgnored = false;
  }

  /**
   * @param obj An object (either a String or a word)
   * @return True if the contents of the other word are equal the contents of this word
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Word other = (Word) obj;
    return Objects.equals(this.content, other.content);
  }

  /**
   * Hashes the word based on its content.
   *
   * @return a hash code value for the word
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.content);
  }

  /**
   * Returns a string representation of the word and it's metadata. To simply see a string
   * representation of the contents of the word, call Word.getContent.toString().
   *
   * @return A string representation of the word and it's metadata.
   */
  @Override
  public String toString() {
    return "Word{"
        + "content='"
        + this.content
        + '\''
        + ", correct="
        + this.correct
        + ", beginningOfSentence="
        + this.beginningOfSentence
        + ", options="
        + this.options
        + '}';
  }

  /**
   * Adds a correction suggestion to the word. If the maximum number of suggestions is reached,
   * replaces the least relevant suggestion.
   *
   * @param option the CorrectionSuggestion object passed to the function
   */
  public void addOption(CorrectionSuggestions option) {
    if (options.size() < MAX_NUMBER_OF_CORRECTION_SUGGESTIONS) {
      options.add(option);
    } else {
      System.out.println("Too many options");
    }
  }

  /**
   * Returns the correction suggestions for the word.
   *
   * @return An array of correction suggestions.
   */
  public String[] getOptionsAsStringArray() {
    String[] arr = new String[MAX_NUMBER_OF_CORRECTION_SUGGESTIONS];
    for (int i = 0; i < options.size(); i++) {
      arr[i] = options.get(i).getWord();
    }
    return arr;
  }

  public ArrayList<CorrectionSuggestions> getOptions() {
    return options;
  }

  public void setOptions(ArrayList<CorrectionSuggestions> options) {
    this.options = options;
  }

  public boolean isBeginning() {
    return beginningOfSentence;
  }

  public void setBeginning(boolean beginningOfSentence) {
    this.beginningOfSentence = beginningOfSentence;
  }

  public boolean getCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String newContent) {
    this.content = newContent;
  }

  public Boolean getDouble() {
    return this.isDouble;
  }

  public void setDouble(boolean v) {
    this.isDouble = v;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public boolean getShouldBeIgnored() {
    return shouldBeIgnored;
  }

  public void setShouldBeIgnored(boolean shouldBeIgnored) {
    this.shouldBeIgnored = shouldBeIgnored;
  }
}
