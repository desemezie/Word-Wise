package ca.uwo.cs2212.group2;

import ca.uwo.cs2212.group2.model.*;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Ctrl+R or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Ctrl+D to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Cmd+F8.
            System.out.println("i = " + i);
        }

        // TESTING
        CorrectionSuggestions cs1 = new CorrectionSuggestions("happy", 4);
        CorrectionSuggestions cs2 = new CorrectionSuggestions("FROG", 2);
        System.out.println(cs1.getWord());
        System.out.println(cs2.getDistance());
        cs1.setWord("bye");
        cs2.setDistance(0);
        System.out.println(cs1.getWord());
        System.out.println(cs2.getDistance());
        Word w1 = new Word("apple");
        Word w2 = new Word("pear");
        System.out.println(w1.getContent());
        w1.setContent("dog");
        System.out.println(w1.getContent());
        w2.setOption("star", 4);
        w2.setOption("moon", 0);
        System.out.println(Arrays.toString(w2.getOption()));
    }
}