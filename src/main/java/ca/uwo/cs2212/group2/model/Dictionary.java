package ca.uwo.cs2212.group2.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

public class Dictionary {

  private Hashtable<String, Boolean> Dictionary_Hashtable;

  // Constructor that takes a filename for a dictionary file and initializes the HashTable
  public Dictionary(String filename) {

    Dictionary_Hashtable = new Hashtable<>();
    loadDictionaryFromFile(filename);
  }

  // Method to add a word to the dictionary
  public void addWord(String word) {
    Dictionary_Hashtable.put(
        word.toLowerCase(),
        true); // Convert the word to lowercase to make the dictionary case-insensitive
  }

  // Method to remove a word from the dictionary
  public void removeWord(String word) {
    Dictionary_Hashtable.remove(word.toLowerCase());
  }

  // Method to check if a word is in the dictionary
  public boolean checkWord(String word) {
    if (Dictionary_Hashtable.containsKey(word)) {
      return true;
    } else {
      return false;
    }
  }

  // Method to load words from a file into the hashtable
  private void loadDictionaryFromFile(String filename) {
    try (BufferedReader br =
        new BufferedReader(
            new InputStreamReader(
                this.getClass().getClassLoader().getResourceAsStream(filename)))) {
      String line;
      while ((line = br.readLine()) != null) {
        addWord(line.trim());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Method to get the enumeration of keys out of the object
  public Enumeration<String> getKeys() {
    return this.Dictionary_Hashtable.keys();
  }
}
