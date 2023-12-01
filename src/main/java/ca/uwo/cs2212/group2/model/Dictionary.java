package ca.uwo.cs2212.group2.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Objects;


/**
 * Represents and manages the dictionary object.
 *
 * @author Daniel Esemezie 
 *     <p>This class manages the dictionary class and includes methods to create a Dictionary object from a file, add word,
 *     remove word, check if word is in a dictionary, a toString method 
 */ 
public class Dictionary {

  private Hashtable<String, Boolean> Dictionary_Hashtable;



  // Constructor that takes a filename for a dictionary file and initializes the HashTable

  /**
   * Constructor 
   *
   * @param filename the name of the file that has the dictionary words
   * @param isResource 
   
   */
  public Dictionary(String filename, boolean isResource) {
    Dictionary_Hashtable = new Hashtable<>();
    if (isResource) {
      loadDictionaryFromResource(filename);
    } else {
      loadDictionaryFromFile(filename);
    }
  }

  

  
  
  private void loadDictionaryFromFile(String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        addWord(line.trim());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadDictionaryFromResource(String filename) {
    try (BufferedReader br =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(
                    this.getClass().getClassLoader().getResourceAsStream(filename))))) {
      String line;
      while ((line = br.readLine()) != null) {
        addWord(line.trim());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
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

  // Method to get the enumeration of keys out of the object
  public Enumeration<String> getKeys() {
    return this.Dictionary_Hashtable.keys();
  }

  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Dictionary Contents:\n");

    Enumeration<String> keys = Dictionary_Hashtable.keys();
    while (keys.hasMoreElements()) {
        String key = keys.nextElement();
        stringBuilder.append(key).append("\n");
    }

    return stringBuilder.toString();
}

}
