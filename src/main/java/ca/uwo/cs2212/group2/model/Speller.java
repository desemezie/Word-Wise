package ca.uwo.cs2212.group2.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// Incomplete speller object, currently only has a spellcheck function
// test

public class Speller {
  /*
   * textproccessor usertext;
   * dictionary dict;
   * Metrics metric;
   */
  private TextProcessor usertext;
  private Dictionary dict;
  private Metrics metric;
  private List<Word> incorrectWords;
  private List<Word> previousIncorrectWords;
  private List<Word> allWords;
  private List<Word> uncheckedWords;

  public Speller() {
    allWords = new ArrayList<Word>();
    incorrectWords = new ArrayList<Word>();
    usertext = new TextProcessor();
    dict = loadDict();
  }

  public List<Word> getAllwords() {
    return this.allWords;
  }

  public List<Word> getWrongWords() {
    return this.incorrectWords;
  }

  public Dictionary getDict() {
    return this.dict;
  }

  public int[] getStats() {

    if (this.usertext == null) {
      System.out.println("Usertext object not found");
    }
    // linecount, wordcount, charcountnospace, incorrectwords
    int[] result = new int[4];
    result[0] = this.usertext.getLineCount(); // lines
    result[1] = this.usertext.getWordCount(); // words
    result[2] = (int) this.usertext.getCharCountNoSpace(); // chars
    result[3] = incorrectWords.size(); // incorrect words

    return result;
  }

  public void spellcheck(String inText) {
    // 1. Create textproccessor object out of given text
    try {
      usertext.parseHtmlString(inText);
    } catch (Exception x) {
      System.out.println(String.format("User text file %s not found", inText));
    }

    // 2. Reset word arrays
    this.resetWrongWords();
    this.resetAllWords();

    // 3. Iterate through all words of textproccessor, calling spellcheck on the word
    this.allWords = usertext.getWords();
    this.uncheckedWords = new ArrayList<>(this.allWords);

    this.loadPreviousIncorrectWordCache();

    // mark doubles
    doubleWords(this.uncheckedWords);

    List<Word> wrongWords = new ArrayList<Word>();
    // STATISTICALS
    List<Word> doubleWords = new ArrayList<Word>();

    //
    for (Word w : this.uncheckedWords) {
      // lowercase the word before checking it
      String wc = w.getContent().toLowerCase();
      // Either it is not in the dictionary, OR it is midcapitalized, OR it at start of sentence OR
      // is a double word
      if ((!dict.checkWord(wc)) || isMidcapped(w.getContent()) || isMiscapped(w) || w.getDouble()) {
        // All words considered incorrect and will be spellchecked
        w.setCorrect(false);
        makeCorrections(w, dict);
        wrongWords.add(w);

        // If it is a double word, add it to doubleWords
        if (w.getDouble()) {
          doubleWords.add(w);
          // Add a blank correctionsuggestion representing deletion
          CorrectionSuggestions blank = new CorrectionSuggestions("", 0);
          w.addOption(blank);
        }

        // add it to incorrectWords
        this.incorrectWords.add(w);
      }
    }
  }

  /**
   * Finds current incorrect words and adds suggestions to them based off the previously cached
   * incorrect words.
   *
   * <p>Why not just load the previous incorrect words into the new incorrect words? First off, they
   * may have been deleted, second, by matching words by their contents we can match words agnostic
   * of their position and use the same suggestions for duplicate words as opposed to having to
   * compute the same suggestions multiple times.
   */
  private void loadPreviousIncorrectWordCache() {
    Map<String, Word> incorrectWordMap = new HashMap<>();

    for (Word incorrectWord : this.previousIncorrectWords) {
      // Only put words in that are not doubles, if they're doubles they'll be added later
      if (!incorrectWord.getDouble()) {
        incorrectWordMap.put(incorrectWord.getContent(), incorrectWord);
      }
    }

    for (Word word : this.allWords) {
      incorrectWordMap.computeIfPresent(
          word.getContent(),
          (key, cachedIncorrectWord) -> {
            word.setOptions(cachedIncorrectWord.getOptions());
            word.setCorrect(cachedIncorrectWord.getCorrect());

            if (word.getCorrect()) {
              System.err.println("Correct word found in wrong words list");
            }

            this.incorrectWords.add(word);
            uncheckedWords.remove(word);
            return cachedIncorrectWord; // You need to return the modified value
          });
    }
  }

  /** Resets the list of incorrect words by moving the current list to the previous list. */
  private void resetWrongWords() {
    this.previousIncorrectWords = this.incorrectWords;
    this.incorrectWords = new ArrayList<Word>();
  }

  private void resetAllWords() {
    this.allWords = new ArrayList<>();
  }

  // Checks if a word has a starting capital letter where it shouldn't
  private static boolean isMiscapped(Word inword) {
    String w = inword.getContent();
    boolean isBeginning = inword.isBeginning();
    // either it is a starting word with no capitalization, or it is a non starter with a
    // capitalization
    if (!isBeginning && Character.isUpperCase(w.charAt(0))
        || isBeginning && !(Character.isUpperCase(w.charAt(0)))) {
      return true;
    }
    return false;
  }

  /**
   * @param inlist list of words
   */
  private static void doubleWords(List<Word> inlist) {
    String prev = "";
    for (Word w : inlist) {
      // if this is a duplicate word
      if (w.getContent().equals(prev)) {
        // tag as double
        w.setDouble(true);
      }
      prev = w.getContent();
    }
  }

  /**
   * @param word word to be checked for capital letters in the middle
   * @return true if word has capital letters in the middle of it
   */
  private static boolean isMidcapped(String word) {
    boolean out = false;
    for (int i = 1; i < word.length(); i++) {
      char c = word.charAt(i);
      if (Character.isUpperCase(c)) {
        out = true;
      }
    }
    return out;
  }

