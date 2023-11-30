package ca.uwo.cs2212.group2.service;

import ca.uwo.cs2212.group2.model.Word;
import ca.uwo.cs2212.group2.model.WordIndex;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ryan Hecht Singleton service that keeps track of words that should be ignored once.
 */
public class WordsToIgnoreOnceService {
  private static WordsToIgnoreOnceService instance;
  private Set<WordIndex> wordsToIgnoreOnce;

  private WordsToIgnoreOnceService() {
    wordsToIgnoreOnce = new HashSet<>();
  }

  public static WordsToIgnoreOnceService getInstance() {
    if (instance == null) {
      instance = new WordsToIgnoreOnceService();
    }
    return instance;
  }

  public void ignoreWordOnce(Word word) {
    wordsToIgnoreOnce.add(new WordIndex(word.getContent(), word.getPosition()));
  }

  public boolean shouldIgnoreWord(Word word) {
    return wordsToIgnoreOnce.contains(new WordIndex(word.getContent(), word.getPosition()));
  }

  public void clearIgnoredWords() {
    wordsToIgnoreOnce.clear();
  }
}
