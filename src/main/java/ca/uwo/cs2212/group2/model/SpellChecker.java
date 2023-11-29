package ca.uwo.cs2212.group2.model;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpellChecker {
  private final PublishSubject<String> textToCheck = PublishSubject.create();
  private final Observable<List<Word>> misspelledWords;

  public SpellChecker() {
    misspelledWords =
        textToCheck
            .observeOn(Schedulers.computation())
            .map(this::findMisspelledWords)
            .publish()
            .refCount();
  }

  public void checkSpelling(String text) {
    textToCheck.onNext(text);
  }

  public Observable<List<Word>> getMisspelledWords() {
    return misspelledWords;
  }

  private List<Word> findMisspelledWords(String text) {
    // For now, let's simulate with random logic as before
    String[] words = text.split("\\s+");
    List<Word> misspelled = new ArrayList<>();
    Random rand = new Random();
    for (String word : words) {
      if (rand.nextBoolean()) {
        misspelled.add(new Word(word)); // Word is a hypothetical class
      }
    }
    return misspelled;
  }
}
