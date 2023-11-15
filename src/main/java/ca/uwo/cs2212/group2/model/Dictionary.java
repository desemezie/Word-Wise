package ca.uwo.cs2212.group2.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        Dictionary_Hashtable.put(word.toLowerCase(), true); // Convert the word to lowercase to make the dictionary case-insensitive
    }

    // Method to remove a word from the dictionary
    public void removeWord(String word) {
        Dictionary_Hashtable.remove(word.toLowerCase());
    }

    // Method to check if a word is in the dictionary
    public void checkWord(String word) {
        if (Dictionary_Hashtable.containsKey(word.toLowerCase())) {
            System.out.println(word + " is in the dictionary.");
        } else {
            System.out.println(word + " is not in the dictionary.");
        }
    }

    // Method to load words from a file into the hashtable
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

    public static void main(String[] args) {
        // Example usage
        Dictionary dictionary = new Dictionary("words.txt");

        // Adding a word to the dictionary
        dictionary.addWord("Tolu");

        // Removing a word from the dictionary
        dictionary.removeWord("Ababua");

        // Checking if a word is in the dictionary
        dictionary.checkWord("Ababua");
        dictionary.checkWord("Tochi");
    }




}