  /**
   * @param inText the name of the input text
   * @return a TextProccessor of that text
   */
  private static TextProcessor process(String inText) {
    // Getting the path for the current directory
    String directoryPath = System.getProperty("user.dir");
    String fileName = inText;

    // Path objects for directory and file
    Path directory = Paths.get(directoryPath);
    Path filepath = directory.resolve(fileName);
    try {
      TextProcessor tp = new TextProcessor(filepath.toString()); // Not throwing correct errors
      return tp;
    } catch (FileNotFoundException x) {
      System.out.println(String.format("Input text %s not found", inText));
      x.printStackTrace();
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * @param w a word to be compared against dictionary
   */
  private static void makeCorrections(Word w, Dictionary dict) {

    Enumeration<String> keys = dict.getKeys();
    // Pq of correctionsuggestions
    PriorityQueue<CorrectionSuggestions> options = new PriorityQueue<CorrectionSuggestions>();
    while (keys.hasMoreElements()) {
      String dictword = keys.nextElement();
      CorrectionSuggestions c =
          new CorrectionSuggestions(dictword, LevDam(w.getContent(), dictword));
      // If the word is at the start of sentence, all options are capitalized
      if (w.isBeginning()) {
        c.setWord(capitalize(c.getWord()));
      }
      options.add(c);
    }
    // Pull out the first 4 closest words out of the pq
    System.out.println(String.format("CORRECTION OPTIONS FOR %s:", w.getContent()));
    for (int i = 0; i < 4; i++) {
      CorrectionSuggestions cj = options.poll();
      w.addOption(cj);
      // Sanity Check
      System.out.println(cj.getWord());
    }
  }

  private static String capitalize(String s) {
    return s.substring(0, 1).toUpperCase() + s.substring(1);
  }

  /**
   * @param s1 origin string
   * @param s2 string to be compared to
   * @return Levenstein Damerau distance between two strings
   */
  private static int LevDam(String s1, String s2) {
    // Creds to https://github.com/tdebatty/java-string-similarity#damerau-levenshtein
    // Checking for input validity
    if (s1 == null) {
      throw new NullPointerException("s1 must not be null");
    }

    if (s2 == null) {
      throw new NullPointerException("s2 must not be null");
    }

    // No calculation required if it's the same word
    if (s1.equals(s2)) {
      return 0;
    }

    // Calculate max possible distance
    int inf = s1.length() + s2.length();

    // Create and initialize the character array indices (The 2D array)
    HashMap<Character, Integer> da = new HashMap<Character, Integer>();

    for (int d = 0; d < s1.length(); d++) {
      da.put(s1.charAt(d), 0);
    }

    for (int d = 0; d < s2.length(); d++) {
      da.put(s2.charAt(d), 0);
    }

    // A second matrix
    // Create the distance matrix H[0 .. s1.length+1][0 .. s2.length+1]
    int[][] h = new int[s1.length() + 2][s2.length() + 2];

    // initialize the left and top edges of H
    for (int i = 0; i <= s1.length(); i++) {
      h[i + 1][0] = inf;
      h[i + 1][1] = i;
    }

    for (int j = 0; j <= s2.length(); j++) {
      h[0][j + 1] = inf;
      h[1][j + 1] = j;
    }

    // fill in the distance matrix H
    // look at each character in s1
    for (int i = 1; i <= s1.length(); i++) {
      int db = 0;

      // look at each character in b
      for (int j = 1; j <= s2.length(); j++) {
        int i1 = da.get(s2.charAt(j - 1));
        int j1 = db;

        int cost = 1;
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          cost = 0;
          db = j;
        }

        h[i + 1][j + 1] =
            mymin(
                h[i][j] + cost, // substituition
                h[i + 1][j] + 1, // insertion
                h[i][j + 1] + 1, // deletion
                h[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1));
      }

      da.put(s1.charAt(i - 1), i);
    }

    return h[s1.length() + 1][s2.length() + 1];
  }

  private static int mymin(int i, int j, int k, int l) {
    int val = Math.min(Math.min(i, j), Math.min(k, l));
    return val;
  }

  /**
   * @param inName
   * @return true if the file exists in current directory
   */
  private static boolean fileExists(String inName) {
    // Getting the path for the current directory
    String directoryPath = System.getProperty("user.dir");
    String fileName = inName;

    // Path objects for directory and file
    Path directory = Paths.get(directoryPath);
    Path filepath = directory.resolve(fileName);

    // return
    return Files.exists(filepath);
  }

  /**
   * @param origin The origin dictionary
   * @param destination The destination dictionary
   */
  // Transfer all words from origin to destination
  private static void transferWords(Dictionary origin, Dictionary destination) {
    Enumeration<String> originKeys = origin.getKeys();
    while (originKeys.hasMoreElements()) {
      String word = originKeys.nextElement();
      destination.addWord(word);
    }
  }

  /**
   * @return merged dictionary if the dictionaries were successfully loaded
   */
  private Dictionary loadDict() {
    // Load the default dictionary from resources
    Dictionary dict = new Dictionary("dict.txt", true); // true indicates it's a resource

    // Handle the user dictionary
    Path userDictPath = Paths.get(System.getProperty("user.home"), "userdict.txt");
    if (Files.exists(userDictPath)) {
      Dictionary userDict =
          new Dictionary(userDictPath.toString(), false); // false for a regular file
      transferWords(userDict, dict);
      System.out.println("userdict found");
    } else {
      try {
        Files.createFile(userDictPath); // Create a new, empty user dictionary file
        System.out.println("Userdict not found, blank userdict created");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return dict;
  }
}
