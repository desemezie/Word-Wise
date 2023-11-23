package ca.uwo.cs2212.group2.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Processes text from a file, providing statistics and manipulations on the text.
 *
 * @author Annabel Irani
 * @author Ryan Hecht
 *     <p>This class can parse text files and provide information such as line count, word count,
 *     and character count (with and without spaces). It also identifies and marks specific words
 *     based on their placement in the text (e.g., words after a period).
 */
public class TextProcessor {



  private int lineCount;
  private int wordCount;
  private long charCountWithSpace;
  private long charCountNoSpace;


  // Internal
  private static BufferedReader text;
  private List<Word> words;
  private List<String> lines;

  /**
   * Constructs a TextProcessor and initializes parsing of the provided file.
   *
   * @param fileName the name of the file to be parsed.
   * @throws FileNotFoundException if the specified file cannot be found.
   */
  public TextProcessor(String fileName) throws Exception {
    this.words = new ArrayList<>();

    try {
      lines = this.parse(fileName);
      this.wordCount = lines.size();
      wordsAfterPeriod();
    } catch (FileNotFoundException e) {
      throw e; // Just rethrow if it's specifically a FileNotFoundException
    } catch (Exception e) {
      throw new Exception("Unknown exception occurred in TextProcessor constructor" + e);
    }
  }

  /**
   * Parses the provided file and extracts words and other text statistics.
   *
   * @param fileName the name of the file to be parsed.
   * @return a List containing words from the file.
   */
  private List<String> parse(String fileName) throws Exception {
    List<String> words = new ArrayList<String>();
    this.lineCount = 0;
    try (Scanner scan = new Scanner(new File(fileName))) {
      while (scan.hasNextLine()) {
        String line = scan.nextLine();
        this.lineCount++;
        this.charCountNoSpace += getNumCharNoSpace(line);
        this.charCountWithSpace += line.length();

        String[] lineWords = line.split("\\s+|,|\\;|\\(|\\)|\\{|\\}|\\:");

        for (String word : lineWords) {
          if (!word.isEmpty()) {

            words.add(word);
            Word newWord = new Word(word);
            this.words.add(newWord);
            this.wordCount++;
          }
        }
      }
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found by parse: " + e);
    } catch (Exception e) {
      throw new Exception("Unknown exception occurred in parse: " + e);
    }

    return words;
  }

  /** Marks words that need to be capitalized (i.e., words after a period) */
  private void wordsAfterPeriod() {
    boolean afterPeriod = false;

    for (Word word : this.words) {
      if (afterPeriod) {
        // change words instance variable to True
        afterPeriod = false; // Reset after printing
      }

      if (word.getContent().charAt(word.getContent().length() - 1) == '.'
          || word.getContent().charAt(word.getContent().length() - 1) == '!'
          || word.getContent().charAt(word.getContent().length() - 1) == '?') {

        afterPeriod = true;
        word.setContent(word.getContent().substring(0, (word.getContent().length() - 1)));
      }
    }
  }

  /**
   * @param line the line to count the number of characters in
   * @return the number of characters in the line, excluding whitespace
   */
  private long getNumCharNoSpace(String line) {
    return line.chars().filter(c -> !Character.isWhitespace(c)).count();
  }

  public void setLineCount(int numLines) {
    this.lineCount = numLines;
  }

  public void setWordCount(int numWords) {
    this.wordCount = numWords;
  }

  public void setCharCountWithSpace(long numChars) {
    this.charCountWithSpace = numChars;
  }

  public long getCharCountWithSpace() {
    return this.charCountWithSpace;
  }

  public void setCharCountNoSpace(long numChars) {
    this.charCountNoSpace = numChars;
  }

  public long getCharCountNoSpace() {
    return this.charCountNoSpace;
  }

  public int getLineCount() {
    return lineCount;
  }

  public int getWordCount() {
    return wordCount;
  }

  /**
   *
   * @return list of all words in the text file as Word objects
   */
	public List<Word> getWords(){
		return this.words;
	}
}
